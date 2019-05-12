package me.zhengjie.modules.erea_info.repository;

import me.zhengjie.modules.erea_info.domain.EreaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface EreaInfoRepository extends JpaRepository<EreaInfo, Long>, JpaSpecificationExecutor {
}