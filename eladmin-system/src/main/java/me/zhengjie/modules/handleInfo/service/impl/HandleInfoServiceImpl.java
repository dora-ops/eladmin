package me.zhengjie.modules.handleInfo.service.impl;

import me.zhengjie.modules.handleInfo.domain.HandleInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.handleInfo.repository.HandleInfoRepository;
import me.zhengjie.modules.handleInfo.service.HandleInfoService;
import me.zhengjie.modules.handleInfo.service.dto.HandleInfoDTO;
import me.zhengjie.modules.handleInfo.service.mapper.HandleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-04-30
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class HandleInfoServiceImpl implements HandleInfoService {

    @Autowired
    private HandleInfoRepository handleInfoRepository;

    @Autowired
    private HandleInfoMapper handleInfoMapper;

    @Override
    public HandleInfoDTO findById(Long id) {
        Optional<HandleInfo> handleInfo = handleInfoRepository.findById(id);
        ValidationUtil.isNull(handleInfo,"HandleInfo","id",id);
        return handleInfoMapper.toDto(handleInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HandleInfoDTO create(HandleInfo resources) {
        return handleInfoMapper.toDto(handleInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(HandleInfo resources) {
        Optional<HandleInfo> optionalHandleInfo = handleInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalHandleInfo,"HandleInfo","id",resources.getId());

        HandleInfo handleInfo = optionalHandleInfo.get();
        resources.setId(handleInfo.getId());
        handleInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        handleInfoRepository.deleteById(id);
    }
}