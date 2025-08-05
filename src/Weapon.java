public class Weapon extends Item {

    private double basicDmg;
    private double spellDmg;
//    private int range;

    public Weapon(String name, double basicDmg, double spellDmg) {
        super(name);
        this.basicDmg = basicDmg;
        this.spellDmg = spellDmg;
//        this.range = range;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Basic attack damage: " + getBasicDmg() + ", Spell damage: " + getSpellDmg();
    }

    public double getBasicDmg() {
        return basicDmg;
    }

    public double getSpellDmg() {
        return spellDmg;
    }

}
