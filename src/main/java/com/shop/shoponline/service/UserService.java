package com.shop.shoponline.service;

import com.shop.shoponline.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.shoponline.query.UserLoginQuery;
import com.shop.shoponline.vo.LoginResultVO;
import com.shop.shoponline.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */
public interface UserService extends IService<User> {
    /*
   用户登录
    */
    LoginResultVO login(UserLoginQuery query);

    User getUserInfo(Integer userId);
    //修改用户信息
    UserVO editUserInfo(UserVO userVO);
    //修改用户头像
    String editUserAvatar(Integer userId, MultipartFile file);
}
