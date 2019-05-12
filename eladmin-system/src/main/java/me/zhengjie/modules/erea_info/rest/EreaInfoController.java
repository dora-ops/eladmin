package me.zhengjie.modules.erea_info.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.erea_info.domain.EreaInfo;
import me.zhengjie.modules.erea_info.service.EreaInfoService;
import me.zhengjie.modules.erea_info.service.dto.EreaInfoDTO;
import me.zhengjie.modules.erea_info.service.query.EreaInfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-12
*/
@RestController
@RequestMapping("api")
public class EreaInfoController {

    @Autowired
    private EreaInfoService ereaInfoService;

    @Autowired
    private EreaInfoQueryService ereaInfoQueryService;

    private static final String ENTITY_NAME = "ereaInfo";

    @Log("查询EreaInfo")
    @GetMapping(value = "/ereaInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getEreaInfos(EreaInfoDTO resources, Pageable pageable){
        return new ResponseEntity(ereaInfoQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增EreaInfo")
    @PostMapping(value = "/ereaInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody EreaInfo resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(ereaInfoService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改EreaInfo")
    @PutMapping(value = "/ereaInfo")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody EreaInfo resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        ereaInfoService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除EreaInfo")
    @DeleteMapping(value = "/ereaInfo/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        ereaInfoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}