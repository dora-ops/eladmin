package me.zhengjie.modules.person.repository;

import me.zhengjie.modules.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author y
* @date 2021-05-15
*/
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor {

    /**
     * findByMobile
     * @param mobile
     * @return
     */
    Person findByMobile(String mobile);
}