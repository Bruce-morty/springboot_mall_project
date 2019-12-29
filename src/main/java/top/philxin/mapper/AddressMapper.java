package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.Address;
import top.philxin.model.AddressExample;

import java.util.List;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    //List<Address> selectAddressByExample(AddressExample example);

    String selectById(Integer id);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}
