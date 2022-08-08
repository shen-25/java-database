package com.imooc.reflect;

import com.imooc.reflect.entity.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DeclaredSample {
    public static void main(String[] args) {
        try {
            Class employeeClass = Class.forName("com.imooc.reflect.entity.Employee");
            Constructor constructor = employeeClass.getConstructor(new Class[]{
                    Integer.class, String.class, Float.class, String.class,
            });
            Employee employee = (Employee) constructor.newInstance(new Object[]{100, "张三", 4455f, "管理部"});
            Field[] fields = employeeClass.getDeclaredFields();
            for (Field field : fields) {
               // System.out.println(field.getName());
                if (field.getModifiers() == 1) {//1修饰public
                    Object val = field.get(employee);
                    System.out.println(field.getName() + ":" + val);
                } else if (field.getModifiers() == 2) {
                    String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                   // System.out.println(methodName);
                   Method getMethod =  employeeClass.getMethod(methodName);
                   Object val =  getMethod.invoke(employee);
                    System.out.println(field.getName()+ ":" + val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
