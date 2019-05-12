package me.zhengjie.modules.express.service.impl;

import me.zhengjie.modules.express.domain.Express;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.express.repository.ExpressRepository;
import me.zhengjie.modules.express.service.ExpressService;
import me.zhengjie.modules.express.service.dto.ExpressDTO;
import me.zhengjie.modules.express.service.mapper.ExpressMapper;
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
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressRepository expressRepository;

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public ExpressDTO findById(Long id) {
        Optional<Express> express = expressRepository.findById(id);
        ValidationUtil.isNull(express,"Express","id",id);
        return expressMapper.toDto(express.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExpressDTO create(Express resources) {
        return expressMapper.toDto(expressRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Express resources) {
        Optional<Express> optionalExpress = expressRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalExpress,"Express","id",resources.getId());

        Express express = optionalExpress.get();
        // 此处需自己修改
        resources.setId(express.getId());
        expressRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        expressRepository.deleteById(id);
    }
}