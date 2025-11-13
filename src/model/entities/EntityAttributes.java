package model.entities;

import model.gridmap.TilePos;

public class EntityAttributes {
    public static final int MAX_MP = 50;

    public String name;
    public char gridRep;
    public int maxHP;
    public int hp;
    public int atk;
    public int def;
    public int mp;

    public int shield = 0;
    public double offenseMult = 1.0;
    public double defenseMult = 1.0;

    public boolean isDamageable;
    public TilePos pos;

    /**
     * Create a regular entity
     */
    public EntityAttributes(String name, char gridRep, int maxHp, int hp, int atk, int def, int mp, TilePos pos) {
        this.name = name;
        this.gridRep = gridRep;
        this.maxHP = maxHp;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.mp = mp;
        this.pos = new TilePos(pos);

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

    public boolean isAlive() {
        return hp > 0;
    }
}
