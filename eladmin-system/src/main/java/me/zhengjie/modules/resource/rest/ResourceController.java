package me.zhengjie.modules.resource.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.resource.domain.Resource;
import me.zhengjie.modules.resource.service.ResourceService;
import me.zhengjie.modules.resource.service.dto.ResourceDTO;
import me.zhengjie.modules.resource.service.query.ResourceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author y
* @date 2021-05-15
*/
@RestController
@RequestMapping("api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceQueryService resourceQueryService;

    private static final String ENTITY_NAME = "resource";

    @Log("分页查询Resource")
    @GetMapping(value = "/resource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getResources(ResourceDTO resources, Pageable pageable){
        return new ResponseEntity(resourceQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Resource")
    @GetMapping(value = "/queryAll/resource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryResources(ResourceDTO resources){
        return new ResponseEntity(resourceQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/resource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(resourceService.findById(id),HttpStatus.OK);
    }

    @Log("新增Resource")
    @PostMapping(value = "/resource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Resource resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(resourceService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Resource")
    @PutMapping(value = "/resource")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Resource resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        resourceService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Resource")
    @DeleteMapping(value = "/resource/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        resourceService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}