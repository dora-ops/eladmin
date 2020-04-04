package me.zhengjie.modules.order.repository;

import me.zhengjie.modules.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-04
*/
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor {
}