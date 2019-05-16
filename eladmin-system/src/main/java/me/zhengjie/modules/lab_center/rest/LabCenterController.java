package me.zhengjie.modules.lab_center.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.lab_center.domain.LabCenter;
import me.zhengjie.modules.lab_center.service.LabCenterService;
import me.zhengjie.modules.lab_center.service.dto.LabCenterDTO;
import me.zhengjie.modules.lab_center.service.query.LabCenterQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-16
*/
@RestController
@RequestMapping("api")
public class LabCenterController {

    @Autowired
    private LabCenterService labCenterService;

    @Autowired
    private LabCenterQueryService labCenterQueryService;

    private static final String ENTITY_NAME = "labCenter";

    @Log("查询LabCenter")
    @GetMapping(value = "/labCenter")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getLabCenters(LabCenterDTO resources, Pageable pageable){
        return new ResponseEntity(labCenterQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增LabCenter")
    @PostMapping(value = "/labCenter")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody LabCenter resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(labCenterService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改LabCenter")
    @PutMapping(value = "/labCenter")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody LabCenter resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        labCenterService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除LabCenter")
    @DeleteMapping(value = "/labCenter/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        labCenterService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}