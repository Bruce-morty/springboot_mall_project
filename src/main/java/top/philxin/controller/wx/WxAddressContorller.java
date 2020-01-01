package top.philxin.controller.wx;

import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.annotation.LogRecordAnno;
import top.philxin.model.Address;
import top.philxin.model.Region;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxAddressService;
import top.philxin.service.admin.MallService;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxAddressContorller {
    @Autowired
    WxAddressService addressService;


    @RequestMapping("region/list")
    public BaseRespVo getAllRegion(Integer pid) {
        List<Region> regionList = addressService.getAllRegion(pid);
        BaseRespVo baseRespVo = BaseRespVo.success(regionList);
        return baseRespVo;
    }

    /**
     * 此方法用于查询所有地址
     * @return
     */
    @RequestMapping("address/list")
    public BaseRespVo getAllAddress(){
        //从session获取用户id
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        System.out.println(userId);
        BaseRespVo baseRespVo=addressService.getAllAddresss(userId);
        return baseRespVo;
    }

    /**
     * 查看单条地址详情
     * @param id
     * @return
     */
    @RequestMapping("address/detail")
    public BaseRespVo getAddress(Integer id){
        return BaseRespVo.success(addressService.getAddressById(id));
    }

    /**
     * 通过确认地址id是否为零来判别是新增还是修改
     * @param address
     * @return
     */
    @RequestMapping("address/save")
    @LogRecordAnno(operateAction = "新增地址")
    public  BaseRespVo saveAddress(@RequestBody Address address ,HttpServletRequest request){
        //从session获取用户id
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        System.out.println(userId);
        Integer id=address.getId();
        if (id==0){
            //新增
            address.setUserId(userId);
            addressService.saveOneAddress(address);
            //id可能和之前一样
            id=address.getId();
        }
        else {
            addressService.saveOneAddress(address);
        }
        return BaseRespVo.success(id);
    }

    /**
     * 删除地址
     * @param map
     * @return
     */
    @RequestMapping("address/delete")
    @LogRecordAnno(operateAction = "删除地址")
    public  BaseRespVo deleteAddress(@RequestBody Map map){
        Integer id = (Integer) map.get("id");
        Address address=addressService.deleteAddress(id);
        return BaseRespVo.success(address);
    }
}
