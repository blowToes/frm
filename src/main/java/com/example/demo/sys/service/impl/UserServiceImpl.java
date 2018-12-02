package com.example.demo.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.sys.entity.User;
import com.example.demo.sys.mapper.UserMapper;
import com.example.demo.sys.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-01
 */
@Service("iUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
