package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.GoodsSpecification;
import top.philxin.model.GoodsSpecificationExample;

import java.util.List;

public interface GoodsSpecificationMapper {
    long countByExample(GoodsSpecificationExample example);

    int deleteByExample(GoodsSpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecification record);

    int insertSelective(GoodsSpecification record);

    List<GoodsSpecification> selectByExample(GoodsSpecificationExample example);

    GoodsSpecification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSpecification record, @Param("example") GoodsSpecificationExample example);

    int updateByExample(@Param("record") GoodsSpecification record, @Param("example") GoodsSpecificationExample example);

    int updateByPrimaryKeySelective(GoodsSpecification record);

    int updateByPrimaryKey(GoodsSpecification record);

    Integer updateSpecificationDeletedCondition(@Param("idList") List<Integer> idList, @Param("goodsId") Integer id);

    Integer insertSpecificationList(@Param("specificationList") List<GoodsSpecification> specificationListToInsert);

    int updateSpecificationList(@Param("specificationList") List<GoodsSpecification> specificationListToUpdate, @Param("idStr") String idStr,@Param("goodsId") Integer id);
}
