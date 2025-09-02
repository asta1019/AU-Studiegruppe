package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHero implements Hero {
    private String type;
    private int mana;
    private int health;

    public StandardHero(String type, int mana, int health) {
        this.type = type;
        this.mana = mana;
        this.health = health;
    }

    @Override
    public int getMana() {
        return this.mana;}

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean canUsePower() {
        return false;
    }

    @Override
    public String getType() {
        return type;
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
