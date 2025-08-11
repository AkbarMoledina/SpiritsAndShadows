public class Weapon extends Item {

    private double basicDmg;
    private double spellDmg;
    // maybe add something to reduce mana costs

    public Weapon(String name, double basicDmg, double spellDmg) {
        super(name);
        this.basicDmg = basicDmg;
        this.spellDmg = spellDmg;
    }

    @Override
    public String toString() {
        return "Weapon: " + getName() + ", Basic attack multiplier: " + getBasicDmg() + ", Spell damage multiplier: " + getSpellDmg();
    }

    public double getBasicDmg() {
        return basicDmg;
    }

    public double getSpellDmg() {
        return spellDmg;
    }

}
