package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.*;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.print("1-查询员工：");
        System.out.print("2-插入员工：");
        System.out.print("3-删除员工：");
        System.out.print("4-员工调薪资：");
        System.out.println("5-分页显示：");
        Scanner in = new Scanner(System.in);
        int cmd = in.nextInt();
        Command command = null;
        switch (cmd){
            case 1:
                 command = new QueryCommand();
                command.execute();
                break;
            case 2:
                command = new InsertCommand();
                command.execute();
                break;
            case 3:
                command = new DeleleteCommand();
                command.execute();
                break;
            case 4:
                command = new UpdateCommand();
                command.execute();
                break;
            case 5:
                command = new PainationCommand();
                command.execute();
                break;
            default:
                break;

        }
    }
}
