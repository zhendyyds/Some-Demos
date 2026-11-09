package application.studentmanager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Enter {
    public static final Scanner sc=new Scanner(System.in);
    public static final Random r=new Random();
    public static boolean loginSystem(ArrayList<User> users){
        System.out.println("请输入用户名:");
        String username=sc.nextLine();
        User u=findUser(users,username);
        if(u==null){
            System.out.println("用户名不存在,请注册!");
            return false;
        }

        System.out.println("请输入密码:");
        String password=sc.nextLine();

        String code=verifyCode();
        System.out.println("验证码为:"+code);
        System.out.println("请输入验证码:");
        String inputCode=sc.nextLine();
        while(!code.equals(inputCode)) {
            System.out.println("验证码错误,请重新输入:");
            inputCode=sc.nextLine();
        }

        for(int i=2;i>=0;i--){
            if(u.getPassword().equals(password)){
                System.out.println("登录成功!");
                return true;
            }
            else if(i!=0){
                System.out.println("密码错误,您还有"+i+"次机会,请重新输入:");
                password=sc.nextLine();
            }
            else{
                System.out.println("密码错误,您的账户已锁定,请联系客服处理!");
                return false;
            }
        }
        return false;
    }

    public static void registerSystem(ArrayList<User> users){
        System.out.println("请输入用户名:");
        String username=sc.nextLine();
        boolean flag=isUniqueUsername(users,username);
//        if(users.isEmpty()) flag=true;
        while(!flag){
            username=sc.nextLine();
            flag=isUniqueUsername(users,username);
        }
        boolean flag2=isLengthCompliant(users,username);
        while(!flag2){
            username=sc.nextLine();
            flag2=isLengthCompliant(users,username);
        }
        boolean flag3=isCompositionCompliant(users,username);
        while(!flag3){
            username=sc.nextLine();
            flag3=isCompositionCompliant(users,username);
        }

        System.out.println("请输入密码:");
        String password=sc.nextLine();
        System.out.println("请再次输入密码:");
        String confirmPassword=sc.nextLine();
        while(!password.equals(confirmPassword)){
            System.out.println("两次输入的密码不一致,请重新输入:");
            password=sc.nextLine();
            confirmPassword=sc.nextLine();
        }

        System.out.println("请输入身份证号码:");
        String idCard=sc.nextLine();
        while(!isIdCardCompliant(users,idCard)){
            idCard=sc.nextLine();
        }

        System.out.println("请输入电话号码:");
        String phoneNumber=sc.nextLine();
        while(!isPhoneNumberCompliant(users,phoneNumber)){
            phoneNumber=sc.nextLine();
        }

        System.out.println("注册成功!");
        User u=new User(username,password,idCard,phoneNumber);
        users.add(u);

    }

    public static void changePassword(ArrayList<User> users){
        System.out.println("请输入用户名:");
        String username=sc.nextLine();
        User u=findUser(users,username);
        if(u==null){
            System.out.println("用户名不存在,请注册!");
            return ;
        }

        System.out.println("请输入身份证号码:");
        String idCard=sc.nextLine();
        System.out.println("请输入电话号码:");
        String phoneNumber=sc.nextLine();
        while(!u.getIdCard().equals(idCard)||!u.getPhoneNumber().equals(phoneNumber)){
            System.out.println("身份证或电话号码错误,请重新输入:");
            idCard=sc.nextLine();
            phoneNumber=sc.nextLine();
        }

        System.out.println("请输入新密码:");
        String newPassword=sc.nextLine();
        System.out.println("请再次输入新密码:");
        String confirmPassword=sc.nextLine();
        while(!newPassword.equals(confirmPassword)){
            System.out.println("两次输入的密码不一致,请重新输入:");
            newPassword=sc.nextLine();
            confirmPassword=sc.nextLine();
        }
        u.setPassword(newPassword);
        System.out.println("密码修改成功!");
    }

    public static boolean isUniqueUsername(ArrayList<User> users,String username){
        if(!users.isEmpty()){
            for(User u:users){
                if(u.getUsername().equals(username)){
                    System.out.println("用户名已存在,请重新输入:");
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isLengthCompliant(ArrayList<User> users,String username){
        if(username.length()<3||username.length()>15){
            System.out.println("用户名长度必须在3-15之间,请重新输入:");
            return false;
        }
        return true;
    }
    public static boolean isCompositionCompliant(ArrayList<User> users,String username){
        char[] chars=username.toCharArray();
        boolean hasLetter=false;
        boolean hasInvalid=false;
        for(int i=0;i<chars.length;i++){
            if(chars[i]>=48&&chars[i]<=57){
                continue;
            }
            if((chars[i] >= 'A' && chars[i] <= 'Z') || (chars[i] >= 'a' && chars[i] <= 'z')) {
                hasLetter = true;
                continue;
            }
            hasInvalid=true;
            break;
        }
        if(hasLetter && !hasInvalid){
            return true;
        }
        else{
            System.out.println("用户名必须由字母和数字组成,且至少包含一个字母,请重新输入:");
            return false;
        }
    }

    public static boolean isIdCardCompliant(ArrayList<User> users,String idCard){
        if(idCard.length()!=18){
            System.out.println("身份证号码必须为18位,请重新输入:");
            return false;
        }
        char[] chars=idCard.toCharArray();
        if(chars[0]=='0'){
            System.out.println("身份证号码不能以0开头,请重新输入:");
            return false;
        }
        for(int i=0;i<chars.length-1;i++){
            if(chars[i]<48||chars[i]>57){
                System.out.println("身份证号码前17位必须为数字,请重新输入:");
                return false;
            }
        }
        if((chars[17]<'0'||chars[17]>'9')&&(chars[17]!='X'&&chars[17]!='x')) {
            System.out.println("身份证号码最后一位必须为数字或X,请重新输入:");
            return false;
        }
        return true;
    }

    public static boolean isPhoneNumberCompliant(ArrayList<User> users,String phoneNumber){
        if(phoneNumber.length()!=11){
            System.out.println("电话号码必须为11位,请重新输入:");
            return false;
        }
        char[] chars=phoneNumber.toCharArray();
        if(chars[0]=='0'){
            System.out.println("电话号码不能以0开头,请重新输入:");
            return false;
        }
        for(int i=0;i<chars.length;i++){
            if(chars[i]<48||chars[i]>57){
                System.out.println("电话号码必须为数字,请重新输入:");
                return false;
            }
        }
        return true;
    }

    public static String verifyCode(){
        char[] chs = new char[5];
        for (int i = 0; i < 5; i++) {
            if (r.nextBoolean()) {
                chs[i] = (char) (r.nextInt(26) + 'A');
            } else {
                chs[i] = (char) (r.nextInt(26) + 'a');
            }
        }
        int pos = r.nextInt(5);
        chs[pos] = (char) (r.nextInt(10) + '0');
        return new String(chs);
    }

    public static User findUser(ArrayList<User> users,String username){
        for(User u:users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
}