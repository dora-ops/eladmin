package me.zhengjie.modules.spider_log.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.spider_log.domain.SpiderLog;
import me.zhengjie.modules.spider_log.service.SpiderLogService;
import me.zhengjie.modules.spider_log.service.dto.SpiderLogDTO;
import me.zhengjie.modules.spider_log.service.query.SpiderLogQueryService;
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
public class SpiderLogController {

    @Autowired
    private SpiderLogService spiderLogService;

    @Autowired
    private SpiderLogQueryService spiderLogQueryService;

    private static final String ENTITY_NAME = "spiderLog";

    @Log("查询SpiderLog")
    @GetMapping(value = "/spiderLog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getSpiderLogs(SpiderLogDTO resources, Pageable pageable){
        return new ResponseEntity(spiderLogQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增SpiderLog")
    @PostMapping(value = "/spiderLog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody SpiderLog resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(spiderLogService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改SpiderLog")
    @PutMapping(value = "/spiderLog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody SpiderLog resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        spiderLogService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除SpiderLog")
    @DeleteMapping(value = "/spiderLog/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        spiderLogService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}