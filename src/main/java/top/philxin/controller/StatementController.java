package top.philxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.model.responseModel.StatementModel.StatementBean;
import top.philxin.service.StatementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/stat")
public class StatementController {

    @Autowired
    StatementService statementService;



@RequestMapping("user")
    public BaseRespVo userStatement() {
    List<StatementBean> statementBeans = statementService.queryDateUsersNum();

    HashMap<Object, Object> map = new HashMap<>();

    ArrayList<Object> arrayList1 = new ArrayList<>();
    arrayList1.add("day");
    arrayList1.add("users");


    map.put("columns",arrayList1 );
    map.put("rows", statementBeans);
    return BaseRespVo.success(map);
}

}
