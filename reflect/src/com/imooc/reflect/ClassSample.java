package com.imooc.reflect;

import com.imooc.reflect.entity.Employee;

public class ClassSample {
    public static void main(String[] args) {
        try {
            //将指定的类加载到jvm,并返回对应的class对象
            Class employeeClass = Class.forName("com.imooc.reflect.entity.Employee");
            System.out.println("Employee已加载jvm");
            Employee employee = (Employee) employeeClass.newInstance();
            System.out.println(employee);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
