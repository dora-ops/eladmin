package me.zhengjie.modules.people.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.people.domain.People;
import me.zhengjie.modules.people.service.PeopleService;
import me.zhengjie.modules.people.service.dto.PeopleDTO;
import me.zhengjie.modules.people.service.query.PeopleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2020-04-04
*/
@RestController
@RequestMapping("api")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PeopleQueryService peopleQueryService;

    private static final String ENTITY_NAME = "people";

    @Log("分页查询People")
    @GetMapping(value = "/people")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPeoples(PeopleDTO resources, Pageable pageable){
        return new ResponseEntity(peopleQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部People")
    @GetMapping(value = "/queryAll/people")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryPeoples(PeopleDTO resources){
        return new ResponseEntity(peopleQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/people")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(peopleService.findById(id),HttpStatus.OK);
    }

    @Log("新增People")
    @PostMapping(value = "/people")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody People resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(peopleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改People")
    @PutMapping(value = "/people")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody People resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        peopleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除People")
    @DeleteMapping(value = "/people/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        peopleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}