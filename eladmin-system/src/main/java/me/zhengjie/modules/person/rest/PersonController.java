package me.zhengjie.modules.person.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.person.domain.Person;
import me.zhengjie.modules.person.service.PersonService;
import me.zhengjie.modules.person.service.dto.PersonDTO;
import me.zhengjie.modules.person.service.query.PersonQueryService;
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
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonQueryService personQueryService;

    private static final String ENTITY_NAME = "person";

    @Log("分页查询Person")
    @GetMapping(value = "/person")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPersons(PersonDTO resources, Pageable pageable){
        return new ResponseEntity(personQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Person")
    @GetMapping(value = "/queryAll/person")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryPersons(PersonDTO resources){
        return new ResponseEntity(personQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/person")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(personService.findById(id),HttpStatus.OK);
    }

    @Log("新增Person")
    @PostMapping(value = "/person")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Person resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(personService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Person")
    @PutMapping(value = "/person")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Person resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        personService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Person")
    @DeleteMapping(value = "/person/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        personService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}