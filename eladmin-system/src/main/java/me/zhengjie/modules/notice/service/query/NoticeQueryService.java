package me.zhengjie.modules.notice.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.notice.domain.Notice;
import me.zhengjie.modules.notice.service.dto.NoticeDTO;
import me.zhengjie.modules.notice.repository.NoticeRepository;
import me.zhengjie.modules.notice.service.mapper.NoticeMapper;
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
@CacheConfig(cacheNames = "notice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NoticeQueryService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(NoticeDTO notice, Pageable pageable){
        Page<Notice> page = noticeRepository.findAll(new Spec(notice),pageable);
        return PageUtil.toPage(page.map(noticeMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(NoticeDTO notice){
        return noticeMapper.toDto(noticeRepository.findAll(new Spec(notice)));
    }

    class Spec implements Specification<Notice> {

        private NoticeDTO notice;

        public Spec(NoticeDTO notice){
            this.notice = notice;
        }

        @Override
        public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(notice.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+notice.getTitle()+"%"));
            }
            if(!ObjectUtils.isEmpty(notice.getType())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("type").as(String.class),notice.getType()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}