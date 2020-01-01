package top.philxin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.model.responseModel.StatementModel.StatementGoodBean;
import top.philxin.model.responseModel.StatementModel.StatementOrderBean;
import top.philxin.model.responseModel.StatementModel.StatementUserBean;
import top.philxin.service.admin.StatementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/stat")
public class StatementController {

    @Autowired
    StatementService statementService;


    /**
     * 用户统计模块
     *
     * @return
     */

    @RequestMapping("user")
    public BaseRespVo userStatement() {
    List<StatementUserBean> statementBeans = statementService.queryDateUsersNum();

    HashMap<Object, Object> map = new HashMap<>();

    ArrayList<Object> arrayList1 = new ArrayList<>();
    arrayList1.add("day");
    arrayList1.add("users");

    map.put("columns",arrayList1 );
    map.put("rows", statementBeans);
    return BaseRespVo.success(map);
    }

    /**
     * 订单统计模块
     *
     */

    @RequestMapping("order")
    public BaseRespVo orderStatement() {

        List<StatementOrderBean> orderBeans = statementService.queryDateOrder();

        HashMap<Object, Object> map = new HashMap<>();

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("day");
        arrayList.add("orders");
        arrayList.add("customers");
        arrayList.add("amount");
        arrayList.add("pcr");
        map.put("columns", arrayList);

        map.put("rows",orderBeans);
        return BaseRespVo.success(map);
    }

    @RequestMapping("goods")
    public BaseRespVo goodsStatement() {
        List<StatementGoodBean> goodBeans = statementService.queryGoods();

        HashMap<Object, Object> map = new HashMap<>();

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("day");
        arrayList.add("orders");
        arrayList.add("products");
        arrayList.add("amount");

        map.put("columns", arrayList);
        map.put("rows", goodBeans);
        return BaseRespVo.success(map);
    }

}
