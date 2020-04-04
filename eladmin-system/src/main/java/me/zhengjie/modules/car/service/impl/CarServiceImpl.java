package me.zhengjie.modules.car.service.impl;

import me.zhengjie.modules.car.domain.Car;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.car.repository.CarRepository;
import me.zhengjie.modules.car.service.CarService;
import me.zhengjie.modules.car.service.dto.CarDTO;
import me.zhengjie.modules.car.service.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-04-04
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public CarDTO findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        ValidationUtil.isNull(car,"Car","id",id);
        return carMapper.toDto(car.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CarDTO create(Car resources) {
        return carMapper.toDto(carRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Car resources) {
        Optional<Car> optionalCar = carRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCar,"Car","id",resources.getId());

        Car car = optionalCar.get();
        resources.setId(car.getId());
        carRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}