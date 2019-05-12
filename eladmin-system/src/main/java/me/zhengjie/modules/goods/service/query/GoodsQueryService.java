package me.zhengjie.modules.goods.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.goods.domain.Goods;
import me.zhengjie.modules.goods.service.dto.GoodsDTO;
import me.zhengjie.modules.goods.repository.GoodsRepository;
import me.zhengjie.modules.goods.service.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "goods")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GoodsQueryService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(GoodsDTO goods, Pageable pageable){
        Page<Goods> page = goodsRepository.findAll(new Spec(goods),pageable);
        return PageUtil.toPage(page.map(goodsMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(GoodsDTO goods){
        return goodsMapper.toDto(goodsRepository.findAll(new Spec(goods)));
    }

    class Spec implements Specification<Goods> {

        private GoodsDTO goods;

        public Spec(GoodsDTO goods){
            this.goods = goods;
        }

        @Override
        public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(goods.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+goods.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}