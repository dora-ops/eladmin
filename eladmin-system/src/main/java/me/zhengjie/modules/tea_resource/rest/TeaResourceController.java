package me.zhengjie.modules.tea_resource.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.tea_resource.domain.TeaResource;
import me.zhengjie.modules.tea_resource.service.TeaResourceService;
import me.zhengjie.modules.tea_resource.service.dto.TeaResourceDTO;
import me.zhengjie.modules.tea_resource.service.query.TeaResourceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-05-15
*/
@RestController
@RequestMapping("api")
public class TeaResourceController {

    @Autowired
    private TeaResourceService teaResourceService;

    @Autowired
    private TeaResourceQueryService teaResourceQueryService;

    private static final String ENTITY_NAME = "teaResource";

    @Log("分页查询TeaResource")
    @GetMapping(value = "/teaResource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTeaResources(TeaResourceDTO resources, Pageable pageable){
        return new ResponseEntity(teaResourceQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部TeaResource")
    @GetMapping(value = "/queryAll/teaResource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryTeaResources(TeaResourceDTO resources){
        return new ResponseEntity(teaResourceQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/teaResource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(teaResourceService.findById(id),HttpStatus.OK);
    }

    @Log("新增TeaResource")
    @PostMapping(value = "/teaResource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody TeaResource resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(teaResourceService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改TeaResource")
    @PutMapping(value = "/teaResource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody TeaResource resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        teaResourceService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除TeaResource")
    @DeleteMapping(value = "/teaResource/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        teaResourceService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}