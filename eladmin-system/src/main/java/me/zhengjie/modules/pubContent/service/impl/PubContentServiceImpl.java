package me.zhengjie.modules.pubContent.service.impl;

import me.zhengjie.modules.pubContent.domain.PubContent;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.pubContent.repository.PubContentRepository;
import me.zhengjie.modules.pubContent.service.PubContentService;
import me.zhengjie.modules.pubContent.service.dto.PubContentDTO;
import me.zhengjie.modules.pubContent.service.mapper.PubContentMapper;
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
public class PubContentServiceImpl implements PubContentService {

    @Autowired
    private PubContentRepository pubContentRepository;

    @Autowired
    private PubContentMapper pubContentMapper;

    @Override
    public PubContentDTO findById(Long id) {
        Optional<PubContent> pubContent = pubContentRepository.findById(id);
        ValidationUtil.isNull(pubContent,"PubContent","id",id);
        return pubContentMapper.toDto(pubContent.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PubContentDTO create(PubContent resources) {
        return pubContentMapper.toDto(pubContentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PubContent resources) {
        Optional<PubContent> optionalPubContent = pubContentRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPubContent,"PubContent","id",resources.getId());

        PubContent pubContent = optionalPubContent.get();
        resources.setId(pubContent.getId());
        pubContentRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        pubContentRepository.deleteById(id);
    }
}