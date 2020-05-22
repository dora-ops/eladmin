package me.zhengjie.modules.reply.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.reply.domain.Reply;
import me.zhengjie.modules.reply.service.ReplyService;
import me.zhengjie.modules.reply.service.dto.ReplyDTO;
import me.zhengjie.modules.reply.service.query.ReplyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-05-21
*/
@RestController
@RequestMapping("api")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyQueryService replyQueryService;

    private static final String ENTITY_NAME = "reply";

    @Log("分页查询Reply")
    @GetMapping(value = "/reply")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getReplys(ReplyDTO resources, Pageable pageable){
        return new ResponseEntity(replyQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Reply")
    @GetMapping(value = "/queryAll/reply")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryReplys(ReplyDTO resources){
        return new ResponseEntity(replyQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/reply")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(replyService.findById(id),HttpStatus.OK);
    }

    @Log("新增Reply")
    @PostMapping(value = "/reply")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Reply resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(replyService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Reply")
    @PutMapping(value = "/reply")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Reply resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        replyService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Reply")
    @DeleteMapping(value = "/reply/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        replyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}