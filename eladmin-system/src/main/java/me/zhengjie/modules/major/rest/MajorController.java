package me.zhengjie.modules.major.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.major.domain.Major;
import me.zhengjie.modules.major.service.MajorService;
import me.zhengjie.modules.major.service.dto.MajorDTO;
import me.zhengjie.modules.major.service.query.MajorQueryService;
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
public class MajorController {

    @Autowired
    private MajorService majorService;

    @Autowired
    private MajorQueryService majorQueryService;

    private static final String ENTITY_NAME = "major";

    @Log("查询Major")
    @GetMapping(value = "/major")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getMajors(MajorDTO resources, Pageable pageable){
        return new ResponseEntity(majorQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Major")
    @PostMapping(value = "/major")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Major resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(majorService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Major")
    @PutMapping(value = "/major")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Major resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        majorService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Major")
    @DeleteMapping(value = "/major/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        majorService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}