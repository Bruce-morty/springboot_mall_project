package top.philxin.controller.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.philxin.exception.WxBusyException;
import top.philxin.model.responseModel.CommonsModel.BaseRespVo;
import top.philxin.service.wx.WxSearchService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */
@RestController
@RequestMapping("wx")
public class WxSearchController {

    @Autowired
    WxSearchService wxSearchService;

    /**
     * 搜取获取关键字
     *
     * @return
     */
    @RequestMapping("search/index")
    public BaseRespVo search() {
        Map map = wxSearchService.queryHistory();
        return BaseRespVo.success(map);
    }

    /**
     * 清除搜索历史
     *
     * @return
     */
    @RequestMapping("search/clearhistory")
    public BaseRespVo clear() {
        int i = wxSearchService.cleanHistorySearch();
        if (i == 0) {
            throw new WxBusyException();
        }
        return BaseRespVo.success();
    }

    /**
     * 联想查找
     *
     * @return
     */
    @RequestMapping("search/helper")
    public BaseRespVo helper(String keyword) {
        List<String> strings = wxSearchService.associationHelper(keyword);
        return BaseRespVo.success(strings);
    }

}
