package me.zhengjie.modules.stu_job.service.impl;

import me.zhengjie.modules.stu_job.domain.StuJob;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.stu_job.repository.StuJobRepository;
import me.zhengjie.modules.stu_job.service.StuJobService;
import me.zhengjie.modules.stu_job.service.dto.StuJobDTO;
import me.zhengjie.modules.stu_job.service.mapper.StuJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-19
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StuJobServiceImpl implements StuJobService {

    @Autowired
    private StuJobRepository stuJobRepository;

    @Autowired
    private StuJobMapper stuJobMapper;

    @Override
    public StuJobDTO findById(Long id) {
        Optional<StuJob> stuJob = stuJobRepository.findById(id);
        ValidationUtil.isNull(stuJob,"StuJob","id",id);
        return stuJobMapper.toDto(stuJob.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StuJobDTO create(StuJob resources) {
        return stuJobMapper.toDto(stuJobRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StuJob resources) {
        Optional<StuJob> optionalStuJob = stuJobRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalStuJob,"StuJob","id",resources.getId());

        StuJob stuJob = optionalStuJob.get();
        // 此处需自己修改
        resources.setId(stuJob.getId());
        stuJobRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        stuJobRepository.deleteById(id);
    }
}