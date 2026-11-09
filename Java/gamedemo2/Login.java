package application.gamedemo2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {
    public void start() {
        ArrayList<User> list = new ArrayList<>();

        while (true) {
            System.out.println("欢迎来到文字格斗游戏");
            System.out.println("请选择操作: 1.登录 2.注册 3.退出");

            Scanner sc = new Scanner(System.in);
            String choose = sc.nextLine();
            switch (choose) {
                case "1"-> login(list);
                case "2"-> register(list);
                case "3"-> {
                    System.out.println("退出游戏，欢迎下次再来!");
                    System.exit(0);
                }
                default -> System.out.println("输入有误,请重新输入!");
            }
        }
    }
    public void register(ArrayList<User> list) {
        User u = new User();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入用户名:");
            String username = sc.nextLine();
            if (!checkLength(username, 3, 16)) {
                System.out.println("用户名长度必须在3-16之间,请重新输入!");
                continue;
            }
            if (!checkUsername(username)) {
                System.out.println("用户名只能包含字母和数字,且不能是纯数字,请重新输入!");
                continue;
            }
            if (containsUsername(username, list)) {
                System.out.println("用户名已存在,请重新输入!");
                continue;
            }
            System.out.println("用户名创建成功!");
            u.setUsername(username);
            break;
        }
        while (true) {
            System.out.println("请输入密码:");
            String password = sc.nextLine();
            System.out.println("请再次输入密码:");
            String password2 = sc.nextLine();
            if (!checkLength(password, 3, 8)) {
                System.out.println("密码长度必须在3-8之间,请重新输入!");
                continue;
            }
            if(!checkPassword(password)) {
                System.out.println("密码必须包含字母和数字,且不能包含特殊字符,请重新输入!");
                continue;
            }
            if(!password.equals(password2)) {
                System.out.println("两次输入的密码不一致,请重新输入!");
                continue;
            }
            System.out.println("密码设置成功!");
            u.setPassword(password);
            break;
        }
        list.add(u);
        System.out.println("用户注册成功!");
    }
    public void login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = sc.nextLine();
        if(!containsUsername(username, list)) {
            System.out.println("用户名不存在,请注册!");
            return;
        }
        int index = findIndex(username, list);
        User u = list.get(index);
        if(!u.isStatus()) {
            System.out.println("账号已被锁定,请联系客服!");
            return;
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入密码:");
            String password = sc.nextLine();
            while (true) {
                String rightCode=getCode();
                System.out.println("验证码是:"+rightCode);
                System.out.println("请输入验证码:");
                String code = sc.nextLine();
                if(rightCode.equalsIgnoreCase(code)) {
                    System.out.println("验证码输入正确!");
                    break;
                }else {
                    System.out.println("验证码输入错误!");
                }
            }
            if(password.equals(u.getPassword())) {
                System.out.println("登录成功!");
                FightingGame fg = new FightingGame();
                fg.gameStart(username);
                break;
            }else{
                System.out.println("密码输入错误!");
                if(i==2) {
                    u.setStatus(false);
                    System.out.println("账号已被锁定,请联系客服!");
                }else {
                    System.out.println("您还有" + (2 - i) + "次机会,请重新输入!");
                }
            }
        }
    }
    public boolean checkLength(String username, int minLength ,int maxLength) {
        return  (username.length() >= minLength && username.length() <= maxLength);
    }
    public int[] getCount(String userInfo) {
        int charCount=0;
        int numCount=0;
        int otherCount=0;
        for (int i = 0; i < userInfo.length(); i++) {
            char c=userInfo.charAt(i);
            if((c>='a'&&c<='z')||(c>='A'&&c<='Z')) {
                charCount++;
            }else if((c>='0'&&c<='9')) {
                numCount++;
            }else{
                otherCount++;
            }
        }
        return new int[]{charCount,numCount,otherCount};
    }
    public boolean checkUsername(String username) {
        int[] arr = getCount(username);
        return arr[0]>0&&arr[1]>=0&&arr[2]==0;
    }
    public boolean containsUsername(String username, ArrayList<User> list) {
        for (User user : list) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkPassword(String password) {
        int[] arr = getCount(password);
        return arr[0]>0&&arr[1]>0&&arr[2]==0;
    }
    public static String getCode(){
        Random r = new Random();
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
    public int findIndex(String username, ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }
}
