package me.zhengjie.modules.medical.service.impl;

import me.zhengjie.modules.medical.domain.Medical;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.medical.repository.MedicalRepository;
import me.zhengjie.modules.medical.service.MedicalService;
import me.zhengjie.modules.medical.service.dto.MedicalDTO;
import me.zhengjie.modules.medical.service.mapper.MedicalMapper;
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
public class MedicalServiceImpl implements MedicalService {

    @Autowired
    private MedicalRepository medicalRepository;

    @Autowired
    private MedicalMapper medicalMapper;

    @Override
    public MedicalDTO findById(Long id) {
        Optional<Medical> medical = medicalRepository.findById(id);
        ValidationUtil.isNull(medical,"Medical","id",id);
        return medicalMapper.toDto(medical.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MedicalDTO create(Medical resources) {
        return medicalMapper.toDto(medicalRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Medical resources) {
        Optional<Medical> optionalMedical = medicalRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMedical,"Medical","id",resources.getId());

        Medical medical = optionalMedical.get();
        // 此处需自己修改
        resources.setId(medical.getId());
        medicalRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        medicalRepository.deleteById(id);
    }
}