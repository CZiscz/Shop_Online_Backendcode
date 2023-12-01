package com.shop.shoponline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shop.shoponline.common.exception.ServerException;
import com.shop.shoponline.convert.AddressConvert;
import com.shop.shoponline.entity.UserShippingAddress;
import com.shop.shoponline.enums.AddressDefaultEnum;
import com.shop.shoponline.enums.DeleteFlagEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.shoponline.mapper.UserShippingAddressMapper;
import com.shop.shoponline.service.UserShippingAddressService;
import com.shop.shoponline.vo.AddressVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 * </p>
 *
 *
 * @author caozhi
 * @since 2023-11-12
 */
@Service
public class UserShippingAddressServiceImpl extends ServiceImpl<UserShippingAddressMapper, UserShippingAddress> implements UserShippingAddressService {

    @Override
    public Integer saveShippingAddress(AddressVO addressVO) {
        // 接收数据转换为实体类
        UserShippingAddress convert = AddressConvert.INSTANCE.convert(addressVO);
        // 是否需要设为默认地址
        if (addressVO.getIsDefault().equals(AddressDefaultEnum.DEFAULT_ADDRESS.getValue())) {
            // 根据用户查询是否存在默认地址
            LambdaQueryWrapper<UserShippingAddress> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserShippingAddress::getIsDefault, AddressDefaultEnum.DEFAULT_ADDRESS.getValue()).eq(UserShippingAddress::getUserId, addressVO.getUserId());
            List<UserShippingAddress> list = baseMapper.selectList(wrapper);
            // 如果存在，返回500
            if (list.size() > 0) {
                throw new ServerException("已存在默认地址，请勿重复操作");
            }
        }
        convert.setId(0);
        save(convert); // 实体对象插入
        return convert.getId();
    }

    @Override
    public Integer editShippingAddress(AddressVO addressVO) {
        // 判断地址是否存在
        UserShippingAddress userShoppingAddress = baseMapper.selectById(addressVO.getId());
        if (userShoppingAddress == null) {
            throw new ServerException("地址不存在");
        }
        // 查询该用户是否存在默认地址
        if (addressVO.getIsDefault().equals(AddressDefaultEnum.DEFAULT_ADDRESS.getValue())) {
            LambdaQueryWrapper<UserShippingAddress> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserShippingAddress::getIsDefault, AddressDefaultEnum.DEFAULT_ADDRESS.getValue()).eq(UserShippingAddress::getUserId, addressVO.getUserId());
            UserShippingAddress address = baseMapper.selectOne(wrapper);
            // 如果存在 更新之前默认地址
            if (address != null) {
                address.setIsDefault(AddressDefaultEnum.NOT_DEFAULT_ADDRESS.getValue());
                updateById(address);
            }
        }
        UserShippingAddress address = AddressConvert.INSTANCE.convert(addressVO);
        updateById(address);
        return address.getId();
    }

    @Override
    public List<AddressVO> getShippingAddress(Integer userId) {
        LambdaQueryWrapper<UserShippingAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserShippingAddress::getUserId, userId);
        wrapper.orderByDesc(UserShippingAddress::getIsDefault);
        List<UserShippingAddress> addressList = baseMapper.selectList(wrapper);
        return AddressConvert.INSTANCE.convertToAddressVOList(addressList);
    }

    @Override
    public AddressVO getShippingAddressById(Integer id) {
        // 查询地址信息 仅能查询到自己的地址
        LambdaQueryWrapper<UserShippingAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserShippingAddress::getId, id);
        UserShippingAddress address = baseMapper.selectOne(wrapper);
        // 判断地址是否存在
        if (address == null) {
            throw new ServerException("地址不存在");
        }
        return AddressConvert.INSTANCE.convertToAddressVO(address);
    }

    @Override
    public Integer deleteShippingAddress(Integer id) {
        LambdaQueryWrapper<UserShippingAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserShippingAddress::getId, id);
        UserShippingAddress address = baseMapper.selectOne(wrapper);
        if (address == null) {
            throw new ServerException("地址不存在");
        }
        baseMapper.deleteById(id);
        return id;
    }
}
