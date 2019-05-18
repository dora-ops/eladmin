package me.zhengjie.modules.spider_info.repository;

import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-18
*/
public interface SpiderInfoRepository extends JpaRepository<SpiderInfo, Long>, JpaSpecificationExecutor {
}