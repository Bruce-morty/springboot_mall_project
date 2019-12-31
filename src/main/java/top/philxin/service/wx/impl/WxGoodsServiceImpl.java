package top.philxin.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.*;
import top.philxin.model.*;
import top.philxin.model.WxGoodsModel.WxGoodsCommentValueVo;
import top.philxin.model.WxGoodsModel.WxGoodsDetailVo;
import top.philxin.model.WxGoodsModel.WxSpecificationValueVo;
import top.philxin.service.wx.WxGoodsService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName: WxGoodsServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 14:26
 * @version: v1.0
 */
@Service
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    IssueMapper issueMapper;


    @Override
    public WxGoodsDetailVo getGoodsDetailInfo(Integer goodsId) {
        WxGoodsDetailVo wxGoodsDetailVo = new WxGoodsDetailVo();
        // 先获取商品的详细信息
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        wxGoodsDetailVo.setInfo(goods);

        // 获取商品的规格详情(使用HashSet存储从数据库中获取的规格值)，另一种思路是去数据库查俩次（目前不知道那种是更优的方法）
        List<WxSpecificationValueVo> specificationValueVoList = new ArrayList<>();
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        criteria.andDeletedEqualTo(false);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        HashSet<String> set = new HashSet<>();
        for (GoodsSpecification specification : goodsSpecificationList) {
            set.add(specification.getSpecification());
        }
        for (String s : set) {
            WxSpecificationValueVo wxSpecificationValueVo = new WxSpecificationValueVo();
            List<GoodsSpecification> specifications = new ArrayList<>();
            wxSpecificationValueVo.setName(s);
            for (GoodsSpecification specification : goodsSpecificationList) {
                if(s.equals(specification.getSpecification())) {
                   specifications.add(specification);
                }
            }
            wxSpecificationValueVo.setValueList(specifications);
            specificationValueVoList.add(wxSpecificationValueVo);
        }
        wxGoodsDetailVo.setSpecificationList(specificationValueVoList);

        // 获取商品的属性
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria attributeExampleCriteria = goodsAttributeExample.createCriteria();
        attributeExampleCriteria.andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<GoodsAttribute> goodsAttributeList = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        wxGoodsDetailVo.setAttribute(goodsAttributeList);

        // 获取商品品牌信息
        Integer brandId = goods.getBrandId();
        if(brandId != 0) {
            Brand brand = brandMapper.selectByPrimaryKey(brandId);
            wxGoodsDetailVo.setBrand(brand);
        }else {
            wxGoodsDetailVo.setBrand(new Brand());
        }

        // 获取商品的产品信息
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria productExampleCriteria = goodsProductExample.createCriteria();
        productExampleCriteria.andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<GoodsProduct> goodsProducts = goodsProductMapper.selectByExample(goodsProductExample);
        wxGoodsDetailVo.setProductList(goodsProducts);

        // 获取常见问题
        IssueExample issueExample = new IssueExample();
        IssueExample.Criteria issueExampleCriteria = issueExample.createCriteria();
        issueExampleCriteria.andDeletedEqualTo(false);
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        wxGoodsDetailVo.setIssue(issues);

        // 获取商品评论表
        WxGoodsCommentValueVo wxGoodsCommentValueVo = new WxGoodsCommentValueVo();
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andValueIdEqualTo(goodsId).andDeletedEqualTo(false);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        wxGoodsCommentValueVo.setData(comments);
        wxGoodsCommentValueVo.setCount(comments.size());
        wxGoodsDetailVo.setComment(wxGoodsCommentValueVo);

        // 获取团购信息(此功能暂时不明确)
        wxGoodsDetailVo.setGroupon(new ArrayList<>());

        return wxGoodsDetailVo;
    }
}
