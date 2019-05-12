package me.zhengjie.modules.notice.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.notice.domain.Notice;
import me.zhengjie.modules.notice.service.NoticeService;
import me.zhengjie.modules.notice.service.dto.NoticeDTO;
import me.zhengjie.modules.notice.service.query.NoticeQueryService;
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
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeQueryService noticeQueryService;

    private static final String ENTITY_NAME = "notice";

    @Log("查询Notice")
    @GetMapping(value = "/notice")
    @PreAuthorize("hasAnyRole('ADMIN','notice_select')")
    public ResponseEntity getNotices(NoticeDTO resources, Pageable pageable){
        return new ResponseEntity(noticeQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Notice")
    @PostMapping(value = "/notice")
    @PreAuthorize("hasAnyRole('ADMIN','notice_create')")
    public ResponseEntity create(@Validated @RequestBody Notice resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(noticeService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Notice")
    @PutMapping(value = "/notice")
    @PreAuthorize("hasAnyRole('ADMIN','notice_edit')")
    public ResponseEntity update(@Validated @RequestBody Notice resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        noticeService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Notice")
    @DeleteMapping(value = "/notice/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','notice_delete')")
    public ResponseEntity delete(@PathVariable Long id){
        noticeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}