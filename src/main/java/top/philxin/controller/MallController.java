package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.MallModel.*;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.MallService;

import java.util.List;

@RestController
@RequestMapping("admin")
public class MallController {
    @Autowired
    MallService mallService;
    /**
     * 用于获取全部的行政区域
     * @return
     */
    @RequestMapping("region/list")
    public BaseRespVo getAllRegion() {
        List<Region> regionList = mallService.getAllRegion();
        BaseRespVo baseRespVo = BaseRespVo.success(regionList);
        return baseRespVo;
    }

    /**
     * 根据名称和id获取制造商信息
     * @return
     */
    @RequestMapping("brand/list")
    public BaseRespVo getBrandListByPage(BrandCondition brandCondition) {
        BaseDataVo<Brand> baseDataVo = mallService.getBrandListByPage(brandCondition);
        BaseRespVo baseRespVo = BaseRespVo.success(baseDataVo);
        return baseRespVo;
    }

    /**
     * 此方法用于更新品牌制造商信息
     * @param brand
     * @return
     */
    @RequestMapping("brand/update")
    public BaseRespVo updateBrand(@RequestBody Brand brand) {
        Brand newBrand = mallService.updateBrand(brand);
        BaseRespVo<Brand> baseRespVo = BaseRespVo.success(newBrand);
        return  baseRespVo;
    }

    /**
     * 此方法用于删除品牌制造商；
     * 假删，将数据库中的deleted设置为1。
     * @param brand
     * @return
     */
    @RequestMapping("brand/delete")
    public BaseRespVo deleteBrand(@RequestBody Brand brand) {
        mallService.deleteBrand(brand.getId());
        return BaseRespVo.success();
    }

    /**
     * 获取全部的商品类目
     * @return
     */
    @RequestMapping("category/list")
    public BaseRespVo getCategoryList() {
        List<Category> categories = mallService.getCategoryList();
        return  BaseRespVo.success(categories);
    }

    /**
     * 获取一级商品类目
     * @return
     */
    @RequestMapping("category/l1")
    public BaseRespVo getCategoryL1() {
        List<CategoryByLevel> categories = mallService.getCategoryByLevel("L1");
        return  BaseRespVo.success(categories);
    }
}
