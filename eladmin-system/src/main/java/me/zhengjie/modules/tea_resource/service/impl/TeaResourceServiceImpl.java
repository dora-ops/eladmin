package me.zhengjie.modules.tea_resource.service.impl;

import me.zhengjie.modules.tea_resource.domain.TeaResource;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.tea_resource.repository.TeaResourceRepository;
import me.zhengjie.modules.tea_resource.service.TeaResourceService;
import me.zhengjie.modules.tea_resource.service.dto.TeaResourceDTO;
import me.zhengjie.modules.tea_resource.service.mapper.TeaResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-05-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TeaResourceServiceImpl implements TeaResourceService {

    @Autowired
    private TeaResourceRepository teaResourceRepository;

    @Autowired
    private TeaResourceMapper teaResourceMapper;

    @Override
    public TeaResourceDTO findById(Long id) {
        Optional<TeaResource> teaResource = teaResourceRepository.findById(id);
        ValidationUtil.isNull(teaResource,"TeaResource","id",id);
        return teaResourceMapper.toDto(teaResource.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TeaResourceDTO create(TeaResource resources) {
        return teaResourceMapper.toDto(teaResourceRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TeaResource resources) {
        Optional<TeaResource> optionalTeaResource = teaResourceRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTeaResource,"TeaResource","id",resources.getId());

        TeaResource teaResource = optionalTeaResource.get();
        resources.setId(teaResource.getId());
        teaResourceRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        teaResourceRepository.deleteById(id);
    }
}