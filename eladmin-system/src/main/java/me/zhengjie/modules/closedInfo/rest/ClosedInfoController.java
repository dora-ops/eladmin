package me.zhengjie.modules.closedInfo.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.closedInfo.domain.ClosedInfo;
import me.zhengjie.modules.closedInfo.service.ClosedInfoService;
import me.zhengjie.modules.closedInfo.service.dto.ClosedInfoDTO;
import me.zhengjie.modules.closedInfo.service.query.ClosedInfoQueryService;
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
public class ClosedInfoController {

    @Autowired
    private ClosedInfoService closedInfoService;

    @Autowired
    private ClosedInfoQueryService closedInfoQueryService;

    private static final String ENTITY_NAME = "closedInfo";

    @Log("分页查询ClosedInfo")
    @GetMapping(value = "/closedInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getClosedInfos(ClosedInfoDTO resources, Pageable pageable){
        return new ResponseEntity(closedInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部ClosedInfo")
    @GetMapping(value = "/queryAll/closedInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryClosedInfos(ClosedInfoDTO resources){
        return new ResponseEntity(closedInfoQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/closedInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(closedInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增ClosedInfo")
    @PostMapping(value = "/closedInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody ClosedInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(closedInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ClosedInfo")
    @PutMapping(value = "/closedInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody ClosedInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        closedInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ClosedInfo")
    @DeleteMapping(value = "/closedInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        closedInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}