package top.philxin.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.GoodsMapper;
import top.philxin.mapper.GrouponRulesMapper;
import top.philxin.model.*;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.service.Generalize_grouponService;

import java.util.Date;
import java.util.List;
@Service
public class Generalize_grouponServiceIMpl implements Generalize_grouponService {


    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Override
    public List<GrouponRules> queryGroupon(PageHelperVo pageHelperVo, Integer goodsId) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        GrouponRulesExample grouponRulesExample=new GrouponRulesExample();
        GrouponRulesExample.Criteria criteria = grouponRulesExample.createCriteria();

        grouponRulesExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());

        if(goodsId!=null)
        {
            criteria.andGoodsIdEqualTo(goodsId);
        }
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        return grouponRules;
    }
//是否是同一个goosid 相同返回true 否则返回false

    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public Goods selectGoodsIs(GrouponRules grouponRules) {

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
}
