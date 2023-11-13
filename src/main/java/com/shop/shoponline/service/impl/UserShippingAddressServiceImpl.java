package com.shop.shoponline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shop.shoponline.vo.AddressVO;
import com.shop.shoponline.common.exception.ServerException;
import com.shop.shoponline.convert.AddressConvert;
import com.shop.shoponline.entity.UserShippingAddress;
import com.shop.shoponline.enums.AddressDefaultEnum;
import com.shop.shoponline.mapper.UserShippingAddressMapper;
import com.shop.shoponline.service.UserShippingAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserShippingAddressServiceImpl extends ServiceImpl<UserShippingAddressMapper, UserShippingAddress> implements UserShippingAddressService {

    @Override
    public Integer saveShippingAddress(AddressVO addressVO) {
        UserShippingAddress convert= AddressConvert.INSTANCE.convert(addressVO);
        if(addressVO.getIsDefault()== AddressDefaultEnum.DEFAULT_ADDRESS.getValue()){
            List<UserShippingAddress> list=baseMapper.selectList(new LambdaQueryWrapper<UserShippingAddress>().eq(UserShippingAddress::getIsDefault,AddressDefaultEnum.DEFAULT_ADDRESS.getValue()));
            if(list.size()>0){
                throw new ServerException("已存在默认接口，请勿重复操作");
            }
        }
        save(convert);
        return convert.getId();

    }

    @Override
    public Integer editShippingAddress(AddressVO addressVO) {
        UserShippingAddress userShippingAddress=baseMapper.selectById(addressVO.getId());
        if(userShippingAddress==null){
            throw new ServerException("地址不存在");
        }
        if(addressVO.getIsDefault()==AddressDefaultEnum.DEFAULT_ADDRESS.getValue()){
            //
            UserShippingAddress address=baseMapper.selectOne(new LambdaQueryWrapper<UserShippingAddress>().eq(UserShippingAddress::getUserId,addressVO.getUserId()).eq(UserShippingAddress::getIsDefault,AddressDefaultEnum.DEFAULT_ADDRESS.getValue()));
            //
            if(address!=null){
                address.setIsDefault(AddressDefaultEnum.NOT_DEFAULT_ADDRESS.getValue());
                updateById(address);
            }
        }
        UserShippingAddress address=AddressConvert.INSTANCE.convert(addressVO);
        updateById(address);
        return address.getId();
    }

    @Override
    public List<AddressVO> getShippingAddress(Integer userid) {
        List<UserShippingAddress> addresses = baseMapper.selectList(new LambdaQueryWrapper<UserShippingAddress>().eq(UserShippingAddress::getUserId, userid).orderByDesc(UserShippingAddress::getIsDefault));
        List<AddressVO> addressVOS = AddressConvert.INSTANCE.convertToAddressVOList(addresses);
        return addressVOS;
    }

    @Override
    public AddressVO getShippingAddressById(Integer id) {
        UserShippingAddress address=baseMapper.selectById(id);
        if(address==null){
            throw new ServerException("地址不存在");
        }
        AddressVO addressVO = AddressConvert.INSTANCE.convertToAddressVO(address);
        return addressVO;
    }

    @Override
    public Integer deleteShippingAddress(Integer id) {
        UserShippingAddress address=baseMapper.selectById(id);
        if(address==null){
            throw new ServerException("地址不存在");
        }
        Integer i = baseMapper.deleteById(id);
        return i;
    }


}

