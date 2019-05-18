package me.zhengjie.modules.spider_task.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_task.domain.SpiderTask;
import me.zhengjie.modules.spider_task.service.SpiderTaskService;
import me.zhengjie.modules.spider_task.service.dto.SpiderTaskDTO;
import me.zhengjie.modules.spider_task.service.query.SpiderTaskQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-18
*/
@RestController
@RequestMapping("api")
public class SpiderTaskController {

    @Autowired
    private SpiderTaskService spiderTaskService;

    @Autowired
    private SpiderTaskQueryService spiderTaskQueryService;

    private static final String ENTITY_NAME = "spiderTask";

    @Log("查询SpiderTask")
    @GetMapping(value = "/spiderTask")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getSpiderTasks(SpiderTaskDTO resources, Pageable pageable){
        return new ResponseEntity(spiderTaskQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增SpiderTask")
    @PostMapping(value = "/spiderTask")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody SpiderTask resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(spiderTaskService.create(resources),HttpStatus.CREATED);
    }

    @Log("启动爬虫")
    @PostMapping(value = "/start/{code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity start(@PathVariable String code,@Validated @RequestBody SpiderTask resources){

        return new ResponseEntity(spiderTaskService.start(code,resources),HttpStatus.CREATED);
    }


    @Log("修改SpiderTask")
    @PutMapping(value = "/spiderTask")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody SpiderTask resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        spiderTaskService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除SpiderTask")
    @DeleteMapping(value = "/spiderTask/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        spiderTaskService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}