package me.zhengjie.modules.lab_room.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.lab_room.domain.LabRoom;
import me.zhengjie.modules.lab_room.service.dto.LabRoomDTO;
import me.zhengjie.modules.lab_room.repository.LabRoomRepository;
import me.zhengjie.modules.lab_room.service.mapper.LabRoomMapper;
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
@CacheConfig(cacheNames = "labRoom")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LabRoomQueryService {

    @Autowired
    private LabRoomRepository labRoomRepository;

    @Autowired
    private LabRoomMapper labRoomMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(LabRoomDTO labRoom, Pageable pageable){
        Page<LabRoom> page = labRoomRepository.findAll(new Spec(labRoom),pageable);
        return PageUtil.toPage(page.map(labRoomMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(LabRoomDTO labRoom){
        return labRoomMapper.toDto(labRoomRepository.findAll(new Spec(labRoom)));
    }

    class Spec implements Specification<LabRoom> {

        private LabRoomDTO labRoom;

        public Spec(LabRoomDTO labRoom){
            this.labRoom = labRoom;
        }

        @Override
        public Predicate toPredicate(Root<LabRoom> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(labRoom.getName())){
                /**
                * 模糊
                */
                list.add(cb.like(root.get("name").as(String.class),"%"+labRoom.getName()+"%"));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}