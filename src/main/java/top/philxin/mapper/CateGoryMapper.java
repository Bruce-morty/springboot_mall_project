package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.CateGory;
import top.philxin.model.CateGoryExample;

import java.util.List;

public interface CateGoryMapper {
    long countByExample(CateGoryExample example);

    int deleteByExample(CateGoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CateGory record);

    int insertSelective(CateGory record);

    List<CateGory> selectByExample(CateGoryExample example);

    CateGory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CateGory record, @Param("example") CateGoryExample example);

    int updateByExample(@Param("record") CateGory record, @Param("example") CateGoryExample example);

    int updateByPrimaryKeySelective(CateGory record);

    int updateByPrimaryKey(CateGory record);

    List<Integer> selectCategoryIdsByPid(@Param("id") Integer id);
}
