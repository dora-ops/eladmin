package me.zhengjie.modules.commentlist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.commentlist.domain.Commentlist;
import me.zhengjie.modules.commentlist.service.CommentlistService;
import me.zhengjie.modules.commentlist.service.dto.CommentlistDTO;
import me.zhengjie.modules.commentlist.service.query.CommentlistQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author y
* @date 2021-05-15
*/
@RestController
@RequestMapping("api")
public class CommentlistController {

    @Autowired
    private CommentlistService commentlistService;

    @Autowired
    private CommentlistQueryService commentlistQueryService;

    private static final String ENTITY_NAME = "commentlist";

    @Log("分页查询Commentlist")
    @GetMapping(value = "/commentlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCommentlists(CommentlistDTO resources, Pageable pageable){
        return new ResponseEntity(commentlistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Commentlist")
    @GetMapping(value = "/queryAll/commentlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryCommentlists(CommentlistDTO resources){
        return new ResponseEntity(commentlistQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/commentlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(commentlistService.findById(id),HttpStatus.OK);
    }

    @Log("新增Commentlist")
    @PostMapping(value = "/commentlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Commentlist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(commentlistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Commentlist")
    @PutMapping(value = "/commentlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Commentlist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        commentlistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Commentlist")
    @DeleteMapping(value = "/commentlist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        commentlistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}