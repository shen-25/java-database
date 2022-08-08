package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BatchCommand implements Command {
    @Override
    public void execute() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into employee(id, ename, salary, dname, hireddate) values(?,?,?,?,?)";

            pstmt = conn.prepareStatement(sql);
            for (int i = 4000; i < 4500; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i );
                pstmt.setFloat(3,  i * 1.9f);
                pstmt.setString(4,  "管理部门");
                //两步骤
                String strDate = "2022-10-1";
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
                pstmt.addBatch();
            }
            int[] cnt = pstmt.executeBatch();
            conn.commit();
            //System.out.println("cnt:" + cnt);
           // System.out.println(cnt);
            System.out.println("员工已办理入职手续");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }

    }

    public static void main(String[] args) {
        Command  command= new BatchCommand();
        command.execute();
    }
}
