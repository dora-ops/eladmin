package me.zhengjie.modules.car.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.car.domain.Car;
import me.zhengjie.modules.car.service.CarService;
import me.zhengjie.modules.car.service.dto.CarDTO;
import me.zhengjie.modules.car.service.query.CarQueryService;
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
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarQueryService carQueryService;

    private static final String ENTITY_NAME = "car";

    @Log("分页查询Car")
    @GetMapping(value = "/car")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCars(CarDTO resources, Pageable pageable){
        return new ResponseEntity(carQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("查询全部Car")
    @GetMapping(value = "/queryAll/car")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity queryCars(CarDTO resources){
        return new ResponseEntity(carQueryService.queryAll(resources),HttpStatus.OK);
    }

    @Log("查询Users")
    @GetMapping(value = "/findById/car")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findById(Long id){
        return new ResponseEntity(carService.findById(id),HttpStatus.OK);
    }

    @Log("新增Car")
    @PostMapping(value = "/car")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Car resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(carService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Car")
    @PutMapping(value = "/car")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Car resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        carService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Car")
    @DeleteMapping(value = "/car/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        carService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}