package com.shop.shoponline.service;

import com.shop.shoponline.entity.UserOrderGoods;
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
public interface UserOrderGoodsService extends IService<UserOrderGoods> {
    void batchUserOrderGoods(List<UserOrderGoods> list);
}
