package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.common.DbUtils;
import com.imooc.jdbc.hrapp.entity.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.List;

public class DbUtilsSample {
    public static void query() {
        Properties properties = new Properties();
        String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            QueryRunner qr = new QueryRunner(dataSource);
             List<Employee> employeeList  = (List<Employee>) qr.query("select* from employee limit ?, 10", new BeanListHandler<>(Employee.class),
                     new Object[]{10});
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void update() {
        Properties properties = new Properties();
        String propertyFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        Connection conn = null;
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn= dataSource.getConnection();
            conn.setAutoCommit(false);
            String sql = "update employee set salary = 10 where eno = ?";
            QueryRunner qr = new QueryRunner();
            qr.update(conn, sql, new Object[]{4000} );
            //qr.update(conn, sql2, new Object[]{100});
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(conn.isClosed() == false && conn != null) {
                    conn.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    try {
                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
      }

    public static void main(String[] args) {
       // query();
        update();
    }
}
