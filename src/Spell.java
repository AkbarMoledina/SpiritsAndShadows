import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spell{

    private String name;
    private String description;
    private double damage;
    private double percentageDamage;
    private int cooldown;
    private double accuracy;
    private double manaCost;
    private spellType spellType;
    private List<SpellEffect> effects = new ArrayList<>();


    public enum spellType {
        PYRO, ICE, EARTH, SHOCK, BLOOD, SPIRIT, SHADOW
    }

    public Spell(String name, String description, double dmg, double pctDmg, int cd, double accuracy, double manaCost, spellType spellType, List<SpellEffect> effects) {
        this.name = name;
        this.description = description;
        this.damage = dmg;
        this.percentageDamage = pctDmg;
        this.cooldown = cd;
        this.accuracy = accuracy;
        this.manaCost = manaCost;
        this.spellType = spellType;
        this.effects = effects != null ? effects :new ArrayList<>();
    }

    public double calculateDamageToEnemy(Player player, Enemy enemy) {
        if (!player.hasEffect(SpellEffect.spellEffect.STUN)) {
            double dmgModification = enemy.getEffectValue(SpellEffect.spellEffect.DMG_MOD) * player.getEffectValue(SpellEffect.spellEffect.SPELL_DMG);
            double manaModification = player.getEffectValue(SpellEffect.spellEffect.MANA_COST_MOD);
            player.changeMana(-manaCost * manaModification);

            Random rand = new Random();
            if (rand.nextDouble() * 100 < accuracy) {
                double damageDealt = damage + (enemy.getMaxHP() * (percentageDamage / 100)) * dmgModification;
                enemy.changeHP(-damageDealt);
                for (SpellEffect effect : effects) {
                    enemy.addEffect(effect);
                }
                return damageDealt;
            } else {
                System.out.println("Your attack missed");
                return 0;
            }
        }
        System.out.println("You are stunned");
        return 0;
    }

    public double calculateDamageToPlayer(Player player, Enemy enemy) {
        if (!enemy.hasEffect(SpellEffect.spellEffect.STUN)) {
            double dmgModification = player.getEffectValue(SpellEffect.spellEffect.DMG_MOD) * enemy.getEffectValue(SpellEffect.spellEffect.SPELL_DMG);
            double manaModification = enemy.getEffectValue(SpellEffect.spellEffect.MANA_COST_MOD);
            enemy.changeMana(-manaCost * manaModification);

            Random rand = new Random();
            if (rand.nextDouble() * 100 < accuracy) {
                double damageDealt = damage + (player.getMaxHP() * (percentageDamage / 100)) * dmgModification;
                player.changeHP(-damageDealt);
                for (SpellEffect effect : effects) {
                    player.addEffect(effect);
                }
                return damageDealt;
            } else {
                System.out.println("The enemy's attack missed");
                return 0;
            }
        }
        System.out.println("The enemy is stunned");
        return 0;
    }

    public String getName() {
        return name;
    }

    public double getManaCost() {
        return manaCost;
    }

    public List<SpellEffect> getEffects() {
        return effects;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + "\nDamage: " + damage + ", Percentage damage: " + percentageDamage + ", Cooldown: " + cooldown + ", Accuracy: " + accuracy + ", Mana Cost: " + manaCost + ", Spell type: " + spellType + "\n";

    }
}
