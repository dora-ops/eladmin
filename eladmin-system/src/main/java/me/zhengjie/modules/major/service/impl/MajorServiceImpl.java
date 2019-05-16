package me.zhengjie.modules.major.service.impl;

import me.zhengjie.modules.major.domain.Major;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.major.repository.MajorRepository;
import me.zhengjie.modules.major.service.MajorService;
import me.zhengjie.modules.major.service.dto.MajorDTO;
import me.zhengjie.modules.major.service.mapper.MajorMapper;
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
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public MajorDTO findById(Long id) {
        Optional<Major> major = majorRepository.findById(id);
        ValidationUtil.isNull(major,"Major","id",id);
        return majorMapper.toDto(major.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MajorDTO create(Major resources) {
        return majorMapper.toDto(majorRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Major resources) {
        Optional<Major> optionalMajor = majorRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMajor,"Major","id",resources.getId());

        Major major = optionalMajor.get();
        // 此处需自己修改
        resources.setId(major.getId());
        majorRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        majorRepository.deleteById(id);
    }
}