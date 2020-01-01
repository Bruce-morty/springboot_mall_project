package top.philxin.model.WxGoodsModel;

import lombok.Data;
import top.philxin.model.Comment;

import java.util.List;

/**
 * @ClassName: WxGoodsCommentValueVo
 * @Description: TODO
 * @author: Administrator
 * @date: 2019/12/31 0031 14:17
 * @version: v1.0
 */
@Data
public class WxGoodsCommentValueVo {

    private Integer count;

    private List<Comment> data;

}
