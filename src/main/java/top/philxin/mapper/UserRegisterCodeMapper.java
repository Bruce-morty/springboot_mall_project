package top.philxin.mapper;

import org.apache.ibatis.annotations.Param;
import top.philxin.model.UserRegisterCode;

import java.util.List;

public interface UserRegisterCodeMapper {

    List<UserRegisterCode> selectInfoByMobile(@Param("mobile") String mobile);

    int setRegisterCodeInfo(@Param("userRegisterCode") UserRegisterCode userRegisterCode);

    int updateRegisterCodeInfo(@Param("userRegisterCode") UserRegisterCode userRegisterCode);
}
