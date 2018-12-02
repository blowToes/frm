package com.example.demo.sys.mapper;

import com.example.demo.sys.entity.Role;
import com.example.demo.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-12-01
 */

public interface UserMapper extends BaseMapper<User> {




    List<Role> queryRoleByUserName(String userName);


    List<User> queryUserList();


}
