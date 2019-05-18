package me.zhengjie.modules.spider_task.service.impl;

import com.alibaba.fastjson.JSONObject;
import me.zhengjie.modules.article.domain.Article;
import me.zhengjie.modules.article.repository.ArticleRepository;
import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_info.repository.SpiderInfoRepository;
import me.zhengjie.modules.spider_log.domain.SpiderLog;
import me.zhengjie.modules.spider_log.repository.SpiderLogRepository;
import me.zhengjie.modules.spider_task.domain.SpiderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 *@Author feri
 *@Date Created in 2018/10/9 19:02
 */

public class CSDNProcess implements PageProcessor {
    private Site site=Site.me();

    private SpiderTask spiderTask;

    private ArticleRepository articleRepository;

    private SpiderInfoRepository spiderInfoRepository;

    private SpiderLogRepository spiderLogRepository;

    public CSDNProcess(SpiderTask resources,ArticleRepository articleRepository,SpiderInfoRepository spiderInfoRepository,SpiderLogRepository spiderLogRepository) {
        this.spiderTask=resources;
        this.articleRepository=articleRepository;
        this.spiderInfoRepository=spiderInfoRepository;
        this.spiderLogRepository=spiderLogRepository;
    }

    public void process(Page page) {
        Html html = page.getHtml();
        SpiderLog spiderLog=new SpiderLog();
        spiderLog.setData(html.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(page.getHeaders());
        spiderLog.setHeader( jsonObject.toJSONString());
        spiderLog.setUrl(page.getUrl().toString());
        spiderLog.setTaskId(this.spiderTask.getId().intValue());
        spiderLogRepository.save(spiderLog);
        //获取文章的标题
        Optional<SpiderInfo> byId = spiderInfoRepository.findById(Long.valueOf(this.spiderTask.getInfoId()));
        SpiderInfo spiderInfo = byId.get();
        JSONObject parseObject = JSONObject.parseObject(spiderInfo.getDynamicField());
        List<String> titles=html.xpath(parseObject.getString("titles")).all();
        //获取文章摘要
        List<String> contents=page.getHtml().xpath(parseObject.getString("contents")).all();
        //获取时间
        List<String> dates=page.getHtml().xpath(parseObject.getString("dates")).all();
        //获取阅读量
        List<String> reads=page.getHtml().xpath(parseObject.getString("reads")).all();
        //获取评论
        List<String> comments=page.getHtml().xpath(parseObject.getString("comments")).all();
        for(int i=0;i<titles.size();i++){
            Article article=new Article();
            article.setTitle(titles.get(i));
            article.setConTent(contents.get(i));
            article.setPubTime(Timestamp.valueOf(dates.get(i)));
            if (i<reads.size()){
                article.setReAd(reads.get(i));
            }
            article.setComMent(comments.get(i));
            articleRepository.save(article);
        }
    }
    public Site getSite() {
        return site;
    }
}
