package me.zhengjie.modules.erea_info.service.impl;

import me.zhengjie.modules.erea_info.domain.EreaInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.erea_info.repository.EreaInfoRepository;
import me.zhengjie.modules.erea_info.service.EreaInfoService;
import me.zhengjie.modules.erea_info.service.dto.EreaInfoDTO;
import me.zhengjie.modules.erea_info.service.mapper.EreaInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EreaInfoServiceImpl implements EreaInfoService {

    @Autowired
    private EreaInfoRepository ereaInfoRepository;

    @Autowired
    private EreaInfoMapper ereaInfoMapper;

    @Override
    public EreaInfoDTO findById(Long id) {
        Optional<EreaInfo> ereaInfo = ereaInfoRepository.findById(id);
        ValidationUtil.isNull(ereaInfo,"EreaInfo","id",id);
        return ereaInfoMapper.toDto(ereaInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EreaInfoDTO create(EreaInfo resources) {
        return ereaInfoMapper.toDto(ereaInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(EreaInfo resources) {
        Optional<EreaInfo> optionalEreaInfo = ereaInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalEreaInfo,"EreaInfo","id",resources.getId());

        EreaInfo ereaInfo = optionalEreaInfo.get();
        // 此处需自己修改
        resources.setId(ereaInfo.getId());
        ereaInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ereaInfoRepository.deleteById(id);
    }
}