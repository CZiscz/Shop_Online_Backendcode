package com.shop.shoponline.service;

import com.shop.shoponline.entity.IndexCarousel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */
public interface IndexCarouselService extends IService<IndexCarousel> {
    //首页轮播图
    List<IndexCarousel> getList(Integer distributionSite);

}