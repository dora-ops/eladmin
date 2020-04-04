package me.zhengjie.modules.order.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.order.domain.Order;
import me.zhengjie.modules.order.service.dto.OrderDTO;
import me.zhengjie.modules.order.repository.OrderRepository;
import me.zhengjie.modules.order.service.mapper.OrderMapper;
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
@CacheConfig(cacheNames = "order")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderQueryService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(OrderDTO order, Pageable pageable){
        Page<Order> page = orderRepository.findAll(new Spec(order),pageable);
        return PageUtil.toPage(page.map(orderMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(OrderDTO order){
        return orderMapper.toDto(orderRepository.findAll(new Spec(order)));
    }

    class Spec implements Specification<Order> {

        private OrderDTO order;

        public Spec(OrderDTO order){
            this.order = order;
        }

        @Override
        public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(order.getUid())){
                /**
                * 精确
                */
                list.add(cb.equal(root.get("uid").as(String.class),order.getUid()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}