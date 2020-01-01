package top.philxin.service.wx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.CategoryMapper;
import top.philxin.model.Category;
import top.philxin.model.CategoryExample;
import top.philxin.service.wx.WxCatalogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 18:47
 */
@Service
public class WxCatalogServiceImpl implements WxCatalogService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 获得全部标签及初始标签信息
     * @return
     */
    @Override
    public Map queryAllIndex() {
        Map map = new HashMap();
        //获取每次初始的居家列表
        Category category = categoryMapper.selectByPrimaryKey(1005000);
        map.put("currentCategory",category);
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andPidEqualTo(0).andDeletedEqualTo(false);
        //获取和居家同级的列表
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        map.put("categoryList",categories);
        //获取当前列表的子列表
        CategoryExample categoryExample2 = new CategoryExample();
        CategoryExample.Criteria criteria2 = categoryExample.createCriteria();
        criteria2.andPidEqualTo(1005000).andDeletedEqualTo(false);
        List<Category> categoriesSon = categoryMapper.selectByExample(categoryExample2);
        map.put("currentSubCategory",categoriesSon);
        return map;
    }

    /**
     * 获得选中的标签信息
     * @param id
     * @return
     */
    @Override
    public Map queryCurrentIndex(Integer id) {
        Map map = new HashMap();
        //获取当前选中的列表对象
        Category category = categoryMapper.selectByPrimaryKey(id);
        map.put("currentCategory",category);
        //获取当前列表的子表
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andPidEqualTo(id).andDeletedEqualTo(false);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        map.put("currentSubCategory",categories);
        return map;
    }
}
