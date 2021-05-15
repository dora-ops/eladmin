package me.zhengjie.modules.person.service.impl;

import me.zhengjie.modules.person.domain.Person;

import me.zhengjie.exception.EntityExistException;

import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.person.repository.PersonRepository;
import me.zhengjie.modules.person.service.PersonService;
import me.zhengjie.modules.person.service.dto.PersonDTO;
import me.zhengjie.modules.person.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author y
* @date 2021-05-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public PersonDTO findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        ValidationUtil.isNull(person,"Person","id",id);
        return personMapper.toDto(person.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonDTO create(Person resources) {
        if(personRepository.findByMobile(resources.getMobile()) != null){
            throw new EntityExistException(Person.class,"mobile",resources.getMobile());
        }
        return personMapper.toDto(personRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Person resources) {
        Optional<Person> optionalPerson = personRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPerson,"Person","id",resources.getId());

        Person person = optionalPerson.get();

            Person person1 = null;

        person1 = personRepository.findByMobile(resources.getMobile());
        if(person1 != null && !person1.getId().equals(person.getId())){
            throw new EntityExistException(Person.class,"mobile",resources.getMobile());
        }
        resources.setId(person.getId());
        personRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}