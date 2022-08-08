package com.imooc.reflect;

import com.imooc.reflect.entity.Employee;

import java.lang.reflect.Constructor;

public class ConstructSample {
    public static void main(String[] args) {
        try {
            Class employeeClass = Class.forName("com.imooc.reflect.entity.Employee");
            Constructor constructor = employeeClass.getConstructor(new Class[]{
                    Integer.class, String.class, Float.class, String.class,
            });
            Employee employee = (Employee) constructor.newInstance(new Object[]{100, "李白", 4455f, "管理部"});
            System.out.println(employee);
//            System.out.println(employee.getDname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
