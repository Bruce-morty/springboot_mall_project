package top.philxin.model.WxGoodsModel;

import com.sun.org.glassfish.gmbal.InheritedAttribute;
import lombok.Data;
import top.philxin.model.*;

import java.util.List;

/**
 * @ClassName: WxGoodsDetailVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/30 0030 23:49
 * @version: v1.0
 */
@Data
public class WxGoodsDetailVo {

    private Integer userHasCollect;

    private List<WxSpecificationValueVo> specificationList;

    private List<Groupon> groupon;

    private List<Issue> issue;

    private WxGoodsCommentValueVo comment;

    private List<GoodsAttribute> attribute;

    private Brand brand;

    private List<GoodsProduct> productList;

    private Goods info;
}
