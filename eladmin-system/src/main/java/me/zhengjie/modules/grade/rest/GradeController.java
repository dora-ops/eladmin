package me.zhengjie.modules.grade.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.grade.domain.Grade;
import me.zhengjie.modules.grade.service.GradeService;
import me.zhengjie.modules.grade.service.dto.GradeDTO;
import me.zhengjie.modules.grade.service.query.GradeQueryService;
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
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeQueryService gradeQueryService;

    private static final String ENTITY_NAME = "grade";

    @Log("分页查询Grade")
    @GetMapping(value = "/grade")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getGrades(GradeDTO resources, Pageable pageable){
        return new ResponseEntity(gradeQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Grade")
    @GetMapping(value = "/queryAll/grade")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryGrades(GradeDTO resources){
        return new ResponseEntity(gradeQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/grade")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(gradeService.findById(id),HttpStatus.OK);
    }

    @Log("新增Grade")
    @PostMapping(value = "/grade")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Grade resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(gradeService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Grade")
    @PutMapping(value = "/grade")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Grade resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        gradeService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Grade")
    @DeleteMapping(value = "/grade/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        gradeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}