package me.zhengjie.modules.caseInfo.service.impl;

import me.zhengjie.modules.caseInfo.domain.CaseInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.caseInfo.repository.CaseInfoRepository;
import me.zhengjie.modules.caseInfo.service.CaseInfoService;
import me.zhengjie.modules.caseInfo.service.dto.CaseInfoDTO;
import me.zhengjie.modules.caseInfo.service.mapper.CaseInfoMapper;
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
public class CaseInfoServiceImpl implements CaseInfoService {

    @Autowired
    private CaseInfoRepository caseInfoRepository;

    @Autowired
    private CaseInfoMapper caseInfoMapper;

    @Override
    public CaseInfoDTO findById(Long id) {
        Optional<CaseInfo> caseInfo = caseInfoRepository.findById(id);
        ValidationUtil.isNull(caseInfo,"CaseInfo","id",id);
        return caseInfoMapper.toDto(caseInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CaseInfoDTO create(CaseInfo resources) {
        return caseInfoMapper.toDto(caseInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CaseInfo resources) {
        Optional<CaseInfo> optionalCaseInfo = caseInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCaseInfo,"CaseInfo","id",resources.getId());

        CaseInfo caseInfo = optionalCaseInfo.get();
        resources.setId(caseInfo.getId());
        caseInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        caseInfoRepository.deleteById(id);
    }
}