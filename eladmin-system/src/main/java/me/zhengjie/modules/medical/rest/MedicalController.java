package me.zhengjie.modules.medical.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.medical.domain.Medical;
import me.zhengjie.modules.medical.service.MedicalService;
import me.zhengjie.modules.medical.service.dto.MedicalDTO;
import me.zhengjie.modules.medical.service.query.MedicalQueryService;
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
public class MedicalController {

    @Autowired
    private MedicalService medicalService;

    @Autowired
    private MedicalQueryService medicalQueryService;

    private static final String ENTITY_NAME = "medical";

    @Log("查询Medical")
    @GetMapping(value = "/medical")
    @PreAuthorize("hasAnyRole('ADMIN','medical_select')")
    public ResponseEntity getMedicals(MedicalDTO resources, Pageable pageable){
        return new ResponseEntity(medicalQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Medical")
    @PostMapping(value = "/medical")
    @PreAuthorize("hasAnyRole('ADMIN','medical_create')")
    public ResponseEntity create(@Validated @RequestBody Medical resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(medicalService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Medical")
    @PutMapping(value = "/medical")
    @PreAuthorize("hasAnyRole('ADMIN','medical_edit')")
    public ResponseEntity update(@Validated @RequestBody Medical resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        medicalService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Medical")
    @DeleteMapping(value = "/medical/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','medical_delete')")
    public ResponseEntity delete(@PathVariable Long id){
        medicalService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}