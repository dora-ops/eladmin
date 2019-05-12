package me.zhengjie.modules.goods.repository;

import me.zhengjie.modules.goods.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface GoodsRepository extends JpaRepository<Goods, Long>, JpaSpecificationExecutor {
}