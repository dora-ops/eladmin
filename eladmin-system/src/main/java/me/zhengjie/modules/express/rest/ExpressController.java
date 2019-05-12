package me.zhengjie.modules.express.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.express.domain.Express;
import me.zhengjie.modules.express.service.ExpressService;
import me.zhengjie.modules.express.service.dto.ExpressDTO;
import me.zhengjie.modules.express.service.query.ExpressQueryService;
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
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    @Autowired
    private ExpressQueryService expressQueryService;

    private static final String ENTITY_NAME = "express";

    @Log("查询Express")
    @GetMapping(value = "/express")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_SELECT')")
    public ResponseEntity getExpresss(ExpressDTO resources, Pageable pageable){
        return new ResponseEntity(expressQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Express")
    @PostMapping(value = "/express")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Express resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(expressService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Express")
    @PutMapping(value = "/express")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Express resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        expressService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Express")
    @DeleteMapping(value = "/express/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JOB_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        expressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}