package me.zhengjie.modules.spider.repository;

import me.zhengjie.modules.spider.domain.Spider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-19
*/
public interface SpiderRepository extends JpaRepository<Spider, Long>, JpaSpecificationExecutor {
}