package me.zhengjie.modules.commentlist.service.impl;

import me.zhengjie.modules.commentlist.domain.Commentlist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.commentlist.repository.CommentlistRepository;
import me.zhengjie.modules.commentlist.service.CommentlistService;
import me.zhengjie.modules.commentlist.service.dto.CommentlistDTO;
import me.zhengjie.modules.commentlist.service.mapper.CommentlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author y
* @date 2021-05-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CommentlistServiceImpl implements CommentlistService {

    @Autowired
    private CommentlistRepository commentlistRepository;

    @Autowired
    private CommentlistMapper commentlistMapper;

    @Override
    public CommentlistDTO findById(Long id) {
        Optional<Commentlist> commentlist = commentlistRepository.findById(id);
        ValidationUtil.isNull(commentlist,"Commentlist","id",id);
        return commentlistMapper.toDto(commentlist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentlistDTO create(Commentlist resources) {
        return commentlistMapper.toDto(commentlistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Commentlist resources) {
        Optional<Commentlist> optionalCommentlist = commentlistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCommentlist,"Commentlist","id",resources.getId());

        Commentlist commentlist = optionalCommentlist.get();
        resources.setId(commentlist.getId());
        commentlistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        commentlistRepository.deleteById(id);
    }
}