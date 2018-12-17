package com.example.demo.test_data;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName DataTestBatch
 * @Description TO_DO
 * @Author ZHAAIKAIXUAN
 * @Date 2018/12/17 21:00
 * @Version 1.0
 */
public class DataTestBatch {
    Connection connection = null;
    PreparedStatement statement = null;

    // 开始批次
    private final static int nstart = 0;
    // 结束批次
    private final static int nend = 10;
    // 每个批次多少条
    private final static long uint = 100;

    @Before
    public void init() throws Exception {
        String sql = "INSERT INTO ts_user (id,account,nick_name,pwdword,sex,create_user)VALUES(?,?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/frm?characterEncoding=UTF8&useSSL=false&serverTimezone=GMT%2B8", "root", "123456");
        statement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
    }

    @Test
    public void JDBCInsert() throws Exception {

        long start = System.currentTimeMillis();
        for (int i = nstart; i < nend; i++) {
            long ustart = System.currentTimeMillis();
            for (long j = nstart * uint; j < nstart * uint + uint; j++) {
                insertDataBatch(j);
            }
            statement.executeBatch();
            connection.commit();
            long uend = System.currentTimeMillis();
            System.out.println(i + ".10w条数据的插入时间:" + (ustart - uend) / 1000);

        }
        long end = System.currentTimeMillis();
        System.out.println("100w条总数总耗时");

    }

    private void insertDataBatch(long j) throws SQLException {
        Date date = new Date();
        statement.setString(1, UUID.randomUUID().toString());
        statement.setString(2, "account" + j);
        statement.setString(3, "nick_name" + j);
        statement.setString(4, "pwdword" + j);
        statement.setString(5, "sex" + j);
        statement.setString(6, "create_user" + j);
        statement.addBatch();
    }

}
