package model.entities;

public class EntityAttributes {
    public static final int MAX_MP = 50;

    public String name;
    public int maxHP;
    public int hp;
    public int atk;
    public int def;
    public int mp;

    public boolean isDamageable;

    /**
     * Create a regular entity
     */
    public EntityAttributes(String name, int maxHp, int hp, int atk, int def, int mp) {
        this.name = name;
        this.maxHP = maxHp;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.mp = mp;
        this.isDamageable = true;
    }

    /**
     * Create an unbreakable entity
     */
    public EntityAttributes(String name) {
        this.name = name;
        this.isDamageable = false;
    }

    /**
     * Makes a copy of the given attributes
     */
    public EntityAttributes(EntityAttributes other) {
        this.name = other.name;
        this.maxHP = other.maxHP;
        this.hp = other.hp;
        this.atk = other.atk;
        this.def = other.def;
        this.mp = other.mp;
        this.isDamageable = other.isDamageable;
    }
}
