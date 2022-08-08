package com.imooc.reflect;

import com.imooc.reflect.entity.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodSample {

    public static void main(String[] args) {
        try {
            Class  employeeClass = Class.forName("com.imooc.reflect.entity.Employee");
            Constructor constructor = employeeClass.getConstructor(new Class[]{
                    Integer.class, String.class, Float.class, String.class
            });
           Employee employee = (Employee) constructor.newInstance(new Object[]{
                    111, "呵呵", 1000.0f, "技术部"
            });
            //System.out.println(employee.updateSalary(999f));
            Method updateMethod = employeeClass.getMethod("updateSalary", new Class[]{Float.class});
            //employee那个对象示例，后一个传值
            Employee employee1 = (Employee)updateMethod.invoke(employee, new Object[]{100f});
            System.out.println(employee1);
            //System.out.println(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
