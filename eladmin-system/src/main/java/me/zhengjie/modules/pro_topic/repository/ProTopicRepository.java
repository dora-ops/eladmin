package me.zhengjie.modules.pro_topic.repository;

import me.zhengjie.modules.pro_topic.domain.ProTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface ProTopicRepository extends JpaRepository<ProTopic, Long>, JpaSpecificationExecutor {
}