package me.zhengjie.modules.grade.service.impl;

import me.zhengjie.modules.grade.domain.Grade;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.grade.repository.GradeRepository;
import me.zhengjie.modules.grade.service.GradeService;
import me.zhengjie.modules.grade.service.dto.GradeDTO;
import me.zhengjie.modules.grade.service.mapper.GradeMapper;
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
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public GradeDTO findById(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        ValidationUtil.isNull(grade,"Grade","id",id);
        return gradeMapper.toDto(grade.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GradeDTO create(Grade resources) {
        return gradeMapper.toDto(gradeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Grade resources) {
        Optional<Grade> optionalGrade = gradeRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalGrade,"Grade","id",resources.getId());

        Grade grade = optionalGrade.get();
        resources.setId(grade.getId());
        gradeRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        gradeRepository.deleteById(id);
    }
}