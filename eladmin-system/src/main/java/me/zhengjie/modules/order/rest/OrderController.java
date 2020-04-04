package me.zhengjie.modules.order.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.order.domain.Order;
import me.zhengjie.modules.order.service.OrderService;
import me.zhengjie.modules.order.service.dto.OrderDTO;
import me.zhengjie.modules.order.service.query.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-04-04
*/
@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderQueryService orderQueryService;

    private static final String ENTITY_NAME = "order";

    @Log("分页查询Order")
    @GetMapping(value = "/order")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getOrders(OrderDTO resources, Pageable pageable){
        return new ResponseEntity(orderQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Order")
    @GetMapping(value = "/queryAll/order")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryOrders(OrderDTO resources){
        return new ResponseEntity(orderQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/order")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(orderService.findById(id),HttpStatus.OK);
    }

    @Log("新增Order")
    @PostMapping(value = "/order")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Order resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(orderService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Order")
    @PutMapping(value = "/order")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Order resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        orderService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Order")
    @DeleteMapping(value = "/order/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        orderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}