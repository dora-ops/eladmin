package me.zhengjie.modules.people.service.impl;

import me.zhengjie.modules.people.domain.People;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.people.repository.PeopleRepository;
import me.zhengjie.modules.people.service.PeopleService;
import me.zhengjie.modules.people.service.dto.PeopleDTO;
import me.zhengjie.modules.people.service.mapper.PeopleMapper;
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
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public PeopleDTO findById(Long id) {
        Optional<People> people = peopleRepository.findById(id);
        ValidationUtil.isNull(people,"People","id",id);
        return peopleMapper.toDto(people.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PeopleDTO create(People resources) {
        return peopleMapper.toDto(peopleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(People resources) {
        Optional<People> optionalPeople = peopleRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPeople,"People","id",resources.getId());

        People people = optionalPeople.get();
        resources.setId(people.getId());
        peopleRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
}