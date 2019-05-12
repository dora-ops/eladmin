package me.zhengjie.modules.topic.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.topic.domain.Topic;
import me.zhengjie.modules.topic.service.TopicService;
import me.zhengjie.modules.topic.service.dto.TopicDTO;
import me.zhengjie.modules.topic.service.query.TopicQueryService;
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
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicQueryService topicQueryService;

    private static final String ENTITY_NAME = "topic";

    @Log("查询Topic")
    @GetMapping(value = "/topic")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTopics(TopicDTO resources, Pageable pageable){
        return new ResponseEntity(topicQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Topic")
    @PostMapping(value = "/topic")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Topic resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(topicService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Topic")
    @PutMapping(value = "/topic")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Topic resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        topicService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Topic")
    @DeleteMapping(value = "/topic/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        topicService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}