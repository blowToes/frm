package com.example.demo.security.service.impl;

import com.example.demo.security.entity.TsUser;
import com.example.demo.security.mapper.TsUserMapper;
import com.example.demo.security.service.ITsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@Service
public class TsUserServiceImpl extends ServiceImpl<TsUserMapper, TsUser> implements ITsUserService {

}
