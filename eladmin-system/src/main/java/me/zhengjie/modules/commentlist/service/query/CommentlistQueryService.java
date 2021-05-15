package me.zhengjie.modules.commentlist.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.commentlist.domain.Commentlist;
import me.zhengjie.modules.commentlist.service.dto.CommentlistDTO;
import me.zhengjie.modules.commentlist.repository.CommentlistRepository;
import me.zhengjie.modules.commentlist.service.mapper.CommentlistMapper;
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
@CacheConfig(cacheNames = "commentlist")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CommentlistQueryService {

    @Autowired
    private CommentlistRepository commentlistRepository;

    @Autowired
    private CommentlistMapper commentlistMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CommentlistDTO commentlist, Pageable pageable){
        Page<Commentlist> page = commentlistRepository.findAll(new Spec(commentlist),pageable);
        return PageUtil.toPage(page.map(commentlistMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CommentlistDTO commentlist){
        return commentlistMapper.toDto(commentlistRepository.findAll(new Spec(commentlist)));
    }

    class Spec implements Specification<Commentlist> {

        private CommentlistDTO commentlist;

        public Spec(CommentlistDTO commentlist){
            this.commentlist = commentlist;
        }

        @Override
        public Predicate toPredicate(Root<Commentlist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(commentlist.getCusNickname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("cus_nickname").as(String.class),"%"+commentlist.getCusNickname()+"%"));
            }
            if(!ObjectUtils.isEmpty(commentlist.getComment())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("comment").as(String.class),"%"+commentlist.getComment()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}