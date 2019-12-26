package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.mall_mapper.BrandMapper;
import top.philxin.mapper.mall_mapper.RegionMapper;
import top.philxin.model.MallModel.Brand;
import top.philxin.model.MallModel.BrandCondition;
import top.philxin.model.MallModel.BrandExample;
import top.philxin.model.MallModel.Region;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.MallService;

import java.util.List;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    BrandMapper brandMapper;
    /**
     * 获取全部的行政区域并以list的形式返回
     * @return
     */
    @Override
    public List<Region> getAllRegion() {
        List<Region> regionList = regionMapper.selectByTypeAndPid(1,0);
        for (Region region : regionList) {
            List<Region> cityList = regionMapper.selectByTypeAndPid(2,region.getId());
            region.setChildren(cityList);
            for (Region cities : cityList) {
                cities.setChildren(regionMapper.selectByTypeAndPid(3,cities.getId()));
            }
        }
        return regionList;
    }

    /**
     * 此方法根据name的模糊查询和id的精确查询得到制造商信息
     * @param brandCondition
     * @return
     */
    @Override
    public BaseDataVo<Brand> getBrandListByPage(BrandCondition brandCondition) {
        PageHelper.startPage(brandCondition.getPage(),brandCondition.getLimit());
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        //如果id不为空，精确查询id
        if(brandCondition.getId() != null){
            criteria.andIdEqualTo(brandCondition.getId());
        }
        //如果name不为空，模糊查询name
        if(brandCondition.getName() != null) {
            criteria.andNameLike("%" + brandCondition.getName() + "%");
        }
        //得到brandList并封入data中返回。
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        BaseDataVo<Brand> baseDataVo = new BaseDataVo<>();
        baseDataVo.setItems(brands);
        baseDataVo.setTotal((int) pageInfo.getTotal());
        return baseDataVo;
    }

    /**
     * 此方法为更新品牌商信息的具体实现
     * @param brand
     * @return
     */
    @Override
    public Brand updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
        //将刚刚更改后的品牌商查出并返回
        Brand newBrand = brandMapper.selectByPrimaryKey(brand.getId());
        return newBrand;
    }
}
