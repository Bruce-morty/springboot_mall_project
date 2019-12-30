package top.philxin.service.wx;

import java.util.Map;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2019/12/30 18:47
 */
public interface WxCatalogService {

    Map queryAllIndex();

    Map queryCurrentIndex(Integer id);
}
