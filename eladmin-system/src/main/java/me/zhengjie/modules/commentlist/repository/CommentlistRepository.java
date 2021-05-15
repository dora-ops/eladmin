package me.zhengjie.modules.commentlist.repository;

import me.zhengjie.modules.commentlist.domain.Commentlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author y
* @date 2021-05-15
*/
public interface CommentlistRepository extends JpaRepository<Commentlist, Long>, JpaSpecificationExecutor {
}