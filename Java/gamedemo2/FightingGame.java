package application.gamedemo2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FightingGame {
    public void gameStart(String username){
        System.out.println(username+"欢迎来到文字格斗游戏!");
        HeroCharacter player=createPlayerCharacter();
        System.out.println("角色创建成功!");
        System.out.println("您的角色属性:"+player.show());
        System.out.println("您的技能:"+player.showSkill());

        ArrayList<EnemyCharacter> enemyList=new ArrayList<>();
        /*enemyList.add(new EnemyCharacter("哥布林",50,8,2,"偷袭"));
        enemyList.add(new EnemyCharacter("骷髅兵",80,12,4,"骨盾"));
        enemyList.add(new EnemyCharacter("地狱犬",120,15,6,"火焰喷吐"));
        enemyList.add(new EnemyCharacter("暗影刺客",150,20,8,"暗影突袭"));*/
        enemyList.add(new EnemyCharacter("初级战士",80,15,10,"猛击"));
        enemyList.add(new EnemyCharacter("敏捷刺客",60,20,5,"快速攻击"));
        enemyList.add(new EnemyCharacter("重装坦克",120,10,20,"防御姿态"));
        enemyList.add(new EnemyCharacter("神秘法师",70,25,8,"火球术"));

        int count=1;
        int wins=0;
        while(player.isAlive()){
            if(wins!=0){
                for (EnemyCharacter c : enemyList) {
                    c.MaxHP = c.MaxHP + 10;
                    c.HP = c.MaxHP;
                    c.attack = c.attack + 3;
                    c.defense = c.defense + 2;
                    c.defending = false;
                }
            }

            Random r=new Random();
            int index=r.nextInt(enemyList.size());
            EnemyCharacter enemy=enemyList.get(index);
            System.out.println(enemy.show());

            System.out.println("----------------------------");
            System.out.println("第"+count+"场战斗开始! 对手:"+enemy.name);
            int round=1;
            while(player.isAlive()){
                System.out.println("----------------------------");
                System.out.println("第"+round+"回合开始!");
                System.out.println(getHealthBar(player.name,player.HP,player.MaxHP));
                System.out.println(getHealthBar(enemy.name,enemy.HP,enemy.MaxHP));
                platerTurn(player,enemy);
                if(!enemy.isAlive()){
                    System.out.println("你击败了"+enemy.name+"!");
                    wins++;
                    break;
                }
                enemyTurn(enemy,player);
                if(!player.isAlive()){
                    System.out.println("你被"+enemy.name+"击败了!");
                    break;
                }
                round++;
            }
            if(player.isAlive()){
                int healHP=r.nextInt(21)+20;
                player.heal(healHP);
                System.out.println("战斗结束,你恢复了"+healHP+"点生命!");
                System.out.println("当前胜场:"+wins);
                System.out.println("----------------------------");
            }
            if(player.isAlive() && wins!=0 && wins%3==0){
                System.out.println("恭喜你属性获得提升!");
                player.MaxHP+=30;
                player.attack+=10;
                player.defense+=5;
                System.out.println("最大生命值+30,攻击力+10,防御力+5");
                System.out.println("当前属性:"+player.show());
            }
            if(player.isAlive()){
                System.out.println("是否继续下一场战斗? (y/n)");
                Scanner sc = new Scanner(System.in);
                String choose=sc.nextLine();
                if("y".equalsIgnoreCase(choose)) {
                    count++;
                }else if("n".equalsIgnoreCase(choose)){
                    break;
                }else{
                    System.out.println("无效的选择,已自动调整为继续下一场战斗!");
                    count++;
                }
            }
        }

        System.out.println("----------------------------");
        System.out.println("游戏结束! 你总共赢了" + wins + "场战斗!");
        System.out.println("感谢游玩文字格斗游戏!");
        System.exit(0);
    }

    public HeroCharacter createPlayerCharacter(){
        Scanner sc = new Scanner(System.in);
        System.out.println("创建您的角色:");
        String name = sc.nextLine();
        System.out.println("您的角色名为:"+name);

        int points=20;
        System.out.println("请分配属性点(20点):");
        System.out.println("1.生命值(点数*10HP)");
        System.out.println("2.攻击力(点数*2ATK)");
        System.out.println("3.防御力(点数*1DEF)");
        String[] attributes={"生命值","攻击力","防御力"};
        int[] values=new int[3];
        for(int i=0;i<attributes.length;i++){
            System.out.println("分配点数到"+attributes[i]+"(剩余点数:"+points+"):");
            int point=sc.nextInt();
            if(point<0){
                System.out.println("属性点不能为负数,已自动调整为0");
                point=0;
            }
            if(point>points){
                System.out.println("属性点分配超过上限,已自动调整为"+points);
                point=points;
            }
            values[i]=point;
            points=points-point;
        }

        HeroCharacter player=new HeroCharacter(name,100+values[0]*10,10+values[1]*2,values[2]);
        player.skillList.add("普通攻击");
        player.skillList.add("强力一击");
        player.skillList.add("生命汲取");
        return player;
    }
    public String getHealthBar(String name,int HP,int MaxHP){
        int barLength=20;
        int filled=(int)((HP*1.0/MaxHP)*barLength);
        StringBuilder sb=new StringBuilder();
        sb.append(name).append(":[");
        for(int i=0;i<barLength;i++){
            if(i<filled){
                sb.append("█");
            }else{
                sb.append(" ");
            }
        }
        sb.append("] ").append(HP).append("/").append(MaxHP).append("HP");
        return sb.toString();
    }
    public void platerTurn(HeroCharacter player,EnemyCharacter enemy){
        System.out.println("=====你的回合=====");
        System.out.println("请选择行动:");
        System.out.println("1.普通攻击");
        System.out.println("2.强力一击");
        System.out.println("3.生命汲取");
        Scanner sc = new Scanner(System.in);
        String choose=sc.nextLine();
        switch (choose){
            case "1"->{
                int damage=calculateDamage(player.attack,enemy.defense);
                enemy.takeDamage(damage);
                System.out.println("你对"+enemy.name+"使用了普通攻击,造成了"+damage+"点伤害!");
            }
            case "2"->{
                if(player.HP>10){
                    player.takeDamage(10);
                    int damage=calculateDamage(player.attack*2,enemy.defense);
                    enemy.takeDamage(damage);
                    System.out.println("失去了10点生命,你对"+enemy.name+"使用了强力一击,造成了"+damage+"点伤害!");
                }else{
                    System.out.println("生命值不足,无法使用强力一击!");
                }
            }
            case "3"->{
                if(player.HP>10){
                    player.takeDamage(10);
                    Random r=new Random();
                    int healHP=r.nextInt(31);
                    player.heal(healHP);
                    System.out.println("失去了10点生命,你使用了生命汲取,恢复了"+healHP+"点生命!");
                }else{
                    System.out.println("生命值不足,无法使用生命汲取!");
                }
            }
            default -> {
                System.out.println("无效的选择,已自动调整为普通攻击!");
                int damage=calculateDamage(player.attack,enemy.defense);
                enemy.takeDamage(damage);
                System.out.println("你对"+enemy.name+"使用了普通攻击,造成了"+damage+"点伤害!");
            }
        }
    }
    public int calculateDamage(int attack,int defense){
        int damage=attack-defense;
        if(damage<1) damage=1;
        return damage;
    }
    private void enemyTurn(EnemyCharacter enemy, HeroCharacter player) {
        System.out.println("=====" + enemy.name + "的回合=====");
        String action="普通攻击";
        Random r=new Random();
        int num=r.nextInt(10);
        if(num>5) action=enemy.skill;
        switch(action){
            case "普通攻击"->{
                System.out.println(enemy.name+"采取了普通攻击!");
                int damage=calculateDamage(enemy.attack,player.defense);
                player.takeDamage(damage);
                System.out.println(enemy.name+"对你使用了普通攻击,造成了"+damage+"点伤害!");
            }
            case "猛击"->{
                System.out.println(enemy.name+"使用了猛击!");
                int damage=calculateDamage((int)(enemy.attack*1.5),player.defense);
                player.takeDamage(damage);
                System.out.println(enemy.name+"对你使用了猛击,造成了"+damage+"点伤害!");
            }
            case "快捷攻击"->{
                System.out.println(enemy.name+"使用了快捷攻击!");
                int damage=calculateDamage((int)(enemy.attack*0.8),player.defense);
                player.takeDamage(damage);
                System.out.println(enemy.name+"对你使用了快捷攻击,造成了"+damage+"点伤害!");
            }
            case "防御姿态"->{
                System.out.println(enemy.name+"进入了防御姿态!");
                enemy.defending=true;
            }
            case "火球术"->{
                System.out.println(enemy.name+"使用了火球术!");
                int damage=calculateDamage(enemy.attack*2,player.defense);
                player.takeDamage(damage);
                System.out.println(enemy.name+"对你使用了火球术,造成了"+damage+"点伤害!");
            }
        }
    }
}
