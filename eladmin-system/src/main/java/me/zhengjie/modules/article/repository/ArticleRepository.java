package me.zhengjie.modules.article.repository;

import me.zhengjie.modules.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-19
*/
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor {
}