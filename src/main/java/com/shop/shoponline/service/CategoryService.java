package com.shop.shoponline.service;

import com.shop.shoponline.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.shoponline.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caozhi
 * @since 2023-11-08
 */
public interface CategoryService extends IService<Category> {
    //首页分类列表
    List<Category> getIndexCategoryList();

    //分类tab页
    List<CategoryVO> getCategoryList();
}
