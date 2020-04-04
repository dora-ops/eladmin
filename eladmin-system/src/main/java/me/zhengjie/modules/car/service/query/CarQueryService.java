package me.zhengjie.modules.car.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.car.domain.Car;
import me.zhengjie.modules.car.service.dto.CarDTO;
import me.zhengjie.modules.car.repository.CarRepository;
import me.zhengjie.modules.car.service.mapper.CarMapper;
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
@CacheConfig(cacheNames = "car")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CarQueryService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CarDTO car, Pageable pageable){
        Page<Car> page = carRepository.findAll(new Spec(car),pageable);
        return PageUtil.toPage(page.map(carMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CarDTO car){
        return carMapper.toDto(carRepository.findAll(new Spec(car)));
    }

    class Spec implements Specification<Car> {

        private CarDTO car;

        public Spec(CarDTO car){
            this.car = car;
        }

        @Override
        public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(car.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+car.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}