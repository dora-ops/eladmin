package me.zhengjie.modules.article.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.service.ArticleService;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import me.zhengjie.modules.article.service.query.ArticleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-19
*/
@RestController
@RequestMapping("api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleQueryService articleQueryService;

    private static final String ENTITY_NAME = "article";

    @Log("查询Article")
    @GetMapping(value = "/article")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getArticles(ArticleDTO resources, Pageable pageable){
        return new ResponseEntity(articleQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Article")
    @PostMapping(value = "/article")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Article resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(articleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Article")
    @PutMapping(value = "/article")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Article resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        articleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Article")
    @DeleteMapping(value = "/article/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        articleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}