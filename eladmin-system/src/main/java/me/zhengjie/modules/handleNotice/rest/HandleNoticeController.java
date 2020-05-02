package me.zhengjie.modules.handleNotice.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.handleNotice.domain.HandleNotice;
import me.zhengjie.modules.handleNotice.service.HandleNoticeService;
import me.zhengjie.modules.handleNotice.service.dto.HandleNoticeDTO;
import me.zhengjie.modules.handleNotice.service.query.HandleNoticeQueryService;
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
public class HandleNoticeController {

    @Autowired
    private HandleNoticeService handleNoticeService;

    @Autowired
    private HandleNoticeQueryService handleNoticeQueryService;

    private static final String ENTITY_NAME = "handleNotice";

    @Log("分页查询HandleNotice")
    @GetMapping(value = "/handleNotice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getHandleNotices(HandleNoticeDTO resources, Pageable pageable){
        return new ResponseEntity(handleNoticeQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部HandleNotice")
    @GetMapping(value = "/queryAll/handleNotice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryHandleNotices(HandleNoticeDTO resources){
        return new ResponseEntity(handleNoticeQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/handleNotice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(handleNoticeService.findById(id),HttpStatus.OK);
    }

    @Log("新增HandleNotice")
    @PostMapping(value = "/handleNotice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody HandleNotice resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(handleNoticeService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改HandleNotice")
    @PutMapping(value = "/handleNotice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody HandleNotice resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        handleNoticeService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除HandleNotice")
    @DeleteMapping(value = "/handleNotice/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        handleNoticeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}