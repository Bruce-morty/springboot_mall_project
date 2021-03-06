package top.philxin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.model.responseModel.DashboardVo;
import top.philxin.service.admin.DashboardService;

/**
 * @ClassName: DashboardController
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 10:55
 * @version: v1.0
 */
@RestController
@RequestMapping("admin")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping("dashboard")
    public BaseRespVo getDashboard() {
        DashboardVo dashboard = dashboardService.getDashboard();
        return BaseRespVo.success(dashboard);
    }
}
