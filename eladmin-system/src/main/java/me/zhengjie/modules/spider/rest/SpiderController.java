package me.zhengjie.modules.spider.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.spider.domain.Spider;
import me.zhengjie.modules.spider.service.SpiderService;
import me.zhengjie.modules.spider.service.dto.SpiderDTO;
import me.zhengjie.modules.spider.service.query.SpiderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-19
*/
@RestController
@RequestMapping("api")
public class SpiderController {

    @Autowired
    private SpiderService spiderService;

    @Autowired
    private SpiderQueryService spiderQueryService;

    private static final String ENTITY_NAME = "spider";

    @Log("查询Spider")
    @GetMapping(value = "/spider")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getSpiders(SpiderDTO resources, Pageable pageable){
        return new ResponseEntity(spiderQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Spider")
    @PostMapping(value = "/spider")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Spider resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(spiderService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Spider")
    @PutMapping(value = "/spider")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Spider resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        spiderService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Spider")
    @DeleteMapping(value = "/spider/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        spiderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}