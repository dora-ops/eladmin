package me.zhengjie.modules.people.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.people.domain.People;
import me.zhengjie.modules.people.service.dto.PeopleDTO;
import me.zhengjie.modules.people.repository.PeopleRepository;
import me.zhengjie.modules.people.service.mapper.PeopleMapper;
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
@CacheConfig(cacheNames = "people")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PeopleQueryService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleMapper peopleMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PeopleDTO people, Pageable pageable){
        Page<People> page = peopleRepository.findAll(new Spec(people),pageable);
        return PageUtil.toPage(page.map(peopleMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PeopleDTO people){
        return peopleMapper.toDto(peopleRepository.findAll(new Spec(people)));
    }

    class Spec implements Specification<People> {

        private PeopleDTO people;

        public Spec(PeopleDTO people){
            this.people = people;
        }

        @Override
        public Predicate toPredicate(Root<People> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(people.getRole())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("role").as(String.class),people.getRole()));
            }
            if(!ObjectUtils.isEmpty(people.getUsername())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("username").as(String.class),"%"+people.getUsername()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}