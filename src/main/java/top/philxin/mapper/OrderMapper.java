package top.philxin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.philxin.model.Order;
import top.philxin.model.OrderExample;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    void updateOrderState(int orderId, int status);

    List<Integer> selectOrderStatusByUserId(@Param("userId") int userId);

    List<Order> selectByStatus(@Param("status") Integer showType,@Param("userId") Integer id);

    void deleteOrder(Integer orderId);

    void prepayOrder(Integer orderId, Date date);

    void cancelOrder(Integer orderId, Date date);

    void refundOrder(Integer orderId, Date date);

    void confrimOrder(Integer orderId, Date date);
}
