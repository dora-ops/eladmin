package me.zhengjie.modules.suggest.service.impl;

import me.zhengjie.modules.suggest.domain.Suggest;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.suggest.repository.SuggestRepository;
import me.zhengjie.modules.suggest.service.SuggestService;
import me.zhengjie.modules.suggest.service.dto.SuggestDTO;
import me.zhengjie.modules.suggest.service.mapper.SuggestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-05-21
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private SuggestMapper suggestMapper;

    @Override
    public SuggestDTO findById(Long id) {
        Optional<Suggest> suggest = suggestRepository.findById(id);
        ValidationUtil.isNull(suggest,"Suggest","id",id);
        return suggestMapper.toDto(suggest.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuggestDTO create(Suggest resources) {
        return suggestMapper.toDto(suggestRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Suggest resources) {
        Optional<Suggest> optionalSuggest = suggestRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalSuggest,"Suggest","id",resources.getId());

        Suggest suggest = optionalSuggest.get();
        resources.setId(suggest.getId());
        suggestRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        suggestRepository.deleteById(id);
    }
}