package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleleteCommand implements  Command {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入你要删除的员工编号：");
        int id = in.nextInt();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
             conn = DbUtils.getConnection();
            String sql = "delete  from employee where id=?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1,  id);
             int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                System.out.println(id + "员工已办理离职手续");
            } else {
                System.out.println(id + "编号员工不存在");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}