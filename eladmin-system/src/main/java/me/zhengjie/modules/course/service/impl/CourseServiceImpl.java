package me.zhengjie.modules.course.service.impl;

import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.course.repository.CourseRepository;
import me.zhengjie.modules.course.service.CourseService;
import me.zhengjie.modules.course.service.dto.CourseDTO;
import me.zhengjie.modules.course.service.mapper.CourseMapper;
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
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CourseDTO findById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        ValidationUtil.isNull(course,"Course","id",id);
        return courseMapper.toDto(course.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseDTO create(Course resources) {
        return courseMapper.toDto(courseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Course resources) {
        Optional<Course> optionalCourse = courseRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCourse,"Course","id",resources.getId());

        Course course = optionalCourse.get();
        // 此处需自己修改
        resources.setId(course.getId());
        courseRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}