package me.zhengjie.modules.pro_topic.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.pro_topic.domain.ProTopic;
import me.zhengjie.modules.pro_topic.service.ProTopicService;
import me.zhengjie.modules.pro_topic.service.dto.ProTopicDTO;
import me.zhengjie.modules.pro_topic.service.query.ProTopicQueryService;
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
public class ProTopicController {

    @Autowired
    private ProTopicService proTopicService;

    @Autowired
    private ProTopicQueryService proTopicQueryService;

    private static final String ENTITY_NAME = "proTopic";

    @Log("查询ProTopic")
    @GetMapping(value = "/proTopic")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_SELECT')")
    public ResponseEntity getProTopics(ProTopicDTO resources, Pageable pageable){
        return new ResponseEntity(proTopicQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增ProTopic")
    @PostMapping(value = "/proTopic")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_UPLOAD')")
    public ResponseEntity create(@Validated @RequestBody ProTopic resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(proTopicService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ProTopic")
    @PutMapping(value = "/proTopic")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_UPDATE')")
    public ResponseEntity update(@Validated @RequestBody ProTopic resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        proTopicService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ProTopic")
    @DeleteMapping(value = "/proTopic/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        proTopicService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}