package DesignPatterns.Creational_Design_Patterns.Prototype_Design_Pattern;

class Character implements Cloneable {
    private String name;
    private int health;
    private int attackPower;
    private int level;

    public Character(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    @Override
    public Character clone() throws CloneNotSupportedException {
        return (Character) super.clone(); 
    }

    public void showCharacterInfo() {
        System.out.println("Character [Name=" + name + ", Health=" + health
                + ", AttackPower=" + attackPower + ", Level=" + level + "]");
    }
}

class CharacterFactory {
    private Character prototypeCharacter;

    public CharacterFactory() {
        prototypeCharacter = new Character("DefaultName", 100, 50, 1);
    }

    public Character createCharacterWithNewName(String name) throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        clonedCharacter.setName(name);
        return clonedCharacter;
    }

    public Character createCharacterWithNewLevel(int level) throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        clonedCharacter.setLevel(level);
        return clonedCharacter;
    }

    public Character createCharacterWithNewAttackPower(int attackPower) throws CloneNotSupportedException {
        Character clonedCharacter = prototypeCharacter.clone();
        clonedCharacter.setAttackPower(attackPower);
        return clonedCharacter;
    }
}

public class Prototype {
    public static void main(String[] args) {
        try {
            CharacterFactory factory = new CharacterFactory();

            Character hero = factory.createCharacterWithNewName("Warrior");
            Character boss = factory.createCharacterWithNewLevel(10);
            Character mage = factory.createCharacterWithNewAttackPower(80);

            hero.showCharacterInfo();
            boss.showCharacterInfo();
            mage.showCharacterInfo();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
