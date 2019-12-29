package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.philxin.mapper.*;
import top.philxin.model.*;
import top.philxin.model.Goods;
import top.philxin.model.GoodsModel.*;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.GoodsService;

import java.util.*;

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
    BrandMapper brandMapper;

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
        List<Integer> cidList = new ArrayList<>();
        Integer categoryId = goodsMapper.getCategoryIdByGoodsId(goodsId);
        Integer pid = categoryMapper.getPidById(categoryId);
        cidList.add(pid);
        cidList.add(categoryId);
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

    /**
     * 获取规格信息和品牌信息
     * @return
     */
    @Override
    public CatAndBrandVo getCatAndBrand() {
        CatAndBrandVo catAndBrandVo = new CatAndBrandVo();
        // 获取规格的所有信息
        List<CatAndBrandInfoVo> catInfo = categoryMapper.selectCategoryList();
        catAndBrandVo.setCategoryList(catInfo);
        // 获取品牌的所有信息
        List<CatAndBrandChildInfoVo> brandInfo = brandMapper.selectBrandList();
        catAndBrandVo.setBrandList(brandInfo);
        return catAndBrandVo;
    }

    /**
     * 编辑更新商品详细信息
     * @param goodsInfoDetailVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGoodsDetailInfo(GoodsInfoDetailVo goodsInfoDetailVo) {
        // 获取当前时间
        Date date = new Date();

        // 更新商品详细信息
        Goods goods = goodsInfoDetailVo.getGoods();
        goodsMapper.updateByPrimaryKeyWithBLOBs(goods);

        // 更新商品参数信息
        List<GoodsAttribute> attributes = goodsInfoDetailVo.getAttributes();
        List<GoodsAttribute> attributesToInsert = new ArrayList<>();
        List<GoodsAttribute> attributesToUpdate = new ArrayList<>();
        List<Integer> idList = new ArrayList();
        for (GoodsAttribute attribute : attributes) {
            if(attribute.getId() == null) {
                attribute.setGoodsId(goods.getId());
                attribute.setAddTime(date);
                attribute.setUpdateTime(date);
                attribute.setDeleted(false);
                attributesToInsert.add(attribute);
                continue;
            }
            attribute.setUpdateTime(date);
            attributesToUpdate.add(attribute);
            idList.add(attribute.getId());
        }
//        String idStr = idList.toString();
//        idStr = "(" + idStr.substring(1,idStr.length() - 1) + ")";
        goodsAttributeMapper.updateAttributeDeletedCondition(idList,goods.getId());
        // 更新新增的信息
        if(!attributesToInsert.isEmpty()) {
            goodsAttributeMapper.insertAttributeList(attributesToInsert);
        }
        // 更新修改的信息
//        if(!attributesToUpdate.isEmpty()) {
//            goodsAttributeMapper.updateAttributeList(attributesToUpdate, idStr,goods.getId());
//        }

        // 更新商品规格信息
        List<GoodsSpecification> specifications = goodsInfoDetailVo.getSpecifications();
        List<GoodsSpecification> specificationListToInsert = new ArrayList<>();
        List<GoodsSpecification> specificationListToUpdate = new ArrayList<>();
        List<Integer> idList2 = new ArrayList();
        for (GoodsSpecification specification : specifications) {
            if(specification.getId() == null) {
                specification.setGoodsId(goods.getId());
                specification.setAddTime(date);
                specification.setUpdateTime(date);
                specification.setDeleted(false);
                specificationListToInsert.add(specification);
                continue;
            }
            specification.setUpdateTime(date);
            specificationListToUpdate.add(specification);
            idList2.add(specification.getId());
        }
//        String idStr2 = idList2.toString();
//        idStr2 = "(" + idStr2.substring(1,idStr2.length() - 1) + ")";
        // 更新需要删除的信息
        goodsSpecificationMapper.updateSpecificationDeletedCondition(idList2,goods.getId());
        // 更新新增的信息
        if(!specificationListToInsert.isEmpty()) {
            goodsSpecificationMapper.insertSpecificationList(specificationListToInsert);
        }
//        // 更新修改的信息
//        if(!specificationListToUpdate.isEmpty()) {
//            goodsSpecificationMapper.updateSpecificationList(specificationListToUpdate, idStr2,goods.getId());
//        }

        // 更新商品库存信息(前端不支持多个更改)
        List<GoodsProduct> products = goodsInfoDetailVo.getProducts();
        List<GoodsProductVo> productListToInsert = new ArrayList<>();
        List<GoodsProduct> productListToUpdate = new ArrayList<>();
        for (GoodsProduct product : products) {
            GoodsProductVo productVo = new GoodsProductVo();
            if(products.get(0).getId() == 0) {
                productVo.setSpecifications(Arrays.toString(product.getSpecifications()));
                productVo.setPrice(product.getPrice());
                productVo.setNumber(product.getNumber());
                productVo.setUrl(product.getUrl());
                productVo.setGoodsId(goods.getId());
                productVo.setAddTime(date);
                productVo.setUpdateTime(date);
                productVo.setDeleted(false);
                productListToInsert.add(productVo);
                continue;
            }
            product.setUpdateTime(date);
            productListToUpdate.add(product);
        }
        // 更新需要删除的信息
        if(products.get(0).getId() == 0) {
            goodsProductMapper.updateProductDeletedCondition(goods.getId());
            // 更新新增的信息
            if(!productListToInsert.isEmpty()) {
                goodsProductMapper.insertProductList(productListToInsert);
            }
        }else {
            // 更新已有的库存信息
            goodsProductMapper.updateProductList(productListToUpdate, goods.getId());
        }
    }

    /**
     * 新增商品
     * @param goodsInfoDetailVo
     */
    @Override
    public void createNewGoods(GoodsInfoDetailVo goodsInfoDetailVo) {
        // 获取当前时间
        Date date = new Date();

        // 新增商品详细信息
        Goods goods = goodsInfoDetailVo.getGoods();
        goods.setAddTime(date);
        goods.setUpdateTime(date);
        goods.setDeleted(false);
        goodsMapper.insert(goods);

        // 新增商品属性信息
        List<GoodsAttribute> attributes = goodsInfoDetailVo.getAttributes();
        List<GoodsAttribute> attributesToInsert = new ArrayList<>();
        for (GoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            attribute.setAddTime(date);
            attribute.setUpdateTime(date);
            attribute.setDeleted(false);
            attributesToInsert.add(attribute);
        }
        goodsAttributeMapper.insertAttributeList(attributesToInsert);

        // 新增商品规格信息
        List<GoodsSpecification> specifications = goodsInfoDetailVo.getSpecifications();
        List<GoodsSpecification> specificationListToInsert = new ArrayList<>();
        for (GoodsSpecification specification : specifications) {
                specification.setGoodsId(goods.getId());
                specification.setAddTime(date);
                specification.setUpdateTime(date);
                specification.setDeleted(false);
                specificationListToInsert.add(specification);
            }
        goodsSpecificationMapper.insertSpecificationList(specificationListToInsert);

        // 新增商品库存信息
        List<GoodsProduct> products = goodsInfoDetailVo.getProducts();
        List<GoodsProductVo> productListToInsert = new ArrayList<>();
        for (GoodsProduct product : products) {
            GoodsProductVo productVo = new GoodsProductVo();
            if (product.getId() == 0) {
                productVo.setSpecifications(Arrays.toString(product.getSpecifications()));
                productVo.setPrice(product.getPrice());
                productVo.setNumber(product.getNumber());
                productVo.setUrl(product.getUrl());
                productVo.setGoodsId(goods.getId());
                productVo.setAddTime(date);
                productVo.setUpdateTime(date);
                productVo.setDeleted(false);
                productListToInsert.add(productVo);
            }
        }
        goodsProductMapper.insertProductList(productListToInsert);
    }


}
