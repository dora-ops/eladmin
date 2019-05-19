package me.zhengjie.modules.stu_job.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.stu_job.domain.StuJob;
import me.zhengjie.modules.stu_job.service.StuJobService;
import me.zhengjie.modules.stu_job.service.dto.StuJobDTO;
import me.zhengjie.modules.stu_job.service.query.StuJobQueryService;
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
public class StuJobController {

    @Autowired
    private StuJobService stuJobService;

    @Autowired
    private StuJobQueryService stuJobQueryService;

    private static final String ENTITY_NAME = "stuJob";

    @Log("查询StuJob")
    @GetMapping(value = "/stuJob")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity getStuJobs(StuJobDTO resources, Pageable pageable){
        return new ResponseEntity(stuJobQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增StuJob")
    @PostMapping(value = "/stuJob")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity create(@Validated @RequestBody StuJob resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(stuJobService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改StuJob")
    @PutMapping(value = "/stuJob")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity update(@Validated @RequestBody StuJob resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        stuJobService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除StuJob")
    @DeleteMapping(value = "/stuJob/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity delete(@PathVariable Long id){
        stuJobService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}