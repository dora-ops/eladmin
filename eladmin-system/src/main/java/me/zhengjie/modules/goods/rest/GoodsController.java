package me.zhengjie.modules.goods.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.goods.domain.Goods;
import me.zhengjie.modules.goods.service.GoodsService;
import me.zhengjie.modules.goods.service.dto.GoodsDTO;
import me.zhengjie.modules.goods.service.query.GoodsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-12
*/
@RestController
@RequestMapping("api")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsQueryService goodsQueryService;

    private static final String ENTITY_NAME = "goods";

    @Log("查询Goods")
    @GetMapping(value = "/goods")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_SELECT')")
    public ResponseEntity getGoodss(GoodsDTO resources, Pageable pageable){
        return new ResponseEntity(goodsQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Goods")
    @PostMapping(value = "/goods")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Goods resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(goodsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Goods")
    @PutMapping(value = "/goods")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Goods resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        goodsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Goods")
    @DeleteMapping(value = "/goods/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        goodsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}