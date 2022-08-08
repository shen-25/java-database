package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InsertCommand implements Command{

    @Override
    public void execute()  {

        Scanner in = new Scanner(System.in);
        System.out.print("请输入员工编号:");
        int eno = in.nextInt();
        System.out.print("请输入员工姓名：");
        String ename = in.next();
        System.out.print("请输入员工工资：");
        float salary = in.nextFloat();
        System.out.print("请输入员工隶属部门：");
        String dname = in.next();
        System.out.print("请输入入职时间：");
        String strDate = in.next();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "insert into employee(id, ename, salary, dname, hireddate) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eno);
            pstmt.setString(2, ename);
            pstmt.setFloat(3, salary);
            pstmt.setString(4, dname);
            //两步骤
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                 date = sdf.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //Java.util.Date 转为java.sql.Date
            long time = date.getTime();
            java.sql.Date sqlDate = new java.sql.Date(time);
            pstmt.setDate(5, sqlDate );
            int cnt = pstmt.executeUpdate();
            //System.out.println("cnt:" + cnt);
            System.out.println(ename + "员工已办理入职手续");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }
    }
}
