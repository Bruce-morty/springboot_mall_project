package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.MallModel.Category;
import top.philxin.model.MallModel.CategoryByLevel;
import top.philxin.model.MallModel.CategoryExample;

import java.util.List;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> getCategoryList();

    List<CategoryByLevel> selectByLevel(String level);
}
