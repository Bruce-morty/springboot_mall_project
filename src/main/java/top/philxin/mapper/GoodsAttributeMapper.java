package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.GoodsAttribute;
import top.philxin.model.GoodsAttributeExample;

import java.util.List;

public interface GoodsAttributeMapper {
    long countByExample(GoodsAttributeExample example);

    int deleteByExample(GoodsAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    List<GoodsAttribute> selectByExample(GoodsAttributeExample example);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByExample(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);

    int insertAttributeList(@Param("attributes") List<GoodsAttribute> attributesToInsert);

    int updateAttributeList(@Param("attributes") List<GoodsAttribute> attributesToUpdate, @Param("idStr") String idStr,@Param("goodId") Integer id);

    int updateAttributeDeletedCondition(@Param("idList") List<Integer> idList, @Param("goodId") Integer id);
}
