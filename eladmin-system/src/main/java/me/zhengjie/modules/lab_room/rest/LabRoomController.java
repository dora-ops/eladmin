package me.zhengjie.modules.lab_room.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.lab_room.domain.LabRoom;
import me.zhengjie.modules.lab_room.service.LabRoomService;
import me.zhengjie.modules.lab_room.service.dto.LabRoomDTO;
import me.zhengjie.modules.lab_room.service.query.LabRoomQueryService;
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
public class LabRoomController {

    @Autowired
    private LabRoomService labRoomService;

    @Autowired
    private LabRoomQueryService labRoomQueryService;

    private static final String ENTITY_NAME = "labRoom";

    @Log("查询LabRoom")
    @GetMapping(value = "/labRoom")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getLabRooms(LabRoomDTO resources, Pageable pageable){
        return new ResponseEntity(labRoomQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增LabRoom")
    @PostMapping(value = "/labRoom")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody LabRoom resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(labRoomService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改LabRoom")
    @PutMapping(value = "/labRoom")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody LabRoom resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        labRoomService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除LabRoom")
    @DeleteMapping(value = "/labRoom/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        labRoomService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}