package me.zhengjie.modules.notice.repository;

import me.zhengjie.modules.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface NoticeRepository extends JpaRepository<Notice, Long>, JpaSpecificationExecutor {
}