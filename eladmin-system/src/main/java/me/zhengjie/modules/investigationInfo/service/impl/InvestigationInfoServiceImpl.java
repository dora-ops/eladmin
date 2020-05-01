package me.zhengjie.modules.investigationInfo.service.impl;

import me.zhengjie.modules.investigationInfo.domain.InvestigationInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.investigationInfo.repository.InvestigationInfoRepository;
import me.zhengjie.modules.investigationInfo.service.InvestigationInfoService;
import me.zhengjie.modules.investigationInfo.service.dto.InvestigationInfoDTO;
import me.zhengjie.modules.investigationInfo.service.mapper.InvestigationInfoMapper;
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
public class InvestigationInfoServiceImpl implements InvestigationInfoService {

    @Autowired
    private InvestigationInfoRepository investigationInfoRepository;

    @Autowired
    private InvestigationInfoMapper investigationInfoMapper;

    @Override
    public InvestigationInfoDTO findById(Long id) {
        Optional<InvestigationInfo> investigationInfo = investigationInfoRepository.findById(id);
        ValidationUtil.isNull(investigationInfo,"InvestigationInfo","id",id);
        return investigationInfoMapper.toDto(investigationInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public InvestigationInfoDTO create(InvestigationInfo resources) {
        return investigationInfoMapper.toDto(investigationInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(InvestigationInfo resources) {
        Optional<InvestigationInfo> optionalInvestigationInfo = investigationInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalInvestigationInfo,"InvestigationInfo","id",resources.getId());

        InvestigationInfo investigationInfo = optionalInvestigationInfo.get();
        resources.setId(investigationInfo.getId());
        investigationInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        investigationInfoRepository.deleteById(id);
    }
}