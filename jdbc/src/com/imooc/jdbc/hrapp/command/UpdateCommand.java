package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateCommand implements  Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入员工编号：");
        int id = in.nextInt();
        System.out.print("请输入员工新的薪资：");
        float newSalary = in.nextFloat();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
             conn = DbUtils.getConnection();
            String sql = "update employee set salary=? where id=?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setFloat(1, newSalary);
             pstmt.setInt(2 , id);
           int cnt = pstmt.executeUpdate();
            if (cnt > 0) {
                System.out.println(id + "员工调薪已完成");
            } else{
                System.out.println(id + "员工找不到");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
