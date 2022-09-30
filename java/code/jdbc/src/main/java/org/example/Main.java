package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //获取连接
        String url = "jdbc:mysql://localhost:49156/learnjdbc";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,username,password);

        //定义sql语句
        String sql = "update students set score=98 where id = 1;";

        //获取执行sql对象statement
        Statement stem = conn.createStatement();

        //执行sql   -- 返回值受影响的行数 -- 如果返回为1 说明1行sql执行成功
        int count = stmt.executeUpdate(sql);

        //处理结果
        System.out.println(count);

        //释放资源 -- 先释放stml再释放conn
        stem.close();
        conn.close();
    }
}