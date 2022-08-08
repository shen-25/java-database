package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;
import com.imooc.jdbc.hrapp.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PainationCommand implements  Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入页数；");
        int page = in.nextInt();
        Connection conn = DbUtils.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        String sql = "select* from employee limit ?,10";
        try {
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, page - 1);
              rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEno(rs.getInt("id"));
                employee.setEname(rs.getString("ename"));
                employee.setSalary(rs.getFloat("salary"));
                employee.setDname(rs.getString("dname"));
                employee.setDate(rs.getDate("hireddate"));
                employeeList.add(employee);
            }
            System.out.println(employeeList.size());
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeConnection(rs, pstmt, conn);
        }
    }
}
