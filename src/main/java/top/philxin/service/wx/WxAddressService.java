package top.philxin.service.wx;

import top.philxin.model.Address;
import top.philxin.model.Region;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;

import java.util.List;

public interface WxAddressService {
    Address deleteAddress(Integer id);

    Address getAddressById(Integer id);

    BaseRespVo getAllAddresss(Integer userId);

    List<Region> getAllRegion(Integer pid);

    BaseRespVo saveOneAddress(Address address);
}
