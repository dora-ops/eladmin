package me.zhengjie.modules.notice.service.query;

import me.zhengjie.domain.vo.TableInfo;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.Timestamp;
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

    @PersistenceContext
    private EntityManager em;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(NoticeDTO notice, Pageable pageable){
        StringBuilder sql = new StringBuilder("select * from notice where 1=1 ");
        StringBuilder sql1 = new StringBuilder("select count(*) from notice where 1=1 ");
        if(!ObjectUtils.isEmpty(notice.getTitle())){
            sql.append("and title like '%"+notice.getTitle()+"%' ");
            sql1.append("and title like '%"+notice.getTitle()+"%' ");
        }
        if(!ObjectUtils.isEmpty(notice.getSend())&&!ObjectUtils.isEmpty(notice.getReceive())){
            sql.append("and ( send = '"+notice.getSend()+"' or receive = '"+notice.getReceive()+"')");
            sql1.append("and ( send = '"+notice.getSend()+"' or receive = '"+notice.getReceive()+"')");
        }
        Query query = em.createNativeQuery(sql.toString());
        List<Object[]> result = query.getResultList();
        List<Notice> tableInfos = new ArrayList<>();
        for (Object[] obj : result) {
            BigInteger id= (BigInteger) obj[0];
            tableInfos.add(new Notice(id.longValue(),(String)(obj[1]),(String)(obj[2]),(String)(obj[3]),(String)(obj[4]), (Timestamp)(obj[5])));
        }
        Query query1 = em.createNativeQuery(sql1.toString());
        Object totalElements = query1.getSingleResult();

//        Page<Notice> page = noticeRepository.findAll(new Spec(notice),pageable);
        return  PageUtil.toPage(tableInfos,totalElements);
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
            List<Predicate> orlist = new ArrayList<Predicate>();
            if(!ObjectUtils.isEmpty(notice.getTitle())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("title").as(String.class),"%"+notice.getTitle()+"%"));
            }
            if(!ObjectUtils.isEmpty(notice.getSend())){
                /**
                 * 模糊
                 */
                orlist.add(cb.equal(root.get("send").as(String.class),notice.getSend()));
            }
            if(!ObjectUtils.isEmpty(notice.getReceive())){
                /**
                 * 模糊
                 */
                orlist.add(cb.equal(root.get("receive").as(String.class),notice.getReceive()));
            }
                Predicate[] p = new Predicate[list.size()];
                Predicate[] p2 = new Predicate[orlist.size()];
                return criteriaQuery.where(cb.or(list.toArray(p2)),cb.and(list.toArray(p))).getRestriction() ;
        }
    }
}