package top.philxin.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.BrandMapper;
import top.philxin.model.Brand;
import top.philxin.service.wx.WxBrandService;

/**
 * @ClassName: WxBrandServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 16:07
 * @version: v1.0
 */
@Service
public class WxBrandServiceImpl implements WxBrandService {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Brand getBrandById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }
}
