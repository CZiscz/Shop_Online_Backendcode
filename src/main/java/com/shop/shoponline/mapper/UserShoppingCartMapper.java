package com.shop.shoponline.mapper;

import com.shop.shoponline.entity.UserShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.shoponline.vo.CartGoodsVO;
import io.lettuce.core.dynamic.annotation.Param;
import jdk.jfr.Period;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */
public interface UserShoppingCartMapper extends BaseMapper<UserShoppingCart> {
List<CartGoodsVO>getCartGoodsInfo(@Param("id")Integer id);
}
