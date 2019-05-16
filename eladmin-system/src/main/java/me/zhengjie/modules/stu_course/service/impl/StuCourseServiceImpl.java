package me.zhengjie.modules.stu_course.service.impl;

import me.zhengjie.modules.stu_course.domain.StuCourse;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.stu_course.repository.StuCourseRepository;
import me.zhengjie.modules.stu_course.service.StuCourseService;
import me.zhengjie.modules.stu_course.service.dto.StuCourseDTO;
import me.zhengjie.modules.stu_course.service.mapper.StuCourseMapper;
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
public class StuCourseServiceImpl implements StuCourseService {

    @Autowired
    private StuCourseRepository stuCourseRepository;

    @Autowired
    private StuCourseMapper stuCourseMapper;

    @Override
    public StuCourseDTO findById(Long id) {
        Optional<StuCourse> stuCourse = stuCourseRepository.findById(id);
        ValidationUtil.isNull(stuCourse,"StuCourse","id",id);
        return stuCourseMapper.toDto(stuCourse.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StuCourseDTO create(StuCourse resources) {
        return stuCourseMapper.toDto(stuCourseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StuCourse resources) {
        Optional<StuCourse> optionalStuCourse = stuCourseRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalStuCourse,"StuCourse","id",resources.getId());

        StuCourse stuCourse = optionalStuCourse.get();
        // 此处需自己修改
        resources.setId(stuCourse.getId());
        stuCourseRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        stuCourseRepository.deleteById(id);
    }
}