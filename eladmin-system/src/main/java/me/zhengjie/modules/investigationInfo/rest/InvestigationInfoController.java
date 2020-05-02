package me.zhengjie.modules.investigationInfo.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.investigationInfo.domain.InvestigationInfo;
import me.zhengjie.modules.investigationInfo.service.InvestigationInfoService;
import me.zhengjie.modules.investigationInfo.service.dto.InvestigationInfoDTO;
import me.zhengjie.modules.investigationInfo.service.query.InvestigationInfoQueryService;
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
public class InvestigationInfoController {

    @Autowired
    private InvestigationInfoService investigationInfoService;

    @Autowired
    private InvestigationInfoQueryService investigationInfoQueryService;

    private static final String ENTITY_NAME = "investigationInfo";

    @Log("分页查询InvestigationInfo")
    @GetMapping(value = "/investigationInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getInvestigationInfos(InvestigationInfoDTO resources, Pageable pageable){
        return new ResponseEntity(investigationInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部InvestigationInfo")
    @GetMapping(value = "/queryAll/investigationInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryInvestigationInfos(InvestigationInfoDTO resources){
        return new ResponseEntity(investigationInfoQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/investigationInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(investigationInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增InvestigationInfo")
    @PostMapping(value = "/investigationInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody InvestigationInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(investigationInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改InvestigationInfo")
    @PutMapping(value = "/investigationInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody InvestigationInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        investigationInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除InvestigationInfo")
    @DeleteMapping(value = "/investigationInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        investigationInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}