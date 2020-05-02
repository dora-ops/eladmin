package me.zhengjie.modules.handleNotice.service.impl;

import me.zhengjie.modules.handleNotice.domain.HandleNotice;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.handleNotice.repository.HandleNoticeRepository;
import me.zhengjie.modules.handleNotice.service.HandleNoticeService;
import me.zhengjie.modules.handleNotice.service.dto.HandleNoticeDTO;
import me.zhengjie.modules.handleNotice.service.mapper.HandleNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-05-02
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class HandleNoticeServiceImpl implements HandleNoticeService {

    @Autowired
    private HandleNoticeRepository handleNoticeRepository;

    @Autowired
    private HandleNoticeMapper handleNoticeMapper;

    @Override
    public HandleNoticeDTO findById(Long id) {
        Optional<HandleNotice> handleNotice = handleNoticeRepository.findById(id);
        ValidationUtil.isNull(handleNotice,"HandleNotice","id",id);
        return handleNoticeMapper.toDto(handleNotice.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HandleNoticeDTO create(HandleNotice resources) {
        return handleNoticeMapper.toDto(handleNoticeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(HandleNotice resources) {
        Optional<HandleNotice> optionalHandleNotice = handleNoticeRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalHandleNotice,"HandleNotice","id",resources.getId());

        HandleNotice handleNotice = optionalHandleNotice.get();
        resources.setId(handleNotice.getId());
        handleNoticeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        handleNoticeRepository.deleteById(id);
    }
}