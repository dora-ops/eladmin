package me.zhengjie.modules.handleInfo.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.handleInfo.domain.HandleInfo;
import me.zhengjie.modules.handleInfo.service.HandleInfoService;
import me.zhengjie.modules.handleInfo.service.dto.HandleInfoDTO;
import me.zhengjie.modules.handleInfo.service.query.HandleInfoQueryService;
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
public class HandleInfoController {

    @Autowired
    private HandleInfoService handleInfoService;

    @Autowired
    private HandleInfoQueryService handleInfoQueryService;

    private static final String ENTITY_NAME = "handleInfo";

    @Log("分页查询HandleInfo")
    @GetMapping(value = "/handleInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getHandleInfos(HandleInfoDTO resources, Pageable pageable){
        return new ResponseEntity(handleInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部HandleInfo")
    @GetMapping(value = "/queryAll/handleInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryHandleInfos(HandleInfoDTO resources){
        return new ResponseEntity(handleInfoQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/handleInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(handleInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增HandleInfo")
    @PostMapping(value = "/handleInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody HandleInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(handleInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改HandleInfo")
    @PutMapping(value = "/handleInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody HandleInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        handleInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除HandleInfo")
    @DeleteMapping(value = "/handleInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        handleInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}