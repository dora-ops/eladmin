package me.zhengjie.modules.awardInfo.service.impl;

import me.zhengjie.modules.awardInfo.domain.AwardInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.awardInfo.repository.AwardInfoRepository;
import me.zhengjie.modules.awardInfo.service.AwardInfoService;
import me.zhengjie.modules.awardInfo.service.dto.AwardInfoDTO;
import me.zhengjie.modules.awardInfo.service.mapper.AwardInfoMapper;
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
public class AwardInfoServiceImpl implements AwardInfoService {

    @Autowired
    private AwardInfoRepository awardInfoRepository;

    @Autowired
    private AwardInfoMapper awardInfoMapper;

    @Override
    public AwardInfoDTO findById(Long id) {
        Optional<AwardInfo> awardInfo = awardInfoRepository.findById(id);
        ValidationUtil.isNull(awardInfo,"AwardInfo","id",id);
        return awardInfoMapper.toDto(awardInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AwardInfoDTO create(AwardInfo resources) {
        return awardInfoMapper.toDto(awardInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AwardInfo resources) {
        Optional<AwardInfo> optionalAwardInfo = awardInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAwardInfo,"AwardInfo","id",resources.getId());

        AwardInfo awardInfo = optionalAwardInfo.get();
        resources.setId(awardInfo.getId());
        awardInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        awardInfoRepository.deleteById(id);
    }
}