package me.zhengjie.modules.resource.service.impl;

import me.zhengjie.modules.resource.domain.Resource;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.resource.repository.ResourceRepository;
import me.zhengjie.modules.resource.service.ResourceService;
import me.zhengjie.modules.resource.service.dto.ResourceDTO;
import me.zhengjie.modules.resource.service.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author y
* @date 2021-05-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public ResourceDTO findById(Long id) {
        Optional<Resource> resource = resourceRepository.findById(id);
        ValidationUtil.isNull(resource,"Resource","id",id);
        return resourceMapper.toDto(resource.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResourceDTO create(Resource resources) {
        return resourceMapper.toDto(resourceRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Resource resources) {
        Optional<Resource> optionalResource = resourceRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalResource,"Resource","id",resources.getId());

        Resource resource = optionalResource.get();
        resources.setId(resource.getId());
        resourceRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        resourceRepository.deleteById(id);
    }
}