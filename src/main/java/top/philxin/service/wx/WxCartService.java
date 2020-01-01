package top.philxin.service.wx;

import top.philxin.model.WxCartModel.*;
import top.philxin.model.Cart;
import top.philxin.model.WxCartModel.AddGoodsVo;
import top.philxin.model.WxCartModel.CheckProductVo;

import java.util.Map;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 21:04
 */
public interface WxCartService {
    //添加商品到购物车
    int queryCartAfterAdd(AddGoodsVo addGoods);

    //获取整个购物车商品
    Map getAllCart();

    //更改购物车商品check状态
    Map checkCart(CheckProductVo checkProduct);

    //更改购物车中商品信息
    void changeCart(Cart cart);

    //从购物车删除选中的商品
    Map remove(ProductIdsVo productIdsVo);

    //获得购物车中商品总数
    Short countCart();

    //从购物车中提交订单
    CheckOutVo checkoutCart(int cartId,int addressId,int couponId,int grouponRulesId);
}
