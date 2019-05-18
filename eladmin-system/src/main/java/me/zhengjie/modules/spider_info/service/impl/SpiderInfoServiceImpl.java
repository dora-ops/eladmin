package me.zhengjie.modules.spider_info.service.impl;

import me.zhengjie.modules.spider_info.domain.SpiderInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.spider_info.repository.SpiderInfoRepository;
import me.zhengjie.modules.spider_info.service.SpiderInfoService;
import me.zhengjie.modules.spider_info.service.dto.SpiderInfoDTO;
import me.zhengjie.modules.spider_info.service.mapper.SpiderInfoMapper;
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
public class SpiderInfoServiceImpl implements SpiderInfoService {

    @Autowired
    private SpiderInfoRepository spiderInfoRepository;

    @Autowired
    private SpiderInfoMapper spiderInfoMapper;

    @Override
    public SpiderInfoDTO findById(Long id) {
        Optional<SpiderInfo> spiderInfo = spiderInfoRepository.findById(id);
        ValidationUtil.isNull(spiderInfo,"SpiderInfo","id",id);
        return spiderInfoMapper.toDto(spiderInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SpiderInfoDTO create(SpiderInfo resources) {
        return spiderInfoMapper.toDto(spiderInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SpiderInfo resources) {
        Optional<SpiderInfo> optionalSpiderInfo = spiderInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalSpiderInfo,"SpiderInfo","id",resources.getId());

        SpiderInfo spiderInfo = optionalSpiderInfo.get();
        // 此处需自己修改
        resources.setId(spiderInfo.getId());
        spiderInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        spiderInfoRepository.deleteById(id);
    }


}