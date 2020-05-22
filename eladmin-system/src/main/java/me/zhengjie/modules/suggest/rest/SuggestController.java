package me.zhengjie.modules.suggest.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.suggest.domain.Suggest;
import me.zhengjie.modules.suggest.service.SuggestService;
import me.zhengjie.modules.suggest.service.dto.SuggestDTO;
import me.zhengjie.modules.suggest.service.query.SuggestQueryService;
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
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    @Autowired
    private SuggestQueryService suggestQueryService;

    private static final String ENTITY_NAME = "suggest";

    @Log("分页查询Suggest")
    @GetMapping(value = "/suggest")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getSuggests(SuggestDTO resources, Pageable pageable){
        return new ResponseEntity(suggestQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Suggest")
    @GetMapping(value = "/queryAll/suggest")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity querySuggests(SuggestDTO resources){
        return new ResponseEntity(suggestQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/suggest")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(suggestService.findById(id),HttpStatus.OK);
    }

    @Log("新增Suggest")
    @PostMapping(value = "/suggest")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Suggest resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(suggestService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Suggest")
    @PutMapping(value = "/suggest")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Suggest resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        suggestService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Suggest")
    @DeleteMapping(value = "/suggest/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        suggestService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}