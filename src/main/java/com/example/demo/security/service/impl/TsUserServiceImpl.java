package com.example.demo.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.security.dto.RolePermissions;
import com.example.demo.security.entity.TsUser;
import com.example.demo.security.mapper.TsUserMapper;
import com.example.demo.security.service.ITsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.PasswordHelper;
import com.example.demo.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@Service
public class TsUserServiceImpl extends ServiceImpl<TsUserMapper, TsUser> implements ITsUserService {

    @Autowired
    private TsUserMapper userMapper;

    @Override
    public TsUser register(TsUser tsUser) {
        // 加密tsUser中的密码
        String pwd = PasswordHelper.md5PasswordGenerator(tsUser.getPwdword(), tsUser.getAccount());
        // 不全tsUser
        tsUser.setId(UUIDGenerator.generatorKey_32());
        tsUser.setPwdword(pwd);
        tsUser.setCreateTime(LocalDate.now());
        tsUser.setModifyTime(LocalDate.now());
        // 插入数据库
        userMapper.insert(tsUser);
        return tsUser;
    }

    @Override
    public List<RolePermissions> queryRolePermissions(String username) {
        return userMapper.queryRolePermissionsByUserName(username);
    }

    @Override
    public TsUser queryUserByWrraper(String username) {
        TsUser tsUser = new TsUser();
        tsUser.setAccount(username);
        QueryWrapper wrapper = new QueryWrapper(tsUser);
        return userMapper.selectOne(wrapper);
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
        String format = formatter.format(LocalDateTime.now());
        System.out.println(format);
    }


}
