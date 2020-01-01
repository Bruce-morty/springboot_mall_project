package top.philxin.service.wx.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.mapper.CartMapper;
import top.philxin.mapper.GoodsMapper;
import top.philxin.mapper.GoodsProductMapper;
import top.philxin.model.*;
import top.philxin.model.WxCartModel.*;
import top.philxin.model.WxCartModel.AddGoodsVo;
import top.philxin.model.WxCartModel.CartTotalVo;
import top.philxin.model.WxCartModel.CheckProductVo;
import top.philxin.service.wx.WxCartService;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 21:04
 */
@Service
public class WxCartServiceImpl implements WxCartService {
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    CartMapper cartMapper;

    /**
     * 添加商品到购物车
     * @param addGoods
     * @return
     */
    @Override
    public int queryCartAfterAdd(AddGoodsVo addGoods) {
        //获得添加的商品的数量
        Short number = addGoods.getNumber();
        //获得goodsId 来查询goods表
        Integer goodsId = addGoods.getGoodsId();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);

        //获得productId来查询goods_product表获得specification
        Integer productId = addGoods.getProductId();
        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(productId);
        String[] specifications = goodsProduct.getSpecifications();
        String str = Arrays.toString(specifications);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Date date = new Date();
        Cart cart = new Cart(userId,
                            goodsId,
                            goods.getGoodsSn(),
                            goods.getName(),
                            productId,
                            goods.getRetailPrice(),
                            number,
                            str,
                            true,
                            goods.getPicUrl(),
                            date,
                            false);
        cartMapper.insert(cart);
        //获取整张cart表
        CartExample example = new CartExample();
        example.createCriteria().andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(example);
        int size = carts.size();
        int i = size + addGoods.getNumber();
        return i;
    }

    /**
     * 获得整个购物车中商品信息
     * @return
     */
    @Override
    public Map getAllCart() {
        Map map = new HashMap();
        Short goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal("0");
        Short checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal("0");
        int gc = 0;
        int cgc = 0;
        int ga = 0;
        int cga = 0;

        //遍历整个购物车中的商品

        CartExample cartExample1 = new CartExample();
        cartExample1.createCriteria().andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample1);
        for (Cart cart : carts) {
            gc += cart.getNumber().intValue();
            ga += (cart.getNumber().intValue())*(cart.getPrice().intValue());
        }
        CartExample cartExample2 = new CartExample();
        CartExample.Criteria criteria = cartExample2.createCriteria();
        //遍历选中的购物车中的商品
        criteria.andCheckedEqualTo(true).andDeletedEqualTo(false);
        List<Cart> cartsChecked = cartMapper.selectByExample(cartExample2);
        for (Cart cart : cartsChecked) {
            cgc += cart.getNumber().intValue();
            cga += (cart.getNumber().intValue())*(cart.getPrice().intValue());
        }
        goodsCount = (short) gc;
        checkedGoodsCount= (short)cgc;
        goodsAmount = new BigDecimal(""+ga);
        checkedGoodsAmount = new BigDecimal(""+cga);
        CartTotalVo cartTotal = new CartTotalVo(goodsCount,checkedGoodsCount,goodsAmount,checkedGoodsAmount);
        map.put("cartTotal",cartTotal);
        map.put("cartList",carts);
        return map;
    }

    /**
     * 更改当前商品的check状态
     * @param checkProduct
     * @return
     */
    @Override
    public Map checkCart(CheckProductVo checkProduct) {
        int[] productIds = checkProduct.getProductIds();
        Date date = new Date();
        for (int productId : productIds) {
            Cart cart = new Cart();
            cart.setChecked(checkProduct.getIsChecked());
            cart.setUpdateTime(date);
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andProductIdEqualTo(productId);
            cartMapper.updateByExampleSelective(cart,cartExample);
        }
        return getAllCart();
    }

    /**
     * 更改购物车中商品信息
     * @param cart
     */
    @Override
    public void changeCart(Cart cart) {
        Date date = new Date();
        cart.setUpdateTime(date);
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andIdEqualTo(cart.getId());
        cartMapper.updateByExampleSelective(cart,cartExample);
    }

    /**
     * 删除购物车中选中商品
     * @param productIdsVo
     * @return
     */
    @Override
    public Map remove(ProductIdsVo productIdsVo) {
        int[] productIds = productIdsVo.getProductIds();
        for (int productId : productIds) {
            Cart cart = new Cart();
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andProductIdEqualTo(productId);
            cart.setDeleted(true);
            cartMapper.updateByExampleSelective(cart,cartExample);
        }
        return getAllCart();
    }

    /**
     * 获得当前购物车商品总数
     * @return
     */
    @Override
    public Short countCart() {
        int i = 0;
        Short sum = 0;
        Map allCart = getAllCart();
        List<Cart> cartList = (List<Cart>) allCart.get("cartList");
        for (Cart cart : cartList) {
            i+=cart.getNumber().intValue();
        }
        sum = (short)i;
        return sum;
    }

    /**
     * 从购物车中提交订单
     * @param cartId
     * @param addressId
     * @param couponId
     * @param grouponRulesId
     * @return
     */
    @Override
    public CheckOutVo checkoutCart(int cartId, int addressId, int couponId, int grouponRulesId) {
        return null;
    }
}
