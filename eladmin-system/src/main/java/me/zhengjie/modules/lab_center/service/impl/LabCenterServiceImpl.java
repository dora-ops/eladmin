package me.zhengjie.modules.lab_center.service.impl;

import me.zhengjie.modules.lab_center.domain.LabCenter;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.lab_center.repository.LabCenterRepository;
import me.zhengjie.modules.lab_center.service.LabCenterService;
import me.zhengjie.modules.lab_center.service.dto.LabCenterDTO;
import me.zhengjie.modules.lab_center.service.mapper.LabCenterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-16
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LabCenterServiceImpl implements LabCenterService {

    @Autowired
    private LabCenterRepository labCenterRepository;

    @Autowired
    private LabCenterMapper labCenterMapper;

    @Override
    public LabCenterDTO findById(Long id) {
        Optional<LabCenter> labCenter = labCenterRepository.findById(id);
        ValidationUtil.isNull(labCenter,"LabCenter","id",id);
        return labCenterMapper.toDto(labCenter.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LabCenterDTO create(LabCenter resources) {
        return labCenterMapper.toDto(labCenterRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LabCenter resources) {
        Optional<LabCenter> optionalLabCenter = labCenterRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalLabCenter,"LabCenter","id",resources.getId());

        LabCenter labCenter = optionalLabCenter.get();
        // 此处需自己修改
        resources.setId(labCenter.getId());
        labCenterRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        labCenterRepository.deleteById(id);
    }
}