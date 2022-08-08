package com.imooc.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import com.imooc.mybatis.entity.GoodsDetail;
import com.imooc.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTestor {
    @Test
    public void testSqlSessionFactory() throws IOException {
            //加载xml
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            System.out.println("加载成功");
            //System.out.println(sqlSessionFactory);
            SqlSession sqlSession = null;
            try {
                //用于和数据库交互
                sqlSession = sqlSessionFactory.openSession();
                Connection connection = sqlSession.getConnection();
                System.out.println(connection);
            } catch (Exception e) {
            e.printStackTrace();
          } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
    }

    public void testMyBatisUtils() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Goods> goodsList = sqlSession.selectList("goods.selectAll");
            for(Goods goods : goodsList){
                System.out.println(goods);
            }
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 4);
            System.out.println(goods);
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public  void testSelectByPriceRange() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map param = new HashMap();
            param.put("min", 1000);
            param.put("max", 3000);
            param.put("limit", 4);
            List<Goods> goodsList = sqlSession.selectList("goods.selectByPriceRange", param);
            System.out.println(goodsList);
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectGoodsMap(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Map> list = sqlSession.selectList("goods.selectGoodsMap");
            for (Map map : list) {
                System.out.println(map.get("category_id"));
            }

        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectGoodsDTO(){

    }

    /**
     * 更新删除插入都要调用commit;
     */
    @Test
    public void testInsert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
               Goods goods = new Goods();
               goods.setTitle("我在测试插入数据");
               goods.setSubTitle("随便写写的");
               goods.setCurrentPrice(5000f);
               goods.setOriginalCost(1000f);
               goods.setDiscount(0.1f);
               goods.setIsFreeDriver(1);
               goods.setCategoryId(1);

            int cnt =  sqlSession.insert("goods.insert", goods);
            sqlSession.commit();
            System.out.println(goods.getGoodsId());

        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw  e;

        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testUpdate() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 6);
            goods.setTitle("更新测试");
            sqlSession.update("goods.update", goods);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testDelete() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            sqlSession.delete("goods.delete", 6);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
       @Test
    public void testDynamicSQL(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
             Map param = new HashMap();
            param.put("categoryId", 1);
             param.put("currentPrice", 5000);
            List<Goods> list = sqlSession.selectList("goods.dynamicSQL", param);
            System.out.println(list);
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testLv1Cache(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 1);
            sqlSession.commit();
            Goods goods1 = sqlSession.selectOne("goods.selectById", 1);
            System.out.println(goods);
            System.out.println(goods.hashCode() == goods1.hashCode());
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }


    }

    @Test
    public void testLv2Cache(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 1);
            System.out.println(goods);
            System.out.println(goods.hashCode());
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }
      @Test
    public void testOneToMany(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Goods> list = sqlSession.selectList("goods.selectOneToMany");
            System.out.println(list);
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    @Test
    public void testManyToOne(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
              List<GoodsDetail> list = sqlSession.selectList("goodsDetail.selectManyToOne");
             System.out.println();
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }
    @Test
    public void testselectPage(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            PageHelper.startPage(1, 10);
           Page<Goods> page = (Page)sqlSession.selectList("goods.selectPage");
            System.out.println(page.getPageNum());
            System.out.println();
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testBatchInsert(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Goods> goodsList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Goods goods  = new Goods();
                goods.setTitle("批处理标题" + i);
                goods.setSubTitle("二级标题" + i);
                goodsList.add(goods);
            }
            sqlSession.insert("goods.batchInsert", goodsList);
            sqlSession.commit();
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }

    @Test
    public void testBatchDelete(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                list.add(i + 7);
            }
            sqlSession.delete("goods.batchDelete", list);
            sqlSession.commit();
        } catch (Exception e) {
            throw  e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }
}
