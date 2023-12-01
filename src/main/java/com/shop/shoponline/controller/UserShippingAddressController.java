package com.shop.shoponline.controller;

import com.shop.shoponline.common.exception.ServerException;
import com.shop.shoponline.common.result.Result;
import com.shop.shoponline.service.UserShippingAddressService;
import com.shop.shoponline.vo.AddressVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import static com.shop.shoponline.common.utils.ObtainUserIdUtils.getUserId;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */

//@RequestMapping("/shoponline/userShippingAddress")

@Tag(name = "地址管理")
@RestController
@RequestMapping("member")
@AllArgsConstructor
public class UserShippingAddressController {
    private final UserShippingAddressService userShippingAddressService;
    @Resource
    private HttpServletRequest request;

    @Operation(summary = "添加收货地址")
    @PostMapping("address")
    public Result<Integer> saveAddress(@RequestBody @Validated AddressVO addressVO) {
        Integer userId = getUserId(request);
        addressVO.setUserId(userId);
        Integer addressId = userShippingAddressService.saveShippingAddress(addressVO);
        return Result.ok(addressId);
    }

    @Operation(summary = "修改收货地址")
    @PutMapping("address")
    public Result<Integer> editAddress(@RequestBody @Validated AddressVO addressVO) {
        if (addressVO.getId() == null) {
            throw new ServerException("请求参数不能为空");
        }
        addressVO.setUserId(getUserId(request));
        Integer addressId = userShippingAddressService.editShippingAddress(addressVO);
        return Result.ok(addressId);
    }

    @Operation(summary = "收货地址列表")
    @GetMapping("address")
    public Result<List<AddressVO>> getAddress(HttpServletRequest request) {
        Integer userId = getUserId(request);
        List<AddressVO> addresses = userShippingAddressService.getShippingAddress(userId);
        return Result.ok(addresses);
    }

    @Operation(summary = "收货地址详情")
    @GetMapping("address2")
    public Result<AddressVO> getAddressById(@RequestParam Integer id) {
        AddressVO address = userShippingAddressService.getShippingAddressById(id);
        return Result.ok(address);
    }

    @Operation(summary = "删除收货地址")
    @DeleteMapping("address")
    public Result<Integer> deleteAddressById(@RequestParam Integer id) {
        Integer i = userShippingAddressService.deleteShippingAddress(id);
        return Result.ok(i);
    }

}
