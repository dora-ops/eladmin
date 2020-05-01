package me.zhengjie.modules.regInfo.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.regInfo.domain.RegInfo;
import me.zhengjie.modules.regInfo.service.RegInfoService;
import me.zhengjie.modules.regInfo.service.dto.RegInfoDTO;
import me.zhengjie.modules.regInfo.service.query.RegInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-04-30
*/
@RestController
@RequestMapping("api")
public class RegInfoController {

    @Autowired
    private RegInfoService regInfoService;

    @Autowired
    private RegInfoQueryService regInfoQueryService;

    private static final String ENTITY_NAME = "regInfo";

    @Log("分页查询RegInfo")
    @GetMapping(value = "/regInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getRegInfos(RegInfoDTO resources, Pageable pageable){
        return new ResponseEntity(regInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部RegInfo")
    @GetMapping(value = "/queryAll/regInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryRegInfos(RegInfoDTO resources){
        return new ResponseEntity(regInfoQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/regInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(regInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增RegInfo")
    @PostMapping(value = "/regInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody RegInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(regInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改RegInfo")
    @PutMapping(value = "/regInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody RegInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        regInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除RegInfo")
    @DeleteMapping(value = "/regInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        regInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}