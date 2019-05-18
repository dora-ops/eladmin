package me.zhengjie.modules.spider_task.repository;

import me.zhengjie.modules.spider_task.domain.SpiderTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-18
*/
public interface SpiderTaskRepository extends JpaRepository<SpiderTask, Long>, JpaSpecificationExecutor {
}