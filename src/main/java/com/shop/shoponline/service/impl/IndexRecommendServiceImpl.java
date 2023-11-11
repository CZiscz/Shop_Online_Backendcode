package com.shop.shoponline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shop.shoponline.convert.IndexRecommendConvert;
import com.shop.shoponline.entity.IndexRecommend;
import com.shop.shoponline.mapper.IndexRecommendMapper;
import com.shop.shoponline.service.IndexRecommendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.shoponline.vo.IndexRecommendVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */
@Service
public class IndexRecommendServiceImpl extends ServiceImpl<IndexRecommendMapper, IndexRecommend> implements IndexRecommendService {

    @Override
    public List<IndexRecommendVO> getList() {
        LambdaQueryWrapper<IndexRecommend> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(IndexRecommend::getCreateTime);
        List<IndexRecommend> list=baseMapper.selectList(wrapper);
        List<IndexRecommendVO> results= IndexRecommendConvert.INSTANCE.convertToUserVoList(list);
        return results;
    }
}
