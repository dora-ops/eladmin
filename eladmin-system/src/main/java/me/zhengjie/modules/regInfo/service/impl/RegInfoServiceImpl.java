package me.zhengjie.modules.regInfo.service.impl;

import me.zhengjie.modules.regInfo.domain.RegInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.regInfo.repository.RegInfoRepository;
import me.zhengjie.modules.regInfo.service.RegInfoService;
import me.zhengjie.modules.regInfo.service.dto.RegInfoDTO;
import me.zhengjie.modules.regInfo.service.mapper.RegInfoMapper;
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
public class RegInfoServiceImpl implements RegInfoService {

    @Autowired
    private RegInfoRepository regInfoRepository;

    @Autowired
    private RegInfoMapper regInfoMapper;

    @Override
    public RegInfoDTO findById(Long id) {
        Optional<RegInfo> regInfo = regInfoRepository.findById(id);
        ValidationUtil.isNull(regInfo,"RegInfo","id",id);
        return regInfoMapper.toDto(regInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegInfoDTO create(RegInfo resources) {
        return regInfoMapper.toDto(regInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RegInfo resources) {
        Optional<RegInfo> optionalRegInfo = regInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalRegInfo,"RegInfo","id",resources.getId());

        RegInfo regInfo = optionalRegInfo.get();
        resources.setId(regInfo.getId());
        regInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        regInfoRepository.deleteById(id);
    }
}