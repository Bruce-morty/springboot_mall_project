package top.philxin.controller.wx;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.WxCartModel.AddGoodsVo;
import top.philxin.model.Cart;
import top.philxin.model.WxCartModel.CheckOutVo;
import top.philxin.model.WxCartModel.CheckProductVo;
import top.philxin.model.WxCartModel.ProductIdsVo;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxCartService;

import java.util.Map;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 20:57
 */
@RestController
@RequestMapping("wx/cart")
public class WxCartController {
    @Autowired
    WxCartService wxCartService;

    /**
     * 添加商品到购物车
     * @param addGoods
     * @return
     */
    @RequestMapping("add")
    public BaseRespVo addCart(@RequestBody AddGoodsVo addGoods){
        int i = wxCartService.queryCartAfterAdd(addGoods);
        if (i==-1){
            return BaseRespVo.error(501,"请登录");
        }
        return BaseRespVo.success(i);
    }

    @RequestMapping("fastadd")
    public BaseRespVo fastAdd(@RequestBody AddGoodsVo addGoodsVo){
        int i = wxCartService.fastAdd(addGoodsVo);
        return BaseRespVo.success(i);
    }

    /**
     * 获得整个购物车的商品信息
     * @return
     */
    @RequestMapping("index")
    public BaseRespVo getAllCart(){
        Map allCart = wxCartService.getAllCart();
        return BaseRespVo.success(allCart);
    }

    /**
     * 更改当前商品的check状态
     * @param checkProduct
     * @return
     */
    @RequestMapping("checked")
    public BaseRespVo checkCart(@RequestBody CheckProductVo checkProduct){
        Map map = wxCartService.checkCart(checkProduct);
        return BaseRespVo.success(map);
    }

    /**
     * 更改购物车中的商品信息
     * @param cart
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo changeCart(@RequestBody Cart cart){
        wxCartService.changeCart(cart);
        return BaseRespVo.success();
    }

    /**
     * 删除选中商品
     * @param productIdsVo
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo removeCart(@RequestBody ProductIdsVo productIdsVo){
        Map remove = wxCartService.remove(productIdsVo);
        return BaseRespVo.success(remove);
    }

    /**
     * 获得整个购物车的商品数量
     * @return
     */
    @RequestMapping("goodscount")
    public BaseRespVo countCart(){
        Short sum = wxCartService.countCart();
        return BaseRespVo.success(sum);
    }

    /**
     * 从购物车中提交订单
     * @param cartId
     * @param addressId
     * @param couponId
     * @param grouponRulesId
     * @return
     */
    @RequestMapping("checkout")
    public BaseRespVo checkoutCart(int cartId,int addressId,int couponId,int grouponRulesId){
        CheckOutVo checkOutVo = wxCartService.checkoutCart(cartId, addressId, couponId, grouponRulesId);
        //将checkout查询出的数据放入session中以供在orderSubmit中使用
        SecurityUtils.getSubject().getSession().setAttribute("checkoutData",checkOutVo);
        return BaseRespVo.success(checkOutVo);
    }
}
