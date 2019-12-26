package top.philxin.model.responseModel;

/**
 * @ClassName: DashboardVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 10:44
 * @version: v1.0
 */
public class DashboardVo {

    /**
     * goodsTotal : 239
     * userTotal : 22
     * productTotal : 244
     * orderTotal : 0
     */

    private int goodsTotal;
    private int userTotal;
    private int productTotal;
    private int orderTotal;

    public int getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(int goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    public int getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(int productTotal) {
        this.productTotal = productTotal;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }
}
