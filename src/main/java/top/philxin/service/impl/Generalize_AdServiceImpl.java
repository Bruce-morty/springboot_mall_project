package top.philxin.service.impl;


import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.AdMapper;
import top.philxin.model.*;
import top.philxin.model.requestModel.CommonsModel.PageHelperVo;
import top.philxin.service.Generalize_AdService;

import java.util.List;

@Service
public class Generalize_AdServiceImpl implements Generalize_AdService {
     @Autowired
    AdMapper adMapper;


    @Override
    public List<Ad> getAd(PageHelperVo pageHelperVo,String name,String content) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        AdExample adExample = new AdExample();
        AdExample.Criteria criteria = adExample.createCriteria();
        //按什么排序
        adExample.setOrderByClause(pageHelperVo.getSort()+" "+pageHelperVo.getOrder());
        if(name!=null&&name.length()!=0)
        {
            criteria.andNameLike("%"+name+"%");
        }
        if(content!=null&&content.length()!=0)
        {
            criteria.andNameLike("%"+content+"%");
        }

        List<Ad> ads = adMapper.selectByExample(adExample);
        return ads;
    }
}
