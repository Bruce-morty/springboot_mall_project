package top.philxin.model.responseModel.StatementModel;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * @ClassName:
 * @Description: zy
 * @author:
 * @date:
 * @version: v1.0
 */

@Data
public class StatementOrderBean {

    BigDecimal amount;
    int orders;
    int customers;
    String day;
    BigDecimal pcr;

}
