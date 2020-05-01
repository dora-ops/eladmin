package me.zhengjie.modules.closedInfo.service.impl;

import me.zhengjie.modules.closedInfo.domain.ClosedInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.closedInfo.repository.ClosedInfoRepository;
import me.zhengjie.modules.closedInfo.service.ClosedInfoService;
import me.zhengjie.modules.closedInfo.service.dto.ClosedInfoDTO;
import me.zhengjie.modules.closedInfo.service.mapper.ClosedInfoMapper;
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
public class ClosedInfoServiceImpl implements ClosedInfoService {

    @Autowired
    private ClosedInfoRepository closedInfoRepository;

    @Autowired
    private ClosedInfoMapper closedInfoMapper;

    @Override
    public ClosedInfoDTO findById(Long id) {
        Optional<ClosedInfo> closedInfo = closedInfoRepository.findById(id);
        ValidationUtil.isNull(closedInfo,"ClosedInfo","id",id);
        return closedInfoMapper.toDto(closedInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClosedInfoDTO create(ClosedInfo resources) {
        return closedInfoMapper.toDto(closedInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ClosedInfo resources) {
        Optional<ClosedInfo> optionalClosedInfo = closedInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalClosedInfo,"ClosedInfo","id",resources.getId());

        ClosedInfo closedInfo = optionalClosedInfo.get();
        resources.setId(closedInfo.getId());
        closedInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        closedInfoRepository.deleteById(id);
    }
}