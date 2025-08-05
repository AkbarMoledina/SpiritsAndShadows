import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Enemy {
    private String name;
    private enemyType enemyType;
    private double maxHP;
    private double maxMana;
    private double currentHP;
    private double currentMana;
    private Spell basicAttack;
    private List<SpellEffect> activeEffects = new ArrayList<>();

    public enum enemyType {
        PYRO, ICE, EARTH, SHOCK, WATER, SPIRIT, SHADOW
    }

    public Enemy(String name, enemyType enemyType, double maxHp, double maxMana) {
        this.name = name;
        this.enemyType = enemyType;
        this.maxHP = maxHp;
        this.maxMana = maxMana;
        this.currentHP = maxHp;
        this.currentMana = maxMana;
    }

    public void changeHP(double amount) {
        currentHP += amount;
        if (currentHP < 0) currentHP = 0;
        if (currentHP > maxHP) currentHP = maxHP;
    }

    public void changeMana(double amount) {
        currentMana += amount;
        if (currentMana < 0) currentMana = 0;
        if (currentMana > maxMana) currentMana = maxMana;
    }

    public void displayStats() {
        System.out.println("Enemy: " + name);
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("Mana: " + currentMana + "/" + maxMana);
    }

    public void addEffect(SpellEffect effect) {
        activeEffects.add(effect);
    }

    public void updateEffects() {
        Iterator<SpellEffect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            SpellEffect effect = iterator.next();
            effect.reduceDuration();
            if (effect.isExpired()) {
                System.out.println(effect.getType() + " has worn off");
                iterator.remove();
            }
        }
    }

    public boolean hasEffect(SpellEffect.spellEffect type) {
        for (SpellEffect effect : activeEffects) {
            if (effect.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public double getEffectValue(SpellEffect.spellEffect type) {
        return activeEffects.stream()
                .filter(e -> e.getType() == type)
                .mapToDouble(SpellEffect::getValue)
                .sum();
    }

    public double getEffectDuration(SpellEffect.spellEffect type) {
        return activeEffects.stream()
                .filter(e -> e.getType() == type)
                .mapToDouble(SpellEffect::getDuration)
                .sum();
    }

    public void setBasicAttack(Spell basicAttack) {
        this.basicAttack = basicAttack;
    }

    public String getName() {
        return name;
    }

    public enemyType getEnemyType() {
        return enemyType;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public double getCurrentHP() {
        return currentHP;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public Spell getBasicAttack() {
        return basicAttack;
    }
}
