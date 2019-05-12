package me.zhengjie.modules.pro_topic.service.impl;

import me.zhengjie.modules.pro_topic.domain.ProTopic;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.pro_topic.repository.ProTopicRepository;
import me.zhengjie.modules.pro_topic.service.ProTopicService;
import me.zhengjie.modules.pro_topic.service.dto.ProTopicDTO;
import me.zhengjie.modules.pro_topic.service.mapper.ProTopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProTopicServiceImpl implements ProTopicService {

    @Autowired
    private ProTopicRepository proTopicRepository;

    @Autowired
    private ProTopicMapper proTopicMapper;

    @Override
    public ProTopicDTO findById(Long id) {
        Optional<ProTopic> proTopic = proTopicRepository.findById(id);
        ValidationUtil.isNull(proTopic,"ProTopic","id",id);
        return proTopicMapper.toDto(proTopic.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProTopicDTO create(ProTopic resources) {
        return proTopicMapper.toDto(proTopicRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProTopic resources) {
        Optional<ProTopic> optionalProTopic = proTopicRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalProTopic,"ProTopic","id",resources.getId());

        ProTopic proTopic = optionalProTopic.get();
        // 此处需自己修改
        resources.setId(proTopic.getId());
        proTopicRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        proTopicRepository.deleteById(id);
    }
}