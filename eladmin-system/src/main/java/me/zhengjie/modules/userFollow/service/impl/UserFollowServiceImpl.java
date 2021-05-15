package me.zhengjie.modules.userFollow.service.impl;

import me.zhengjie.modules.userFollow.domain.UserFollow;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.userFollow.repository.UserFollowRepository;
import me.zhengjie.modules.userFollow.service.UserFollowService;
import me.zhengjie.modules.userFollow.service.dto.UserFollowDTO;
import me.zhengjie.modules.userFollow.service.mapper.UserFollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author y
* @date 2021-05-15
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserFollowServiceImpl implements UserFollowService {

    @Autowired
    private UserFollowRepository userFollowRepository;

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Override
    public UserFollowDTO findById(Long id) {
        Optional<UserFollow> userFollow = userFollowRepository.findById(id);
        ValidationUtil.isNull(userFollow,"UserFollow","id",id);
        return userFollowMapper.toDto(userFollow.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserFollowDTO create(UserFollow resources) {
        return userFollowMapper.toDto(userFollowRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserFollow resources) {
        Optional<UserFollow> optionalUserFollow = userFollowRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalUserFollow,"UserFollow","id",resources.getId());

        UserFollow userFollow = optionalUserFollow.get();
        resources.setId(userFollow.getId());
        userFollowRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        userFollowRepository.deleteById(id);
    }
}