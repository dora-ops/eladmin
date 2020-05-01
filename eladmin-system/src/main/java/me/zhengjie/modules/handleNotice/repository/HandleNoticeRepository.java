package me.zhengjie.modules.handleNotice.repository;

import me.zhengjie.modules.handleNotice.domain.HandleNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-04-30
*/
public interface HandleNoticeRepository extends JpaRepository<HandleNotice, Long>, JpaSpecificationExecutor {
}