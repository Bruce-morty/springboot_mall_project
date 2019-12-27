package top.philxin.model.MallModel;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BrandCondition {
    String name;

    Integer page;

    Integer limit;

    String sort;

    String order;

    Integer id;
}