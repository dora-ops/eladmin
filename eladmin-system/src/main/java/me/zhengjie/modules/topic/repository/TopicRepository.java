package me.zhengjie.modules.topic.repository;

import me.zhengjie.modules.topic.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-05-21
*/
public interface TopicRepository extends JpaRepository<Topic, Long>, JpaSpecificationExecutor {
}