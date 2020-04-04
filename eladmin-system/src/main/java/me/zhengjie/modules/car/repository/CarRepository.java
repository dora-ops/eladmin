package me.zhengjie.modules.car.repository;

import me.zhengjie.modules.car.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-04
*/
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor {
}