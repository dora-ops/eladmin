package me.zhengjie.modules.spider_log.repository;

import me.zhengjie.modules.spider_log.domain.SpiderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-19
*/
public interface SpiderLogRepository extends JpaRepository<SpiderLog, Long>, JpaSpecificationExecutor {
}