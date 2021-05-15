package me.zhengjie.modules.person.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.person.domain.Person;
import me.zhengjie.modules.person.service.dto.PersonDTO;
import me.zhengjie.modules.person.repository.PersonRepository;
import me.zhengjie.modules.person.service.mapper.PersonMapper;
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
@CacheConfig(cacheNames = "person")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PersonQueryService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PersonDTO person, Pageable pageable){
        Page<Person> page = personRepository.findAll(new Spec(person),pageable);
        return PageUtil.toPage(page.map(personMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PersonDTO person){
        return personMapper.toDto(personRepository.findAll(new Spec(person)));
    }

    class Spec implements Specification<Person> {

        private PersonDTO person;

        public Spec(PersonDTO person){
            this.person = person;
        }

        @Override
        public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(person.getMobile())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("mobile").as(String.class),"%"+person.getMobile()+"%"));
            }
            if(!ObjectUtils.isEmpty(person.getNickname())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("nickname").as(String.class),"%"+person.getNickname()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}