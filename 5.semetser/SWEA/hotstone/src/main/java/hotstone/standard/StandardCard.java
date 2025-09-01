package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class StandardCard implements Card {
    private String name;
    private int mana;
    private int attack;
    private int health;

    public StandardCard(String name, int mana, int attack, int health) {
        this.name = name;
        this.mana = mana;
        this.attack = attack;
        this.health = health;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getManaCost() {
        return this.mana;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
