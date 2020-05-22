package me.zhengjie.modules.reply.service.impl;

import me.zhengjie.modules.reply.domain.Reply;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.reply.repository.ReplyRepository;
import me.zhengjie.modules.reply.service.ReplyService;
import me.zhengjie.modules.reply.service.dto.ReplyDTO;
import me.zhengjie.modules.reply.service.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-05-21
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public ReplyDTO findById(Long id) {
        Optional<Reply> reply = replyRepository.findById(id);
        ValidationUtil.isNull(reply,"Reply","id",id);
        return replyMapper.toDto(reply.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReplyDTO create(Reply resources) {
        return replyMapper.toDto(replyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Reply resources) {
        Optional<Reply> optionalReply = replyRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalReply,"Reply","id",resources.getId());

        Reply reply = optionalReply.get();
        resources.setId(reply.getId());
        replyRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        replyRepository.deleteById(id);
    }
}