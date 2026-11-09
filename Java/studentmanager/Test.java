package application.studentmanager;

import java.util.ArrayList;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        boolean key;
        while (true) {
            System.out.println("欢迎使用学生信息管理系统");
            System.out.println("请选择操作:1.登录 2.注册 3.忘记密码 4.退出");
            int choose = sc.nextInt();
            switch (choose) {
                case 1 -> {
                    key=Enter.loginSystem(users);
                    while(key){
                        System.out.println("请输入需要的选项:");
                        System.out.println("1.添加学生信息");
                        System.out.println("2.删除学生信息");
                        System.out.println("3.修改学生信息");
                        System.out.println("4.查询学生信息");
                        System.out.println("0.退出系统");
                        int option = sc.nextInt();
                        switch (option){
                            case 1->Function.addStudent(students);
                            case 2->Function.removeStudent(students);
                            case 3->Function.changeStudent(students);
                            case 4->Function.printStudent(students);
                            case 0->key=false;
                            default-> System.out.println("输入错误,请重新输入");
                        }
                    }
                }
                case 2 -> Enter.registerSystem(users);
                case 3 -> Enter.changePassword(users);
                case 4 ->{
                    System.out.println("感谢使用学生信息管理系统,再见!");
                    return ;}
                default -> System.out.println("输入错误,请重新输入");
            }
        }
    }

}
