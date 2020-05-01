package me.zhengjie.modules.projectInfo.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.projectInfo.domain.ProjectInfo;
import me.zhengjie.modules.projectInfo.service.dto.ProjectInfoDTO;
import me.zhengjie.modules.projectInfo.repository.ProjectInfoRepository;
import me.zhengjie.modules.projectInfo.service.mapper.ProjectInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "projectInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProjectInfoQueryService {

    @Autowired
    private ProjectInfoRepository projectInfoRepository;

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ProjectInfoDTO projectInfo, Pageable pageable){
        Page<ProjectInfo> page = projectInfoRepository.findAll(new Spec(projectInfo),pageable);
        return PageUtil.toPage(page.map(projectInfoMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ProjectInfoDTO projectInfo){
        return projectInfoMapper.toDto(projectInfoRepository.findAll(new Spec(projectInfo)));
    }

    class Spec implements Specification<ProjectInfo> {

        private ProjectInfoDTO projectInfo;

        public Spec(ProjectInfoDTO projectInfo){
            this.projectInfo = projectInfo;
        }

        @Override
        public Predicate toPredicate(Root<ProjectInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(projectInfo.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+projectInfo.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}