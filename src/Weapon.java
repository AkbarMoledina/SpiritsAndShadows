public class Weapon extends Item {

    public enum WeaponType {
        ONE_HANDED,
        TWO_HANDED
    }

    private int atkDmg;
    private double atkSpd;
//    private int range;
    private WeaponType weaponType;

    public Weapon(String name, WeaponType weaponType, int atkDmg, double atkSpd) {
        super(name);
        this.weaponType = weaponType;
        this.atkDmg = atkDmg;
        this.atkSpd = atkSpd;
//        this.range = range;
    }

    public String getItemType() {
        if (weaponType == WeaponType.ONE_HANDED) {
            return "One Handed Weapon";
        }
        else return "Two Handed Weapon";
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Type: " + getItemType() + ", Attack damage: " + getAtkDmg() + ", Attack speed: " + getAtkSpd();
    }

    public int getAtkDmg() {
        return atkDmg;
    }

    public double getAtkSpd() {
        return atkSpd;
    }

}
