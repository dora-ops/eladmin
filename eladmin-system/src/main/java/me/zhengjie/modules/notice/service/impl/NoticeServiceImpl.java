package me.zhengjie.modules.notice.service.impl;

import me.zhengjie.modules.notice.domain.Notice;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.notice.repository.NoticeRepository;
import me.zhengjie.modules.notice.service.NoticeService;
import me.zhengjie.modules.notice.service.dto.NoticeDTO;
import me.zhengjie.modules.notice.service.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-18
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public NoticeDTO findById(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        ValidationUtil.isNull(notice,"Notice","id",id);
        return noticeMapper.toDto(notice.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NoticeDTO create(Notice resources) {
        return noticeMapper.toDto(noticeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Notice resources) {
        Optional<Notice> optionalNotice = noticeRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalNotice,"Notice","id",resources.getId());

        Notice notice = optionalNotice.get();
        // 此处需自己修改
        resources.setId(notice.getId());
        noticeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}