package me.zhengjie.modules.caseInfo.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.caseInfo.domain.CaseInfo;
import me.zhengjie.modules.caseInfo.service.CaseInfoService;
import me.zhengjie.modules.caseInfo.service.dto.CaseInfoDTO;
import me.zhengjie.modules.caseInfo.service.query.CaseInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-05-02
*/
@RestController
@RequestMapping("api")
public class CaseInfoController {

    @Autowired
    private CaseInfoService caseInfoService;

    @Autowired
    private CaseInfoQueryService caseInfoQueryService;

    private static final String ENTITY_NAME = "caseInfo";

    @Log("分页查询CaseInfo")
    @GetMapping(value = "/caseInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCaseInfos(CaseInfoDTO resources, Pageable pageable){
        return new ResponseEntity(caseInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部CaseInfo")
    @GetMapping(value = "/queryAll/caseInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryCaseInfos(CaseInfoDTO resources){
        return new ResponseEntity(caseInfoQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/caseInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(caseInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增CaseInfo")
    @PostMapping(value = "/caseInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody CaseInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(caseInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改CaseInfo")
    @PutMapping(value = "/caseInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody CaseInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        caseInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除CaseInfo")
    @DeleteMapping(value = "/caseInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        caseInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}