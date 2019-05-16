package me.zhengjie.modules.experiment.service.impl;

import me.zhengjie.modules.experiment.domain.Experiment;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.experiment.repository.ExperimentRepository;
import me.zhengjie.modules.experiment.service.ExperimentService;
import me.zhengjie.modules.experiment.service.dto.ExperimentDTO;
import me.zhengjie.modules.experiment.service.mapper.ExperimentMapper;
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
public class ExperimentServiceImpl implements ExperimentService {

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private ExperimentMapper experimentMapper;

    @Override
    public ExperimentDTO findById(Long id) {
        Optional<Experiment> experiment = experimentRepository.findById(id);
        ValidationUtil.isNull(experiment,"Experiment","id",id);
        return experimentMapper.toDto(experiment.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExperimentDTO create(Experiment resources) {
        return experimentMapper.toDto(experimentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Experiment resources) {
        Optional<Experiment> optionalExperiment = experimentRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalExperiment,"Experiment","id",resources.getId());

        Experiment experiment = optionalExperiment.get();
        // 此处需自己修改
        resources.setId(experiment.getId());
        experimentRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        experimentRepository.deleteById(id);
    }
}