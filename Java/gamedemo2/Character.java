package application.gamedemo2;

public class Character {
    public String name;
    public int HP;
    public int MaxHP;
    public int attack;
    public int defense;

    public Character() {}

    public Character(String name,int HP,int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
        this.HP = HP;
        this.MaxHP = HP;
        this.name = name;
    }

    public boolean isAlive() {
        return HP>0;
    }

    public void heal(int amount) {
        HP += amount;
        if(HP > MaxHP) HP = MaxHP;
    }

    public void takeDamage(int damage) {
        HP -= damage;
        if(HP<0) HP = 0;
    }

    public String show(){
        return name+"[生命值:"+HP+" 攻击力:"+attack+" 防御力:"+defense+"]";
    }
}
