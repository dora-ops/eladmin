package me.zhengjie.modules.projectInfo.service.impl;

import me.zhengjie.modules.projectInfo.domain.ProjectInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.projectInfo.repository.ProjectInfoRepository;
import me.zhengjie.modules.projectInfo.service.ProjectInfoService;
import me.zhengjie.modules.projectInfo.service.dto.ProjectInfoDTO;
import me.zhengjie.modules.projectInfo.service.mapper.ProjectInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2020-05-02
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProjectInfoServiceImpl implements ProjectInfoService {

    @Autowired
    private ProjectInfoRepository projectInfoRepository;

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public ProjectInfoDTO findById(Long id) {
        Optional<ProjectInfo> projectInfo = projectInfoRepository.findById(id);
        ValidationUtil.isNull(projectInfo,"ProjectInfo","id",id);
        return projectInfoMapper.toDto(projectInfo.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectInfoDTO create(ProjectInfo resources) {
        return projectInfoMapper.toDto(projectInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProjectInfo resources) {
        Optional<ProjectInfo> optionalProjectInfo = projectInfoRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalProjectInfo,"ProjectInfo","id",resources.getId());

        ProjectInfo projectInfo = optionalProjectInfo.get();
        resources.setId(projectInfo.getId());
        projectInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        projectInfoRepository.deleteById(id);
    }
}