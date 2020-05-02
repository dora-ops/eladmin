package me.zhengjie.modules.awardInfo.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.awardInfo.domain.AwardInfo;
import me.zhengjie.modules.awardInfo.service.AwardInfoService;
import me.zhengjie.modules.awardInfo.service.dto.AwardInfoDTO;
import me.zhengjie.modules.awardInfo.service.query.AwardInfoQueryService;
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
public class AwardInfoController {

    @Autowired
    private AwardInfoService awardInfoService;

    @Autowired
    private AwardInfoQueryService awardInfoQueryService;

    private static final String ENTITY_NAME = "awardInfo";

    @Log("分页查询AwardInfo")
    @GetMapping(value = "/awardInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getAwardInfos(AwardInfoDTO resources, Pageable pageable){
        return new ResponseEntity(awardInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部AwardInfo")
    @GetMapping(value = "/queryAll/awardInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryAwardInfos(AwardInfoDTO resources){
        return new ResponseEntity(awardInfoQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/awardInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(awardInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增AwardInfo")
    @PostMapping(value = "/awardInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody AwardInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(awardInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AwardInfo")
    @PutMapping(value = "/awardInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody AwardInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        awardInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AwardInfo")
    @DeleteMapping(value = "/awardInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        awardInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}