package top.philxin.model.responseModel.CommonsModel;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: BaseDataVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/26 0026 17:18
 * @version: v1.0
 */
@Component
@Data
public class BaseDataVo<T>{
    private Integer total;
    private List<T> items;
}
