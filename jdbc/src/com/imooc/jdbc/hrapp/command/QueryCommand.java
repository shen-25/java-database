package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class QueryCommand implements Command {

    @Override
    public void execute() {
        System.out.print("请输入部门名称：");
        Scanner in = new Scanner(System.in);
        String dname = in.nextLine();
        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            //创建statement对象
            String sql = "select* from employee where dname=?";
            //   System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            //赋值
            pstmt.setString(1, dname);
            //rs = stmt.executeQuery(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String departname = rs.getString("dname");
                System.out.println(id + "-" + ename + "-" + salary + "-" + departname);
            }
        }
         catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeConnection(rs, pstmt, conn);
        }
    }
}

