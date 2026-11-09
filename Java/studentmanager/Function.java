package application.studentmanager;

import java.util.ArrayList;
import java.util.Scanner;
public class Function {
    // 全局复用Scanner,避免重复创建和输入异常
    private static final Scanner sc = new Scanner(System.in);
    public static void addStudent(ArrayList<Student> students) {
        System.out.println("请输入学生的id:");
        String id=sc.next();
        if(!students.isEmpty()){
            for(Student s:students){
                if(id.equals(s.getId())){
                    System.out.println("学生id已存在,请重新输入");
                    return;
                }
            }
        }
        System.out.println("请输入学生的姓名:");
        String name=sc.next();
        System.out.println("请输入学生的年龄:");
        int age=sc.nextInt();
        System.out.println("请输入学生的地址:");
        String address=sc.next();
        Student s=new Student(id,name,age,address);
        students.add(s);
    }
    public static void removeStudent(ArrayList<Student> students) {
        if(students.isEmpty()){
            System.out.println("学生信息为空,无法删除");
            return;
        }
        System.out.println("请输入需要删除的学生id:");
        String id=sc.next();
        int flag=0;
        for (int i = students.size()-1; i >= 0; i--) {
            Student s = students.get(i);
            if (s.getId().equals(id)) {
                students.remove(i);
                flag=1;
            }
        }
        if(flag==0){
            System.out.println("未找到该学生id,请重新输入");
        }
    }
    public static void changeStudent(ArrayList<Student> students) {
        if(students.isEmpty()){
            System.out.println("学生信息为空,无法修改");
            return;
        }
        System.out.println("请输入需要修改的学生id:");
        String id=sc.next();
        int flag=0;
        for(Student s:students) {
            if (id.equals(s.getId())) {
                System.out.println("请输入学生的姓名:");
                String name = sc.next();
                System.out.println("请输入学生的年龄:");
                int age = sc.nextInt();
                System.out.println("请输入学生的地址:");
                String address = sc.next();
                s.setName(name);
                s.setAge(age);
                s.setAddress(address);
                flag = 1;
            }
        }
        if(flag==0){
            System.out.println("未找到该学生id,请重新输入");
        }
    }
    public static void printStudent(ArrayList<Student> students) {
        if(students.isEmpty()){
            System.out.println("学生信息为空,无法查询");
            return;
        }
        System.out.println("id\t\t姓名\t\t年龄\t\t地址");
        for(Student s:students){
            System.out.print(s.getId()+"\t\t");
            System.out.print(s.getName()+"\t\t");
            System.out.print(s.getAge()+"\t\t");
            System.out.print(s.getAddress()+"\t\t");
            System.out.println();
        }
    }

}
