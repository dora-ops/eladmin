package me.zhengjie.modules.express.repository;

import me.zhengjie.modules.express.domain.Express;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface ExpressRepository extends JpaRepository<Express, Long>, JpaSpecificationExecutor {
}