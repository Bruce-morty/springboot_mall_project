package top.philxin.model.responseModel.CommonsModel;

import lombok.Data;

/**
 * @ClassName: BaseRespVo
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

    public static BaseRespVo error(int errorNo, String message) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        BaseRespVo.setErrmsg(message);
        BaseRespVo.setErrno(errorNo);
        return BaseRespVo;
    }

    public static BaseRespVo success() {
        BaseRespVo BaseRespVo = new BaseRespVo();
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }

    public static <V> BaseRespVo<V> success(V data) {
        BaseRespVo<V> BaseRespVo = new BaseRespVo<>();
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setData(data);
        return BaseRespVo;
    }
}
