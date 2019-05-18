package me.zhengjie.modules.spider_info.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_info.service.SpiderInfoService;
import me.zhengjie.modules.spider_info.service.dto.SpiderInfoDTO;
import me.zhengjie.modules.spider_info.service.query.SpiderInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-18
*/
@RestController
@RequestMapping("api")
public class SpiderInfoController {

    @Autowired
    private SpiderInfoService spiderInfoService;

    @Autowired
    private SpiderInfoQueryService spiderInfoQueryService;

    private static final String ENTITY_NAME = "spiderInfo";

    @Log("查询SpiderInfo")
    @GetMapping(value = "/spiderInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getSpiderInfos(SpiderInfoDTO resources, Pageable pageable){
        return new ResponseEntity(spiderInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增SpiderInfo")
    @PostMapping(value = "/spiderInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody SpiderInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(spiderInfoService.create(resources),HttpStatus.CREATED);
    }


    @Log("修改SpiderInfo")
    @PutMapping(value = "/spiderInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody SpiderInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        spiderInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除SpiderInfo")
    @DeleteMapping(value = "/spiderInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        spiderInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}