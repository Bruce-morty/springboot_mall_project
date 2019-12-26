package top.philxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.mall_mapper.RegionMapper;
import top.philxin.model.MallModel.Region;
import top.philxin.service.MallService;

import java.util.List;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    RegionMapper regionMapper;

    /**
     * 获取全部的行政区域并以list的形式返回
     * @return
     */
    @Override
    public List<Region> getAllRegion() {
        List<Region> regionList = regionMapper.selectByTypeAndPid(1,0);
        for (Region region : regionList) {
            List<Region> cityList = regionMapper.selectByTypeAndPid(2,region.getId());
            region.setChildren(cityList);
            for (Region cities : cityList) {
                cities.setChildren(regionMapper.selectByTypeAndPid(3,cities.getId()));
            }
        }
        return regionList;
    }
}
