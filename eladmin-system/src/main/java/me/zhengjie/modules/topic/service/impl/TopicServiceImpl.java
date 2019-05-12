package me.zhengjie.modules.topic.service.impl;

import me.zhengjie.modules.topic.domain.Topic;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.topic.repository.TopicRepository;
import me.zhengjie.modules.topic.service.TopicService;
import me.zhengjie.modules.topic.service.dto.TopicDTO;
import me.zhengjie.modules.topic.service.mapper.TopicMapper;
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
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public TopicDTO findById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        ValidationUtil.isNull(topic,"Topic","id",id);
        return topicMapper.toDto(topic.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TopicDTO create(Topic resources) {
        return topicMapper.toDto(topicRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Topic resources) {
        Optional<Topic> optionalTopic = topicRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTopic,"Topic","id",resources.getId());

        Topic topic = optionalTopic.get();
        // 此处需自己修改
        resources.setId(topic.getId());
        topicRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        topicRepository.deleteById(id);
    }
}