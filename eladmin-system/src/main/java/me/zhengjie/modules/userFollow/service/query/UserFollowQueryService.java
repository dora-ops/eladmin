package me.zhengjie.modules.userFollow.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.userFollow.domain.UserFollow;
import me.zhengjie.modules.userFollow.service.dto.UserFollowDTO;
import me.zhengjie.modules.userFollow.repository.UserFollowRepository;
import me.zhengjie.modules.userFollow.service.mapper.UserFollowMapper;
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
@CacheConfig(cacheNames = "userFollow")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserFollowQueryService {

    @Autowired
    private UserFollowRepository userFollowRepository;

    @Autowired
    private UserFollowMapper userFollowMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UserFollowDTO userFollow, Pageable pageable){
        Page<UserFollow> page = userFollowRepository.findAll(new Spec(userFollow),pageable);
        return PageUtil.toPage(page.map(userFollowMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UserFollowDTO userFollow){
        return userFollowMapper.toDto(userFollowRepository.findAll(new Spec(userFollow)));
    }

    class Spec implements Specification<UserFollow> {

        private UserFollowDTO userFollow;

        public Spec(UserFollowDTO userFollow){
            this.userFollow = userFollow;
        }

        @Override
        public Predicate toPredicate(Root<UserFollow> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(userFollow.getCusId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("cus_id").as(Integer.class),userFollow.getCusId()));
            }
            if(!ObjectUtils.isEmpty(userFollow.getCusFollowId())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("cus_follow_id").as(Integer.class),userFollow.getCusFollowId()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}