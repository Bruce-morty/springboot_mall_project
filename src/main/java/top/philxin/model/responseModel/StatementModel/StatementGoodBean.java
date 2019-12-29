package top.philxin.model.responseModel.StatementModel;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */

@Data
public class StatementGoodBean {
    BigDecimal amount;
    int orders;
    String day;
    int products;
}
