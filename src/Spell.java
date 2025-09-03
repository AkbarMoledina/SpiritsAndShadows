import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

            Inventory inventory = Inventory.getInstance();
            double weaponModifier = 1;

            if (Objects.equals(name, "Basic attack")) {
                weaponModifier = inventory.getWeapon().getBasicDmg();
            } else { weaponModifier = inventory.getWeapon().getSpellDmg(); }

            double dmgModification = modifier(enemy.getEffectValue(SpellEffect.spellEffect.SELF_INCOMING_DMG_MOD)) * modifier(player.getEffectValue(SpellEffect.spellEffect.SELF_SPELL_DMG)) * weaponModifier;
            double manaModification = modifier(player.getEffectValue(SpellEffect.spellEffect.SELF_MANA_COST_MOD));
            player.changeMana(-manaCost * manaModification);

            Random rand = new Random();
            if (rand.nextDouble() * 100 < accuracy) {
                double damageDealt = (damage + (enemy.getMaxHP() * (percentageDamage / 100))) * dmgModification;
                enemy.changeHP(-damageDealt);
                System.out.printf("Your %s did %.2f damage!\n", name, damageDealt);
                for (SpellEffect effect : effects) {
                    if (rand.nextDouble() * 100 < effect.getChance()) { effect.applyPlayerSpellEffect(player, enemy ); }
                }
                return damageDealt;
            } else {
                System.out.println("Your attack missed.");
                return 0;
            }
        }
        System.out.println("You are stunned.");
        return 0;
    }

    public double calculateDamageToPlayer(Player player, Enemy enemy) {
        if (!enemy.hasEffect(SpellEffect.spellEffect.STUN)) {
            // set these to 1 when there's no active modifier (check if there's a cleaner way later)
            double dmgModification = modifier(player.getEffectValue(SpellEffect.spellEffect.SELF_INCOMING_DMG_MOD)) * modifier(enemy.getEffectValue(SpellEffect.spellEffect.SELF_SPELL_DMG));
            double manaModification = modifier(enemy.getEffectValue(SpellEffect.spellEffect.SELF_MANA_COST_MOD));
            enemy.changeMana(-manaCost * manaModification);

            Random rand = new Random();
            if (rand.nextDouble() * 100 < accuracy) {
                double damageDealt = damage * dmgModification + (player.getMaxHP() * (percentageDamage / 100));
                player.changeHP(-damageDealt);
                System.out.printf("The enemy's attack did %.2f damage to you!\n", damageDealt);
                for (SpellEffect effect : effects) {
                    if (rand.nextDouble() * 100 < effect.getChance()) { effect.applyEnemySpellEffect(player, enemy ); }
                }
                return damageDealt;
            } else {
                System.out.println("The enemy's attack missed.");
                return 0;
            }
        }
        System.out.println("The enemy is stunned.");
        return 0;
    }

    public double modifier(double modifier) {
        return modifier == 0 ? 1 : modifier;
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
