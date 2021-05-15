package me.zhengjie.modules.userFollow.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.userFollow.domain.UserFollow;
import me.zhengjie.modules.userFollow.service.UserFollowService;
import me.zhengjie.modules.userFollow.service.dto.UserFollowDTO;
import me.zhengjie.modules.userFollow.service.query.UserFollowQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author y
* @date 2021-05-15
*/
@RestController
@RequestMapping("api")
public class UserFollowController {

    @Autowired
    private UserFollowService userFollowService;

    @Autowired
    private UserFollowQueryService userFollowQueryService;

    private static final String ENTITY_NAME = "userFollow";

    @Log("分页查询UserFollow")
    @GetMapping(value = "/userFollow")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getUserFollows(UserFollowDTO resources, Pageable pageable){
        return new ResponseEntity(userFollowQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部UserFollow")
    @GetMapping(value = "/queryAll/userFollow")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryUserFollows(UserFollowDTO resources){
        return new ResponseEntity(userFollowQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/userFollow")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(userFollowService.findById(id),HttpStatus.OK);
    }

    @Log("新增UserFollow")
    @PostMapping(value = "/userFollow")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody UserFollow resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(userFollowService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改UserFollow")
    @PutMapping(value = "/userFollow")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody UserFollow resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        userFollowService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除UserFollow")
    @DeleteMapping(value = "/userFollow/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        userFollowService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}