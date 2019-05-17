package me.zhengjie.modules.article.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-17
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

}