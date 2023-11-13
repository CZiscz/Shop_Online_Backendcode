package com.shop.shoponline.service;

import com.shop.shoponline.entity.UserShippingAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.shoponline.vo.AddressVO;
import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */
public interface UserShippingAddressService extends IService<UserShippingAddress> {

    Integer saveShippingAddress(AddressVO addressVO);
    //修改地址
    Integer editShippingAddress(AddressVO addressVO);

    //获取用户收获地址
//    Integer getList(AddressVO addressVO);
    List<AddressVO> getShippingAddress(Integer userid);

    //根据id获取指定地址
    AddressVO getShippingAddressById(Integer id);

    //删除指定地址
    Integer deleteShippingAddress(Integer id);
}
