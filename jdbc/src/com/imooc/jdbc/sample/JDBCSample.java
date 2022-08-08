package com.imooc.jdbc.sample;

import java.sql.*;

public class JDBCSample {
    public static void main(String[] args) throws SQLException {
        //加载并注册JDBC驱动
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc", "root", "123456");
            Statement stmt = conn.createStatement();
            ResultSet rs =  stmt.executeQuery("select* from employee");
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "  ");
                System.out.print(rs.getString("ename"));
                System.out.print(rs.getFloat("salary"));
                System.out.println(rs.getString("dname"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(conn.isClosed() == false && conn != null) {
                conn.close();
            }

        }


    }
}
