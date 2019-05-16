package me.zhengjie.modules.course_plan.service.impl;

import me.zhengjie.modules.course_plan.domain.CoursePlan;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.course_plan.repository.CoursePlanRepository;
import me.zhengjie.modules.course_plan.service.CoursePlanService;
import me.zhengjie.modules.course_plan.service.dto.CoursePlanDTO;
import me.zhengjie.modules.course_plan.service.mapper.CoursePlanMapper;
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
public class CoursePlanServiceImpl implements CoursePlanService {

    @Autowired
    private CoursePlanRepository coursePlanRepository;

    @Autowired
    private CoursePlanMapper coursePlanMapper;

    @Override
    public CoursePlanDTO findById(Long id) {
        Optional<CoursePlan> coursePlan = coursePlanRepository.findById(id);
        ValidationUtil.isNull(coursePlan,"CoursePlan","id",id);
        return coursePlanMapper.toDto(coursePlan.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CoursePlanDTO create(CoursePlan resources) {
        return coursePlanMapper.toDto(coursePlanRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CoursePlan resources) {
        Optional<CoursePlan> optionalCoursePlan = coursePlanRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCoursePlan,"CoursePlan","id",resources.getId());

        CoursePlan coursePlan = optionalCoursePlan.get();
        // 此处需自己修改
        resources.setId(coursePlan.getId());
        coursePlanRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        coursePlanRepository.deleteById(id);
    }
}