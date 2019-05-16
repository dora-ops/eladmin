package me.zhengjie.modules.exp_plan.service.impl;

import me.zhengjie.modules.exp_plan.domain.ExpPlan;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.exp_plan.repository.ExpPlanRepository;
import me.zhengjie.modules.exp_plan.service.ExpPlanService;
import me.zhengjie.modules.exp_plan.service.dto.ExpPlanDTO;
import me.zhengjie.modules.exp_plan.service.mapper.ExpPlanMapper;
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
public class ExpPlanServiceImpl implements ExpPlanService {

    @Autowired
    private ExpPlanRepository expPlanRepository;

    @Autowired
    private ExpPlanMapper expPlanMapper;

    @Override
    public ExpPlanDTO findById(Long id) {
        Optional<ExpPlan> expPlan = expPlanRepository.findById(id);
        ValidationUtil.isNull(expPlan,"ExpPlan","id",id);
        return expPlanMapper.toDto(expPlan.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExpPlanDTO create(ExpPlan resources) {
        return expPlanMapper.toDto(expPlanRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ExpPlan resources) {
        Optional<ExpPlan> optionalExpPlan = expPlanRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalExpPlan,"ExpPlan","id",resources.getId());

        ExpPlan expPlan = optionalExpPlan.get();
        // 此处需自己修改
        resources.setId(expPlan.getId());
        expPlanRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        expPlanRepository.deleteById(id);
    }
}