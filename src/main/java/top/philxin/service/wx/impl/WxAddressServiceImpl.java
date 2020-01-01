package top.philxin.service.wx.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.AddressMapper;
import top.philxin.mapper.RegionMapper;
import top.philxin.mapper.UserMapper;
import top.philxin.model.Address;
import top.philxin.model.AddressExample;
import top.philxin.model.Region;
import top.philxin.model.User;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxAddressService;

import java.util.List;

@Service
public class WxAddressServiceImpl implements WxAddressService {
    @Autowired
    AddressMapper addressMapper;

    @Autowired
    RegionMapper regionMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Address getAddressById(Integer id) {
        Address address=addressMapper.selectByPrimaryKey(id);
        //获取省市区
        address.setProvinceName(addressMapper.selectById(address.getProvinceId()));
        address.setCityName(addressMapper.selectById(address.getCityId()));
        address.setAreaName(addressMapper.selectById(address.getAreaId()));
        StringBuffer detailedAddress=new StringBuffer();
        detailedAddress.append(address.getProvinceName());
        detailedAddress.append(address.getCityName());
        detailedAddress.append(address.getAreaName());

        return address;
    }

    @Override
    public BaseRespVo getAllAddresss(Integer userId) {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria=addressExample.createCriteria();
        if(userId != null  ) {
            criteria.andUserIdEqualTo(userId);
        }
        List<Address> addressList = addressMapper.selectByExample(addressExample);
        //查询省市区，并将查询结果和地址结合
        //从addressList中获取省市区id ,foreach
        for (Address address:addressList) {
            //省
            address.setProvinceName(addressMapper.selectById(address.getProvinceId()));
            //市
            address.setCityName(addressMapper.selectById(address.getCityId()));
            //区
            address.setAreaName(addressMapper.selectById(address.getAreaId()));

            //针对单条记录将省市区连接且与地址连接
            StringBuffer detailedAddress=new StringBuffer();
            detailedAddress.append(address.getProvinceName());
            detailedAddress.append(address.getCityName());
            detailedAddress.append(address.getAreaName());
            detailedAddress.append(" ");
            detailedAddress.append(address.getAddress());
            address.setDetailedAddress(detailedAddress);
        }
        return BaseRespVo.success(addressList);
    }

    @Override
    public List<Region> getAllRegion(Integer pid) {
        List<Region> regionList = regionMapper.selectByPid(pid);
        for (Region region : regionList) {
            List<Region> cityList = regionMapper.selectByPid(pid);
            region.setChildren(cityList);
            for (Region cities : cityList) {
                cities.setChildren(regionMapper.selectByPid(pid));
            }
        }
        return regionList;
    }

    @Override
    public BaseRespVo saveOneAddress(Address address) {
        if (address.getId()==0){
            //新增
            address.setDeleted(false);
            //问题在于向address中省市区Id时传的值为pid的值，
            // 如何在选择完地址后将其对应的code传到的区域id? 改下sql语句
            addressMapper.insertSelective(address);
        }
        else {
            addressMapper.updateByPrimaryKeySelective(address);
            //获取省市区
            address.setProvinceName(addressMapper.selectById(address.getProvinceId()));
            address.setCityName(addressMapper.selectById(address.getCityId()));
            address.setAreaName(addressMapper.selectById(address.getAreaId()));
        }
        return null;
    }

    @Override
    public Address deleteAddress(Integer id) {
        //通过id获取对应记录信息
        Address address=addressMapper.selectByPrimaryKey(id);
        //将address的deleted改为true删除状态
        address.setDeleted(true);
        //修改数据库中对应的记录
        addressMapper.updateByPrimaryKeySelective(address);
        addressMapper.deleteById(id);
        return address;
    }
}
