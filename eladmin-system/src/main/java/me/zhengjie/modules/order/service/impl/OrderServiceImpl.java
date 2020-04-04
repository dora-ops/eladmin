package me.zhengjie.modules.order.service.impl;

import me.zhengjie.modules.order.domain.Order;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.order.repository.OrderRepository;
import me.zhengjie.modules.order.service.OrderService;
import me.zhengjie.modules.order.service.dto.OrderDTO;
import me.zhengjie.modules.order.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-04-04
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        ValidationUtil.isNull(order,"Order","id",id);
        return orderMapper.toDto(order.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO create(Order resources) {
        return orderMapper.toDto(orderRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Order resources) {
        Optional<Order> optionalOrder = orderRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalOrder,"Order","id",resources.getId());

        Order order = optionalOrder.get();
        resources.setId(order.getId());
        orderRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}