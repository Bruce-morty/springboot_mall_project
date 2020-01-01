package top.philxin.service.admin;

import com.sun.org.apache.xpath.internal.operations.Or;
import top.philxin.model.*;
import top.philxin.model.MallModel.*;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;

import java.util.List;
import java.util.Map;


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

    Map<String, Object> getOrderDetail(int id);

    void refund(Integer orderId);

    void ship( Map map);

    BaseDataVo<Issue> getIssueListByQuestion(IssueCondition issueCondition);

    Issue createIssue(Issue issue);

    Issue updateIssue(Issue issue);

    void deleteIssue(Integer id);

    BaseDataVo<Keyword> getKeywordListByCondition(KeywordCondition keywordCondition);

    Keyword createKeyword(Keyword keyword);

    Keyword updateKeyword(Keyword keyword);

    void deleteKeyword(Integer id);

    Category createCategory(Category category);

    Brand createBrand(Brand brand);
}
