package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.Region;
import top.philxin.model.RegionExample;

import java.util.List;

public interface RegionMapper {
    long countByExample(RegionExample example);

    int deleteByExample(RegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    List<Region> selectByExample(RegionExample example);

    List<Region> selectByPid(@Param("pid") Integer pid);

    List<Region> selectByTypeAndPid(@Param("type") Integer type, @Param("pid") Integer Pid);

    Region selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByExample(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}
