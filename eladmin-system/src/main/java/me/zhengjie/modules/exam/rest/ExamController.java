package me.zhengjie.modules.exam.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.exam.domain.Exam;
import me.zhengjie.modules.exam.service.ExamService;
import me.zhengjie.modules.exam.service.dto.ExamDTO;
import me.zhengjie.modules.exam.service.query.ExamQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-05-15
*/
@RestController
@RequestMapping("api")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamQueryService examQueryService;

    private static final String ENTITY_NAME = "exam";

    @Log("分页查询Exam")
    @GetMapping(value = "/exam")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getExams(ExamDTO resources, Pageable pageable){
        return new ResponseEntity(examQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Exam")
    @GetMapping(value = "/queryAll/exam")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryExams(ExamDTO resources){
        return new ResponseEntity(examQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/exam")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(examService.findById(id),HttpStatus.OK);
    }

    @Log("新增Exam")
    @PostMapping(value = "/exam")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Exam resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(examService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Exam")
    @PutMapping(value = "/exam")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Exam resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        examService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Exam")
    @DeleteMapping(value = "/exam/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        examService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}