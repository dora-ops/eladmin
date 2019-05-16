package me.zhengjie.modules.course_plan.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.course_plan.domain.CoursePlan;
import me.zhengjie.modules.course_plan.service.CoursePlanService;
import me.zhengjie.modules.course_plan.service.dto.CoursePlanDTO;
import me.zhengjie.modules.course_plan.service.query.CoursePlanQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-16
*/
@RestController
@RequestMapping("api")
public class CoursePlanController {

    @Autowired
    private CoursePlanService coursePlanService;

    @Autowired
    private CoursePlanQueryService coursePlanQueryService;

    private static final String ENTITY_NAME = "coursePlan";

    @Log("查询CoursePlan")
    @GetMapping(value = "/coursePlan")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity getCoursePlans(CoursePlanDTO resources, Pageable pageable){
        return new ResponseEntity(coursePlanQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增CoursePlan")
    @PostMapping(value = "/coursePlan")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody CoursePlan resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(coursePlanService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改CoursePlan")
    @PutMapping(value = "/coursePlan")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody CoursePlan resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        coursePlanService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除CoursePlan")
    @DeleteMapping(value = "/coursePlan/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        coursePlanService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}