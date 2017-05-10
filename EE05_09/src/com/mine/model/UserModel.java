package com.mine.model;

import com.mine.domain.User;
import com.mine.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by yuanxing on 2017/5/10.
 * 控制用户名／密码和数据库连接的校验
 */
public class UserModel {

    public User login(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where username= ? and password= ?";
        Object[] params = {user.getUsername(), user.getPassword()};
        return runner.query(sql, new BeanHandler<>(User.class), params);
    }
}
