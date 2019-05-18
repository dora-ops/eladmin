package me.zhengjie.modules.spider_log.service.impl;

import me.zhengjie.modules.spider_log.domain.SpiderLog;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.spider_log.repository.SpiderLogRepository;
import me.zhengjie.modules.spider_log.service.SpiderLogService;
import me.zhengjie.modules.spider_log.service.dto.SpiderLogDTO;
import me.zhengjie.modules.spider_log.service.mapper.SpiderLogMapper;
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
public class SpiderLogServiceImpl implements SpiderLogService {

    @Autowired
    private SpiderLogRepository spiderLogRepository;

    @Autowired
    private SpiderLogMapper spiderLogMapper;

    @Override
    public SpiderLogDTO findById(Long id) {
        Optional<SpiderLog> spiderLog = spiderLogRepository.findById(id);
        ValidationUtil.isNull(spiderLog,"SpiderLog","id",id);
        return spiderLogMapper.toDto(spiderLog.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SpiderLogDTO create(SpiderLog resources) {
        return spiderLogMapper.toDto(spiderLogRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SpiderLog resources) {
        Optional<SpiderLog> optionalSpiderLog = spiderLogRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalSpiderLog,"SpiderLog","id",resources.getId());

        SpiderLog spiderLog = optionalSpiderLog.get();
        // 此处需自己修改
        resources.setId(spiderLog.getId());
        spiderLogRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        spiderLogRepository.deleteById(id);
    }
}