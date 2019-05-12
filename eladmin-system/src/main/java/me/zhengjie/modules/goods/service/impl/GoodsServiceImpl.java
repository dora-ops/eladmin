package me.zhengjie.modules.goods.service.impl;

import me.zhengjie.modules.goods.domain.Goods;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.goods.repository.GoodsRepository;
import me.zhengjie.modules.goods.service.GoodsService;
import me.zhengjie.modules.goods.service.dto.GoodsDTO;
import me.zhengjie.modules.goods.service.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public GoodsDTO findById(Long id) {
        Optional<Goods> goods = goodsRepository.findById(id);
        ValidationUtil.isNull(goods,"Goods","id",id);
        return goodsMapper.toDto(goods.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsDTO create(Goods resources) {
        return goodsMapper.toDto(goodsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Goods resources) {
        Optional<Goods> optionalGoods = goodsRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalGoods,"Goods","id",resources.getId());

        Goods goods = optionalGoods.get();
        // 此处需自己修改
        resources.setId(goods.getId());
        goodsRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }
}