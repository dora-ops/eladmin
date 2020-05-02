package me.zhengjie.modules.handleNotice.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.handleNotice.domain.HandleNotice;
import me.zhengjie.modules.handleNotice.service.dto.HandleNoticeDTO;
import me.zhengjie.modules.handleNotice.repository.HandleNoticeRepository;
import me.zhengjie.modules.handleNotice.service.mapper.HandleNoticeMapper;
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
@CacheConfig(cacheNames = "handleNotice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class HandleNoticeQueryService {

    @Autowired
    private HandleNoticeRepository handleNoticeRepository;

    @Autowired
    private HandleNoticeMapper handleNoticeMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(HandleNoticeDTO handleNotice, Pageable pageable){
        Page<HandleNotice> page = handleNoticeRepository.findAll(new Spec(handleNotice),pageable);
        return PageUtil.toPage(page.map(handleNoticeMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(HandleNoticeDTO handleNotice){
        return handleNoticeMapper.toDto(handleNoticeRepository.findAll(new Spec(handleNotice)));
    }

    class Spec implements Specification<HandleNotice> {

        private HandleNoticeDTO handleNotice;

        public Spec(HandleNoticeDTO handleNotice){
            this.handleNotice = handleNotice;
        }

        @Override
        public Predicate toPredicate(Root<HandleNotice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(handleNotice.getId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("id").as(Long.class),handleNotice.getId()));
            }
            if(!ObjectUtils.isEmpty(handleNotice.getCid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("cid").as(Long.class),handleNotice.getCid()));
            }
            if(!ObjectUtils.isEmpty(handleNotice.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+handleNotice.getName()+"%"));
            }
            if(!ObjectUtils.isEmpty(handleNotice.getUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("uname").as(String.class),"%"+handleNotice.getUname()+"%"));
            }
            if(!ObjectUtils.isEmpty(handleNotice.getUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("uid").as(Long.class),handleNotice.getUid()));
            }
            if(!ObjectUtils.isEmpty(handleNotice.getContent())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("content").as(String.class),"%"+handleNotice.getContent()+"%"));
            }
            if(!ObjectUtils.isEmpty(handleNotice.getHandleUname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("handle_uname").as(String.class),"%"+handleNotice.getHandleUname()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}