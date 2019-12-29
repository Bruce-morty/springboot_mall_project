package top.philxin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.philxin.model.GoodsModel.GoodsProductVo;
import top.philxin.model.GoodsProduct;
import top.philxin.model.GoodsProductExample;

import java.util.List;

@Mapper
public interface GoodsProductMapper {
    long countByExample(GoodsProductExample example);

    int deleteByExample(GoodsProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsProduct record);

    int insertSelective(GoodsProduct record);

    List<GoodsProduct> selectByExample(GoodsProductExample example);

    GoodsProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsProduct record, @Param("example") GoodsProductExample example);

    int updateByExample(@Param("record") GoodsProduct record, @Param("example") GoodsProductExample example);

    int updateByPrimaryKeySelective(GoodsProduct record);

    int updateByPrimaryKey(GoodsProduct record);

    int updateProductDeletedCondition(@Param("goodsId") Integer id);

    int insertProductList(@Param("productList") List<GoodsProductVo> productListToInsert);

    int updateProductList(@Param("productListToUpdate") List<GoodsProduct> productListToUpdate, @Param("goodsId") Integer id);

//    int updateProductList(@Param("productList") List<GoodsProduct> productListToUpdate, @Param("idStr") String idStr);
}
