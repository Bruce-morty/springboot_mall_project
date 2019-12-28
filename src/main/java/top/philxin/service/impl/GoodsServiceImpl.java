package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.*;
import top.philxin.model.*;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.GoodsModel.GoodsInfoDetailVo;
import top.philxin.service.GoodsService;

import java.util.List;

/**
 * @ClassName: GoddsServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 21:09
 * @version: v1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    StorageMapper storageMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;


    /**
     * 查询商品信息列表
     * @param pageHelperVo
     * @param goodsSn
     * @param name
     * @return
     */
    @Override
    public BaseDataVo queryGoods(PageHelperVo pageHelperVo, String goodsSn, String name) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        BaseDataVo baseDataVo = new BaseDataVo();
        List<Goods> goods = goodsMapper.selectGoods(goodsSn, name);
        baseDataVo.setItems(goods);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        long total = pageInfo.getTotal();
        baseDataVo.setTotal((int) total);
        return baseDataVo;
    }

    /**
     * 获取商品详细信息（可以使用连接查询，待优化）
     * @param goodsId 商品id
     * @return
     */
    @Override
    public GoodsInfoDetailVo getGoodDetail(Integer goodsId) {
        GoodsInfoDetailVo goodsInfoDetailVo = new GoodsInfoDetailVo();
        // 获取类目List
        List<Integer> cidList = categoryMapper.selectCategoryIdsByPid(goodsId);
        goodsInfoDetailVo.setCategoryIds(cidList);
        // 获取商品详细信息
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        goodsInfoDetailVo.setGoods(goods);
        // 获取商品参数信息
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria goodsAttributeExampleCriteria = goodsAttributeExample.createCriteria();
        goodsAttributeExampleCriteria.andGoodsIdEqualTo(goodsId);
        goodsAttributeExampleCriteria.andDeletedEqualTo(false);
        List<GoodsAttribute> goodsAttributeList = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        goodsInfoDetailVo.setAttributes(goodsAttributeList);
        // 获取规格信息
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria goodsSpecificationExampleCriteria = goodsSpecificationExample.createCriteria();
        goodsSpecificationExampleCriteria.andGoodsIdEqualTo(goodsId);
        goodsSpecificationExampleCriteria.andDeletedEqualTo(false);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        goodsInfoDetailVo.setSpecifications(goodsSpecificationList);
        // 获取商品库存信息
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria goodsProductExampleCriteria = goodsProductExample.createCriteria();
        goodsProductExampleCriteria.andGoodsIdEqualTo(goodsId);
        goodsProductExampleCriteria.andDeletedEqualTo(false);
        List<GoodsProduct> goodsProductsList = goodsProductMapper.selectByExample(goodsProductExample);
        goodsInfoDetailVo.setProducts(goodsProductsList);
        return goodsInfoDetailVo;
    }
}
