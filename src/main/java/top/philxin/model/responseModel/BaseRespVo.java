package top.philxin.model.responseModel;

import lombok.Data;

/**
 * @ClassName: DataVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/25 0025 21:13
 * @version: v1.0
 */
@Data
public class BaseRespVo<T>{
    private T data;
    private String errmsg;
    private int errno;
}
