package me.zhengjie.modules.reply.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.reply.domain.Reply;
import me.zhengjie.modules.reply.service.dto.ReplyDTO;
import me.zhengjie.modules.reply.repository.ReplyRepository;
import me.zhengjie.modules.reply.service.mapper.ReplyMapper;
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
@CacheConfig(cacheNames = "reply")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ReplyQueryService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ReplyDTO reply, Pageable pageable){
        Page<Reply> page = replyRepository.findAll(new Spec(reply),pageable);
        return PageUtil.toPage(page.map(replyMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ReplyDTO reply){
        return replyMapper.toDto(replyRepository.findAll(new Spec(reply)));
    }

    class Spec implements Specification<Reply> {

        private ReplyDTO reply;

        public Spec(ReplyDTO reply){
            this.reply = reply;
        }

        @Override
        public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(reply.getId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("id").as(Long.class),reply.getId()));
            }
            if(!ObjectUtils.isEmpty(reply.getText())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("text").as(String.class),"%"+reply.getText()+"%"));
            }
            if(!ObjectUtils.isEmpty(reply.getReply())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("reply").as(String.class),"%"+reply.getReply()+"%"));
            }
            if(!ObjectUtils.isEmpty(reply.getStuid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("stuid").as(String.class),reply.getStuid()));
            }
            if(!ObjectUtils.isEmpty(reply.getStuname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("stuname").as(String.class),"%"+reply.getStuname()+"%"));
            }
            if(!ObjectUtils.isEmpty(reply.getTeaid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("teaid").as(Long.class),reply.getTeaid()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}