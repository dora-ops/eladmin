package me.zhengjie.modules.people.repository;

import me.zhengjie.modules.people.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-04
*/
public interface PeopleRepository extends JpaRepository<People, Long>, JpaSpecificationExecutor {
}