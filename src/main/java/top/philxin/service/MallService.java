package top.philxin.service;

import top.philxin.model.MallModel.Brand;
import top.philxin.model.MallModel.BrandCondition;
import top.philxin.model.MallModel.Region;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;

import java.util.List;


public interface MallService {

    List<Region> getAllRegion();

    BaseDataVo<Brand> getBrandListByPage(BrandCondition brandCondition);

    Brand updateBrand(Brand brand);
}
