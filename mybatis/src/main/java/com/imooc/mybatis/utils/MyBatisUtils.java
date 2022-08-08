package com.imooc.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {
    //全局唯一
    private static SqlSessionFactory sqlSessionFactory = null;
    static{
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            //让外界知道
            throw new ExceptionInInitializerError(e);
        }
    }


    public static SqlSession openSession() {

        return sqlSessionFactory.openSession();
    }
    public static  void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}
