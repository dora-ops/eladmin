package me.zhengjie.modules.lab_room.service.impl;

import me.zhengjie.modules.lab_room.domain.LabRoom;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.lab_room.repository.LabRoomRepository;
import me.zhengjie.modules.lab_room.service.LabRoomService;
import me.zhengjie.modules.lab_room.service.dto.LabRoomDTO;
import me.zhengjie.modules.lab_room.service.mapper.LabRoomMapper;
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
public class LabRoomServiceImpl implements LabRoomService {

    @Autowired
    private LabRoomRepository labRoomRepository;

    @Autowired
    private LabRoomMapper labRoomMapper;

    @Override
    public LabRoomDTO findById(Long id) {
        Optional<LabRoom> labRoom = labRoomRepository.findById(id);
        ValidationUtil.isNull(labRoom,"LabRoom","id",id);
        return labRoomMapper.toDto(labRoom.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LabRoomDTO create(LabRoom resources) {
        return labRoomMapper.toDto(labRoomRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LabRoom resources) {
        Optional<LabRoom> optionalLabRoom = labRoomRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalLabRoom,"LabRoom","id",resources.getId());

        LabRoom labRoom = optionalLabRoom.get();
        // 此处需自己修改
        resources.setId(labRoom.getId());
        labRoomRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        labRoomRepository.deleteById(id);
    }
}