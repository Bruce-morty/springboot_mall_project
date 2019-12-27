package top.philxin.service;

import top.philxin.model.*;
import top.philxin.model.MallModel.*;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;

import java.util.List;


public interface MallService {

    List<Region> getAllRegion();

    BaseDataVo<Brand> getBrandListByPage(BrandCondition brandCondition);

    Brand updateBrand(Brand brand);

    void deleteBrand(Integer id);

    List<Category> getCategoryList();

    List<CategoryByLevel> getCategoryByLevel(String l1);

    void deleteCategory(Integer id);

    void updateCategory(Category category);

    BaseDataVo<Order> getOrderListByPage(OrderCondition orderCondition);
}
