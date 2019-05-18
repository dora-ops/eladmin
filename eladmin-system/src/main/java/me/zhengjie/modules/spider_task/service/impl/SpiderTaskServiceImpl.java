package me.zhengjie.modules.spider_task.service.impl;

import me.zhengjie.modules.article.repository.ArticleRepository;
import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.modules.spider_info.repository.SpiderInfoRepository;
import me.zhengjie.modules.spider_log.repository.SpiderLogRepository;
import me.zhengjie.modules.spider_task.domain.SpiderTask;
import me.zhengjie.utils.StringUtils;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.spider_task.repository.SpiderTaskRepository;
import me.zhengjie.modules.spider_task.service.SpiderTaskService;
import me.zhengjie.modules.spider_task.service.dto.SpiderTaskDTO;
import me.zhengjie.modules.spider_task.service.mapper.SpiderTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.Optional;

/**
* @author jie
* @date 2019-05-18
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpiderTaskServiceImpl implements SpiderTaskService {

    @Autowired
    private SpiderTaskRepository spiderTaskRepository;

    @Autowired
    private SpiderTaskMapper spiderTaskMapper;

    @Autowired
    private SpiderInfoRepository spiderInfoRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SpiderLogRepository spiderLogRepository;

    @Override
    public SpiderTaskDTO findById(Long id) {
        Optional<SpiderTask> spiderTask = spiderTaskRepository.findById(id);
        ValidationUtil.isNull(spiderTask,"SpiderTask","id",id);
        return spiderTaskMapper.toDto(spiderTask.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SpiderTaskDTO create(SpiderTask resources) {
        return spiderTaskMapper.toDto(spiderTaskRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SpiderTask resources) {
        Optional<SpiderTask> optionalSpiderTask = spiderTaskRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalSpiderTask,"SpiderTask","id",resources.getId());

        SpiderTask spiderTask = optionalSpiderTask.get();
        // 此处需自己修改
        resources.setId(spiderTask.getId());
        spiderTaskRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        spiderTaskRepository.deleteById(id);
    }

    @Override
    public Object start(String code,SpiderTask resources) {
        Optional<SpiderInfo> byId = spiderInfoRepository.findById(Long.valueOf(resources.getInfoId()));
        SpiderInfo spiderInfo = byId.get();
        String url=spiderInfo.getDomain()+spiderInfo.getStartUrl();
        if (StringUtils.equals(code,"CSDNProcess")){
            CSDNProcess csdnProcess = new CSDNProcess(resources,articleRepository,spiderInfoRepository,spiderLogRepository);
            new Spider(csdnProcess).
                    addUrl(url).
                    addPipeline(new ConsolePipeline()).run();
        }
        return "success";
    }
}