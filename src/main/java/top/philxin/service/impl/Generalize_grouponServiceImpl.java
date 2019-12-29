package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.GoodsMapper;
import top.philxin.mapper.GrouponRulesMapper;
import top.philxin.model.*;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.service.Generalize_grouponService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Generalize_grouponServiceImpl implements Generalize_grouponService {


    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Override
    public Map queryGroupon(PageHelperVo pageHelperVo, Integer goodsId) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        GrouponRulesExample grouponRulesExample=new GrouponRulesExample();
        GrouponRulesExample.Criteria criteria = grouponRulesExample.createCriteria();

        grouponRulesExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());

        if(goodsId!=null)
        {
            criteria.andGoodsIdEqualTo(goodsId);
        }
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        PageInfo<GrouponRules> grouponRulesPageInfo = new PageInfo<>(grouponRules);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("items",grouponRules);
        map.put("total",grouponRulesPageInfo.getTotal());
        return map;
    }
//是否是同一个goosid 相同返回true 否则返回false

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public Goods selectGoodsIs(GrouponRules grouponRules) {
        grouponRules.setDeleted(false);
        Goods goods1 = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());

        return goods1;
    }

    @Override
    public int updateGroupon(GrouponRules grouponRules) {
        grouponRules.setUpdateTime(new Date());

       return   grouponRulesMapper.updateByPrimaryKey(grouponRules);

    }

    @Override
    public GrouponRules insertGroupon(GrouponRules grouponRules) {
        grouponRules.setAddTime(new Date());
        grouponRulesMapper.insertSelective(grouponRules);
        return grouponRules;
    }

    @Override
    public int deleteGroupon(GrouponRules grouponRules) {
          grouponRules.setDeleted(true);
        int i = grouponRulesMapper.updateByPrimaryKey(grouponRules);
        return i;
    }
}
