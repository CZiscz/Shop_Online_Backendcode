package com.shop.shoponline.service;

import com.shop.shoponline.entity.UserShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.shoponline.query.CartQuery;
import com.shop.shoponline.vo.CartGoodsVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */
public interface UserShoppingCartService extends IService<UserShoppingCart> {

    CartGoodsVO addShopCart(CartQuery query); //添加购物车
    List<CartGoodsVO> shopCartList(Integer userId);//购物车列表
}
