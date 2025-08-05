public class SpellEffect {

    private final spellEffect type;
    private final double value;
    private int duration;
    private double chance;

    public enum spellEffect {
        STUN, DOT, MANA_DMG, HP_CHANGE, DMG_MOD, SPELL_DMG, MANA_COST_MOD, EFFECT_IMMUNITY
    }

    public SpellEffect(spellEffect type, double value, int duration, double chance) {
        this.type = type;
        this.value = value;
        this.duration = duration;
        this.chance = chance;
    }

    public spellEffect getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public int getDuration() {
        return duration;
    }

    public void reduceDuration() {
        duration--;
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    @Override
    public String toString() {
        return type + " (" + value + ") for " + duration + " turns";
    }
}
