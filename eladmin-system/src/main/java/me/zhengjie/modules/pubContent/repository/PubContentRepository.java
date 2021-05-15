package me.zhengjie.modules.pubContent.repository;

import me.zhengjie.modules.pubContent.domain.PubContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author y
* @date 2021-05-15
*/
public interface PubContentRepository extends JpaRepository<PubContent, Long>, JpaSpecificationExecutor {
}