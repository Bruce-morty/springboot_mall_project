package top.philxin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.*;
import top.philxin.model.MallModel.*;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.MallService;

import java.util.List;
import java.util.Map;

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
     * 根据名称和id获取制造商信息（分页）
     * @return
     */
    @RequestMapping("brand/list")
    public BaseRespVo getBrandListByPage(BrandCondition brandCondition) {
        BaseDataVo<Brand> baseDataVo = mallService.getBrandListByPage(brandCondition);
        BaseRespVo baseRespVo = BaseRespVo.success(baseDataVo);
        return baseRespVo;
    }
    /**
     * 此方法用于新增品牌制造商
     * @param brand
     * @return
     */
    @RequestMapping("brand/create")
    public BaseRespVo createBrand(@RequestBody Brand brand) {
        Brand newBrand = mallService.createBrand(brand);
        return  BaseRespVo.success(newBrand);
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

    /**
     * 此方法用于删除商品类目
     * @return
     */
    @RequestMapping("category/delete")
    public BaseRespVo deleteCategory(@RequestBody Category category) {
        mallService.deleteCategory(category.getId());
        return BaseRespVo.success();
    }

    /**
     * 此方法用于新增商品类目
     * @return
     */
    @RequestMapping("category/create")
    public BaseRespVo createCategory(@RequestBody Category category) {
        category = mallService.createCategory(category);
        return BaseRespVo.success(category);
    }

    /**
     * 此方法用于更新商品类目
     * @return
     */
    @RequestMapping("/category/update")
    public BaseRespVo updateCategory(@RequestBody Category category) {
        mallService.updateCategory(category);
        return BaseRespVo.success();
    }

    /**
     * 此方法根据用户id，订单编号以及订单状态分页获得订单
     * @return
     */
    @RequestMapping("/order/list")
    public BaseRespVo getOrderListByCondition(OrderCondition orderCondition){
        BaseDataVo<Order> baseDataVo = mallService.getOrderListByPage(orderCondition);
        return BaseRespVo.success(baseDataVo);
    }

    /**
     * 此方法根据订单的id获取订单详情
     * @param id
     * @return
     */
    @RequestMapping("/order/detail")
    public BaseRespVo getOrderDetail(int id) {
        Map<String, Object> result = mallService.getOrderDetail(id);
        return BaseRespVo.success(result);
    }

    /**
     * 此方法用于实现退款，即将order表中的order_status置为203
     * @param map
     * @return
     */
    @RequestMapping("/order/refund")
    public BaseRespVo refund(@RequestBody Map map) {
        mallService.refund((Integer) map.get("orderId"));
        return BaseRespVo.success();
    }
    /**
     * 此方法用于实现商品发货
     * @param map
     * @return
     */
    @RequestMapping("/order/ship")
    public BaseRespVo ship(@RequestBody Map map) {
        mallService.ship(map);
        return BaseRespVo.success();
    }

    /**
     * 此方法用于根据问题获得通用问题的分页数据
     * @return
     */
    @RequestMapping("/issue/list")
    public BaseRespVo getIssueListByQuestion(IssueCondition issueCondition) {
        BaseDataVo<Issue> baseDataVo = mallService.getIssueListByQuestion(issueCondition);
        return BaseRespVo.success(baseDataVo);
    }

    /**
     * 此方法用于新增通用问题，并将刚生成的问题返回
     * @param issue
     * @return
     */
    @RequestMapping("/issue/create")
    public BaseRespVo createIssue(@RequestBody Issue issue) {
        issue = mallService.createIssue(issue);
        return  BaseRespVo.success(issue);
    }

    /**
     * 此方法用于更新通用问题，并将其返回
     * @param issue
     * @return
     */
    @RequestMapping("/issue/update")
    public BaseRespVo updateIssue(@RequestBody Issue issue) {
        issue = mallService.updateIssue(issue);
        return  BaseRespVo.success(issue);
    }

    /**
     * 此方法用于删除通用问题，
     * 将deleted设为1
     * @param issue
     * @return
     */
    @RequestMapping("/issue/delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue) {
        mallService.deleteIssue(issue.getId());
        return  BaseRespVo.success();
    }

    /**
     * 此方法用于根据关键词和跳转链接分页查询关键词
     * 均为模糊查询
     * @return
     */
    @RequestMapping("/keyword/list")
    public BaseRespVo getKeywordListByCondition(KeywordCondition keywordCondition) {
        BaseDataVo<Keyword> baseDataVo = mallService.getKeywordListByCondition(keywordCondition);
        return BaseRespVo.success(baseDataVo);
    }

    /**
     * 此方法用于新增关键词，并将新增的关键词返回
     * @param keyword
     * @return
     */
    @RequestMapping("/keyword/create")
    public BaseRespVo createKeyword(@RequestBody Keyword keyword) {
        keyword = mallService.createKeyword(keyword);
        return BaseRespVo.success(keyword);
    }

    /**
     * 此方法用于更新关键词，并将其返回
     * @param keyword
     * @return
     */
    @RequestMapping("/keyword/update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword) {
        keyword = mallService.updateKeyword(keyword);
        return  BaseRespVo.success(keyword);
    }

    /**
     * 此方法用于删除关键词，
     * 将deleted设为1
     * @param keyword
     * @return
     */
    @RequestMapping("/keyword/delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword) {
        mallService.deleteKeyword(keyword.getId());
        return  BaseRespVo.success();
    }
}
