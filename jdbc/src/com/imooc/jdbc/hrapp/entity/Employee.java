package com.imooc.jdbc.hrapp.entity;

import java.util.Date;

public class Employee {
    private Integer eno;
    private String ename;
    private float salary;
    private String dname;
    private Date date;

    public Employee() {

    }

    public Employee(Integer eno, String ename, float salary, String dname, Date date) {
        this.eno = eno;
        this.ename = ename;
        this.salary = salary;
        this.dname = dname;
        this.date = date;
    }

    public void setEno(Integer eno) {
        this.eno = eno;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return  eno + "---" + ename + "---" + salary + "---" + dname + "---" + date;
    }
}
