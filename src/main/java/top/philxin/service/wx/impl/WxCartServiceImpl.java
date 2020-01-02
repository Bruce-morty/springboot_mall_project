package top.philxin.service.wx.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.philxin.exception.GlobalExceptionHandler;
import top.philxin.mapper.*;
import top.philxin.mapper.cart.CartFastAddMapper;
import top.philxin.model.*;
import top.philxin.model.WxCartModel.*;
import top.philxin.model.WxCartModel.AddGoodsVo;
import top.philxin.model.WxCartModel.CartTotalVo;
import top.philxin.model.WxCartModel.CheckProductVo;
import top.philxin.service.wx.WxCartService;

import java.io.Serializable;
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

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    CouponUserMapper couponUserMapper;

    @Autowired
    CartFastAddMapper cartFastAddMapper;

    /**
     * 添加商品到购物车
     * @param addGoods
     * @return
     */
    @Override
    public int queryCartAfterAdd(AddGoodsVo addGoods) {
        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("userId");
        if (userId == null){
            return -1;
        }
        Cart cart = null;

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
        Date date = new Date();
        //判断cart表中是否已经存在该productId的商品
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andProductIdEqualTo(productId).andUserIdEqualTo(userId);
        List<Cart> carts1 = cartMapper.selectByExample(cartExample);
        if (carts1.size()==0){
                cart = new Cart(userId,
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
        }else {
            //更新cart表中已存在商品的数量
            Cart cart1 = carts1.get(0);
            int i = cart1.getNumber().intValue() + number.intValue();
            cart1.setNumber((short)i);
            CartExample cartExample1 = new CartExample();
            cartExample1.createCriteria().andProductIdEqualTo(productId).andUserIdEqualTo(userId);
            cartMapper.updateByExampleSelective(cart1,cartExample1);

        }
        cartMapper.insert(cart);
        //获取整张cart表
        CartExample example = new CartExample();
        example.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        List<Cart> carts = cartMapper.selectByExample(example);
        int num = 0;
        for (Cart cart1 : carts) {
            num+=cart1.getNumber().intValue();
        }
        //获得商品总数
        Short count = (short)num;
        return count;
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
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        CartExample cartExample1 = new CartExample();
        cartExample1.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        List<Cart> carts = cartMapper.selectByExample(cartExample1);
        for (Cart cart : carts) {
            gc += cart.getNumber().intValue();
            ga += (cart.getNumber().intValue())*(cart.getPrice().intValue());
        }
        CartExample cartExample2 = new CartExample();
        CartExample.Criteria criteria = cartExample2.createCriteria();
        //遍历选中的购物车中的商品
        criteria.andCheckedEqualTo(true).andDeletedEqualTo(false).andUserIdEqualTo(userId);
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
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        int[] productIds = checkProduct.getProductIds();
        Date date = new Date();
        for (int productId : productIds) {
            Cart cart = new Cart();
            cart.setChecked(checkProduct.getIsChecked());
            cart.setUpdateTime(date);
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andProductIdEqualTo(productId).andUserIdEqualTo(userId);
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
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Date date = new Date();
        cart.setUpdateTime(date);
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andIdEqualTo(cart.getId()).andUserIdEqualTo(userId);
        cartMapper.updateByExampleSelective(cart,cartExample);
    }

    /**
     * 删除购物车中选中商品
     * @param productIdsVo
     * @return
     */
    @Override
    public Map remove(ProductIdsVo productIdsVo) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        int[] productIds = productIdsVo.getProductIds();
        for (int productId : productIds) {
            Cart cart = new Cart();
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andProductIdEqualTo(productId).andUserIdEqualTo(userId);
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
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        List<Cart> checkedGoodsList = null;

        Address checkedAddress = null;
                //订单总金额
        BigDecimal orderTotalPrice = new BigDecimal("0");
        int otp = 0;
        //运费
        BigDecimal freightPrice = new BigDecimal("0");
        //优惠券优惠金额
        BigDecimal couponPrice = new BigDecimal("0");
        //满足优惠券的最低金额要求
        BigDecimal min = new BigDecimal("0");
        //团购优惠金额
        BigDecimal grouponPrice = new BigDecimal("0");
        //实际金额
        BigDecimal actualPrice = new BigDecimal("0");
        //购物车所有商品金额（不含运费）
        BigDecimal goodsTotalPrice = new BigDecimal("0");
        //优惠券可用时间
        Short availableCouponLength = 0;
        int gtp = 0;


        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(true);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        if (addresses!=null){
            checkedAddress = addresses.get(0);
        }


        /*//团购金额,这里目前还未判断是否符合团购条件
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(grouponRulesId);
        grouponPrice = grouponRules.getDiscount();*/

        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andCheckedEqualTo(true).andDeletedEqualTo(false).andUserIdEqualTo(userId);
        //获得选中的商品列表信息
        if (cartId!=0){
            Cart cart = cartMapper.selectByPrimaryKey(cartId);
            gtp=(cart.getPrice().intValue())*(cart.getNumber().intValue());
            checkedGoodsList.add(cart);
        }else {
            checkedGoodsList = cartMapper.selectByExample(cartExample);
            for (Cart cart : checkedGoodsList) {
                gtp += (cart.getPrice().intValue()) * (cart.getNumber().intValue());
            }
        }
        goodsTotalPrice = new BigDecimal("" + gtp);

        if (couponId!=0){
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            min = coupon.getMin();
            int i = goodsTotalPrice.compareTo(min);
            if (i>0){
                couponPrice = coupon.getDiscount();
            }
            availableCouponLength = coupon.getDays();
        }
        if (gtp<88){
            freightPrice = new BigDecimal("10");
        }
        //订单金额
        orderTotalPrice = goodsTotalPrice.add(freightPrice);
        //实际金额
        actualPrice=orderTotalPrice.subtract(couponPrice).subtract(grouponPrice);
        CheckOutVo checkOutVo = new CheckOutVo(grouponPrice,
                                                grouponRulesId,
                                                checkedAddress,
                                                actualPrice,
                                                orderTotalPrice,
                                                couponPrice,
                                                availableCouponLength,
                                                couponId,
                                                freightPrice,
                                                checkedGoodsList,
                                                goodsTotalPrice,
                                                addressId);
        return checkOutVo;
    }

    @Override
    public int fastAdd(AddGoodsVo addGoodsVo) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Address address = null;
        if(userId == null) {
            return -1;
        }
        queryCartAfterAdd(addGoodsVo);
        Integer productId = addGoodsVo.getProductId();
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andProductIdEqualTo(productId);
        //看购物车是否存在该商品，若存在则不再向购物车添加，否则添加
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        if (carts.size()==0){
            queryCartAfterAdd(addGoodsVo);
        }
        int cartId = cartFastAddMapper.selectCartId(productId);
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        if (addresses!=null){
            address = addresses.get(0);
        }

        return cartId;
    }

}
