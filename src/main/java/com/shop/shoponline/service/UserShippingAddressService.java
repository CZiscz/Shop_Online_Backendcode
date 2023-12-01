package com.shop.shoponline.service;

import com.shop.shoponline.entity.UserShippingAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.shoponline.vo.AddressVO;
import org.springframework.stereotype.Service;

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

    Integer editShippingAddress(AddressVO addressVO);

    List<AddressVO> getShippingAddress(Integer userid);

    AddressVO getShippingAddressById(Integer id);

    Integer deleteShippingAddress(Integer id);

}
