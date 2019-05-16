package me.zhengjie.modules.exp_plan.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.exp_plan.domain.ExpPlan;
import me.zhengjie.modules.exp_plan.service.ExpPlanService;
import me.zhengjie.modules.exp_plan.service.dto.ExpPlanDTO;
import me.zhengjie.modules.exp_plan.service.query.ExpPlanQueryService;
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
public class ExpPlanController {

    @Autowired
    private ExpPlanService expPlanService;

    @Autowired
    private ExpPlanQueryService expPlanQueryService;

    private static final String ENTITY_NAME = "expPlan";

    @Log("查询ExpPlan")
    @GetMapping(value = "/expPlan")
    @PreAuthorize("hasAnyRole('ADMIN','REDIS_ALL')")
    public ResponseEntity getExpPlans(ExpPlanDTO resources, Pageable pageable){
        return new ResponseEntity(expPlanQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增ExpPlan")
    @PostMapping(value = "/expPlan")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody ExpPlan resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(expPlanService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ExpPlan")
    @PutMapping(value = "/expPlan")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody ExpPlan resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        expPlanService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ExpPlan")
    @DeleteMapping(value = "/expPlan/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        expPlanService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}