package top.philxin.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.*;

import top.philxin.model.*;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.model.responseModel.CommonsModel.BaseDataVo;
import top.philxin.service.admin.UserService;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 16:38
 * @version: v1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    FootprintMapper footprintMapper;

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Autowired
    BaseDataVo baseDataVo;

    /**
     * 获取用户信息 + 模糊查询
     * @param pageHelperVo
     * @param username
     * @param mobile
     * @return
     */
    @Override
    public BaseDataVo queryUsers(PageHelperVo pageHelperVo,String username, String mobile) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        userExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
        if(username != null && username.length() != 0) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if(mobile != null && mobile.length() != 0) {
            criteria.andMobileLike("%" + mobile + "%");
        }
        criteria.andDeletedEqualTo(false);
        List<User> userList = userMapper.selectByExample(userExample);
        // 存入 items
        baseDataVo.setItems(userList);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        long total = pageInfo.getTotal();
        // 存入 totals
        baseDataVo.setTotal((int)total);
        return baseDataVo;
    }

    @Override
    public BaseDataVo queryAddresss(PageHelperVo pageHelperVo, String userId, String name) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        addressExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
        if(userId != null && userId.length() != 0) {
            criteria.andUserIdLike("%" + userId + "%");
        }
        if(name != null && name.length() != 0) {
            criteria.andNameLike("%" + name + "%");
        }
        List<Address> addressList = addressMapper.selectByExample(addressExample);
        //查询省市区，并将查询结果和地址结合
        //从addressList中获取省市区id ,foreach
        for (Address address:addressList) {
            //省
            address.setProvince(addressMapper.selectById(address.getProvinceId()));
            //市
            address.setCity(addressMapper.selectById(address.getCityId()));
            //区
            address.setArea(addressMapper.selectById(address.getAreaId()));
        }
        // 存入 items
        baseDataVo.setItems(addressList);
        PageInfo<Address> pageInfo = new PageInfo<>(addressList);
        long total = pageInfo.getTotal();
        // 存入 totals
        baseDataVo.setTotal((int)total);
        return baseDataVo;
    }

    @Override
    public BaseDataVo queryCollects(PageHelperVo pageHelperVo, String userId, String valueId) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        collectExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
        if(userId != null && userId.length() != 0) {
            criteria.andUserIdLike("%" + userId + "%");
        }
        if(valueId != null && valueId.length() != 0) {
            criteria.andValueIdLike("%" + valueId + "%");
        }
        List<Collect> collectList = collectMapper.selectByExample(collectExample);
        // 存入 items
        baseDataVo.setItems(collectList);
        PageInfo<Collect> pageInfo = new PageInfo<>(collectList);
        long total = pageInfo.getTotal();
        // 存入 totals
        baseDataVo.setTotal((int)total);
        return baseDataVo;
    }

    @Override
    public BaseDataVo queryFeedbacks(PageHelperVo pageHelperVo, String username, String id) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        FeedbackExample feedbackExample = new FeedbackExample();
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        feedbackExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
        if(username != null && username.length() != 0) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if(id != null && id.length() != 0) {
            criteria.andIdLike("%" + id + "%");
        }
        List<Feedback> feedbackList = feedbackMapper.selectByExample(feedbackExample);
        // 存入 items
        baseDataVo.setItems(feedbackList);
        PageInfo<Feedback> pageInfo = new PageInfo<>(feedbackList);
        long total = pageInfo.getTotal();
        // 存入 totals
        baseDataVo.setTotal((int)total);
        return baseDataVo;
    }

    @Override
    public BaseDataVo queryFootprints(PageHelperVo pageHelperVo, String userId, String goodsId) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        FootprintExample footprintExample = new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        footprintExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
        if(userId != null && userId.length() != 0) {
            criteria.andUserIdLike("%" + userId + "%");
        }
        if(goodsId != null && goodsId.length() != 0) {
            criteria.andGoodsIdLike("%" + goodsId + "%");
        }
        List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);
        // 存入 items
        baseDataVo.setItems(footprintList);
        PageInfo<Footprint> pageInfo = new PageInfo<>(footprintList);
        long total = pageInfo.getTotal();
        // 存入 totals
        baseDataVo.setTotal((int)total);
        return baseDataVo;
    }

    @Override
    public BaseDataVo querySearchHistory(PageHelperVo pageHelperVo, String userId, String keyword) {
        // 开启分页
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria = searchHistoryExample.createCriteria();
        searchHistoryExample.setOrderByClause(pageHelperVo.getSort() + " " + pageHelperVo.getOrder());
        if(userId != null && userId.length() != 0) {
            criteria.andUserIdLike("%" + userId + "%");
        }
        if(keyword != null && keyword.length() != 0) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        List<SearchHistory> searchHistoryList = searchHistoryMapper.selectByExample(searchHistoryExample);
        // 存入 items
        baseDataVo.setItems(searchHistoryList);
        PageInfo<SearchHistory> pageInfo = new PageInfo<>(searchHistoryList);
        long total = pageInfo.getTotal();
        // 存入 totals
        baseDataVo.setTotal((int)total);
        return baseDataVo;
    }


}
