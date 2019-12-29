package top.philxin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.philxin.model.AllAuth;
import top.philxin.model.AllAuthExample;

/**
 * @author  xqs
 * @description 
 * @date  2019/12/28 22:24
 * @version 1.0
 */
public interface AllAuthMapper {
    long countByExample(AllAuthExample example);

    int deleteByExample(AllAuthExample example);

    int deleteByPrimaryKey(Integer primaryId);

    int insert(AllAuth record);

    int insertSelective(AllAuth record);

    List<AllAuth> selectByExample(AllAuthExample example);

    AllAuth selectByPrimaryKey(Integer primaryId);

    int updateByExampleSelective(@Param("record") AllAuth record, @Param("example") AllAuthExample example);

    int updateByExample(@Param("record") AllAuth record, @Param("example") AllAuthExample example);

    int updateByPrimaryKeySelective(AllAuth record);

    int updateByPrimaryKey(AllAuth record);
}