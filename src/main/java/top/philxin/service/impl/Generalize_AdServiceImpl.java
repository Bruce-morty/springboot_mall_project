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
    public List<Ad> getAd(PageHelperVo pageHelperVo) {
        PageHelper.startPage(pageHelperVo.getPage(),pageHelperVo.getLimit());
        List<Ad> ads = adMapper.selectByExample(new AdExample());
        return ads;
    }
}
