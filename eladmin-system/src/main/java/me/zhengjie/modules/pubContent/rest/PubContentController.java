package me.zhengjie.modules.pubContent.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.pubContent.domain.PubContent;
import me.zhengjie.modules.pubContent.service.PubContentService;
import me.zhengjie.modules.pubContent.service.dto.PubContentDTO;
import me.zhengjie.modules.pubContent.service.query.PubContentQueryService;
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
public class PubContentController {

    @Autowired
    private PubContentService pubContentService;

    @Autowired
    private PubContentQueryService pubContentQueryService;

    private static final String ENTITY_NAME = "pubContent";

    @Log("分页查询PubContent")
    @GetMapping(value = "/pubContent")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPubContents(PubContentDTO resources, Pageable pageable){
        return new ResponseEntity(pubContentQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部PubContent")
    @GetMapping(value = "/queryAll/pubContent")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryPubContents(PubContentDTO resources){
        return new ResponseEntity(pubContentQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/pubContent")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(pubContentService.findById(id),HttpStatus.OK);
    }

    @Log("新增PubContent")
    @PostMapping(value = "/pubContent")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody PubContent resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(pubContentService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改PubContent")
    @PutMapping(value = "/pubContent")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody PubContent resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        pubContentService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除PubContent")
    @DeleteMapping(value = "/pubContent/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        pubContentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}