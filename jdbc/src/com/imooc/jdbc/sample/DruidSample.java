package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.common.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DruidSample {
    public static void main(String[] args) {
        //1.加载属性文件
        Properties properties = new Properties();
         String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2.获取dataSourcs数据源对象
        DataSource dataSource = null;
        Connection conn = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
             conn = dataSource.getConnection();
            String sql = "select* from employee ";
             pstmt = conn.prepareStatement(sql);
             rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("ename"));
                System.out.println(rs.getFloat("salary"));
                System.out.println(rs.getString("dname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /**
             * 不使用连接池：conn.close()关闭连接
             * 使用连接池，conn.close()将回收到连接池
             */
            DbUtils.closeConnection(rs, pstmt, conn);
        }
    }
}
