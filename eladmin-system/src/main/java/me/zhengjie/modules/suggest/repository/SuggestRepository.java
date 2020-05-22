package me.zhengjie.modules.suggest.repository;

import me.zhengjie.modules.suggest.domain.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-05-21
*/
public interface SuggestRepository extends JpaRepository<Suggest, Long>, JpaSpecificationExecutor {
}