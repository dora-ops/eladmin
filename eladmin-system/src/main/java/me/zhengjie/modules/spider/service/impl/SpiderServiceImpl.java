package me.zhengjie.modules.spider.service.impl;

import me.zhengjie.modules.spider.domain.Spider;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.spider.repository.SpiderRepository;
import me.zhengjie.modules.spider.service.SpiderService;
import me.zhengjie.modules.spider.service.dto.SpiderDTO;
import me.zhengjie.modules.spider.service.mapper.SpiderMapper;
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
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private SpiderRepository spiderRepository;

    @Autowired
    private SpiderMapper spiderMapper;

    @Override
    public SpiderDTO findById(Long id) {
        Optional<Spider> spider = spiderRepository.findById(id);
        ValidationUtil.isNull(spider,"Spider","id",id);
        return spiderMapper.toDto(spider.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SpiderDTO create(Spider resources) {
        return spiderMapper.toDto(spiderRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Spider resources) {
        Optional<Spider> optionalSpider = spiderRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalSpider,"Spider","id",resources.getId());

        Spider spider = optionalSpider.get();
        // 此处需自己修改
        resources.setId(spider.getId());
        spiderRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        spiderRepository.deleteById(id);
    }
}