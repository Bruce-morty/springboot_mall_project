package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.Brand;
import top.philxin.model.BrandExample;
import top.philxin.model.GoodsModel.CatAndBrandChildInfoVo;

import java.util.List;

public interface BrandMapper {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    void deleteBrandById(Integer id);

    List<CatAndBrandChildInfoVo> selectBrandList();
}
