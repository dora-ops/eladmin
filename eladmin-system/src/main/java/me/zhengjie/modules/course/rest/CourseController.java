package me.zhengjie.modules.course.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.modules.course.service.CourseService;
import me.zhengjie.modules.course.service.dto.CourseDTO;
import me.zhengjie.modules.course.service.query.CourseQueryService;
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
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseQueryService courseQueryService;

    private static final String ENTITY_NAME = "course";

    @Log("查询Course")
    @GetMapping(value = "/course")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity getCourses(CourseDTO resources, Pageable pageable){
        return new ResponseEntity(courseQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Course")
    @PostMapping(value = "/course")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Course resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(courseService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Course")
    @PutMapping(value = "/course")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Course resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        courseService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Course")
    @DeleteMapping(value = "/course/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        courseService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}