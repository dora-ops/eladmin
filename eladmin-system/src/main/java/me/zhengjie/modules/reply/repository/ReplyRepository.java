package me.zhengjie.modules.reply.repository;

import me.zhengjie.modules.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2020-05-21
*/
public interface ReplyRepository extends JpaRepository<Reply, Long>, JpaSpecificationExecutor {
}