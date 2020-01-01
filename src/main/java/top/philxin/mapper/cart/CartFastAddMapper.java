package top.philxin.mapper.cart;

import org.apache.ibatis.annotations.Param;

/**
 * @author xqs
 * @version 1.0
 * @description
 * @date 2020/1/1 21:19
 */
public interface CartFastAddMapper {
    int selectCartId(@Param("productId") int productId);
}
