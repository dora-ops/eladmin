package me.zhengjie.modules.article.service.impl;

import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.article.repository.ArticleRepository;
import me.zhengjie.modules.article.service.ArticleService;
import me.zhengjie.modules.article.service.dto.ArticleDTO;
import me.zhengjie.modules.article.service.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-19
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleDTO findById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        ValidationUtil.isNull(article,"Article","id",id);
        return articleMapper.toDto(article.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDTO create(Article resources) {
        return articleMapper.toDto(articleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Article resources) {
        Optional<Article> optionalArticle = articleRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalArticle,"Article","id",resources.getId());

        Article article = optionalArticle.get();
        // 此处需自己修改
        resources.setId(article.getId());
        articleRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}