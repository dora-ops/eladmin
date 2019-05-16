package me.zhengjie.modules.experiment.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.experiment.domain.Experiment;
import me.zhengjie.modules.experiment.service.ExperimentService;
import me.zhengjie.modules.experiment.service.dto.ExperimentDTO;
import me.zhengjie.modules.experiment.service.query.ExperimentQueryService;
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
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private ExperimentQueryService experimentQueryService;

    private static final String ENTITY_NAME = "experiment";

    @Log("查询Experiment")
    @GetMapping(value = "/experiment")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity getExperiments(ExperimentDTO resources, Pageable pageable){
        return new ResponseEntity(experimentQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Experiment")
    @PostMapping(value = "/experiment")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Experiment resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(experimentService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Experiment")
    @PutMapping(value = "/experiment")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Experiment resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        experimentService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Experiment")
    @DeleteMapping(value = "/experiment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        experimentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}