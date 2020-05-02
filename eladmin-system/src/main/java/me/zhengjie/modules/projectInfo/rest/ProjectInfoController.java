package me.zhengjie.modules.projectInfo.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.projectInfo.domain.ProjectInfo;
import me.zhengjie.modules.projectInfo.service.ProjectInfoService;
import me.zhengjie.modules.projectInfo.service.dto.ProjectInfoDTO;
import me.zhengjie.modules.projectInfo.service.query.ProjectInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-05-02
*/
@RestController
@RequestMapping("api")
public class ProjectInfoController {

    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private ProjectInfoQueryService projectInfoQueryService;

    private static final String ENTITY_NAME = "projectInfo";

    @Log("分页查询ProjectInfo")
    @GetMapping(value = "/projectInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getProjectInfos(ProjectInfoDTO resources, Pageable pageable){
        return new ResponseEntity(projectInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部ProjectInfo")
    @GetMapping(value = "/queryAll/projectInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryProjectInfos(ProjectInfoDTO resources){
        return new ResponseEntity(projectInfoQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/projectInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(projectInfoService.findById(id),HttpStatus.OK);
    }

    @Log("新增ProjectInfo")
    @PostMapping(value = "/projectInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody ProjectInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(projectInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改ProjectInfo")
    @PutMapping(value = "/projectInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody ProjectInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        projectInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除ProjectInfo")
    @DeleteMapping(value = "/projectInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        projectInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}