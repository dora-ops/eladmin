package me.zhengjie.modules.stu_course.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.stu_course.domain.StuCourse;
import me.zhengjie.modules.stu_course.service.StuCourseService;
import me.zhengjie.modules.stu_course.service.dto.StuCourseDTO;
import me.zhengjie.modules.stu_course.service.query.StuCourseQueryService;
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
public class StuCourseController {

    @Autowired
    private StuCourseService stuCourseService;

    @Autowired
    private StuCourseQueryService stuCourseQueryService;

    private static final String ENTITY_NAME = "stuCourse";

    @Log("查询StuCourse")
    @GetMapping(value = "/stuCourse")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity getStuCourses(StuCourseDTO resources, Pageable pageable){
        return new ResponseEntity(stuCourseQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增StuCourse")
    @PostMapping(value = "/stuCourse")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity create(@Validated @RequestBody StuCourse resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(stuCourseService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改StuCourse")
    @PutMapping(value = "/stuCourse")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity update(@Validated @RequestBody StuCourse resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        stuCourseService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除StuCourse")
    @DeleteMapping(value = "/stuCourse/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity delete(@PathVariable Long id){
        stuCourseService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}