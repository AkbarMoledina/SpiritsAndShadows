public class SpellEffect {

    private final spellEffect type;
    private final double value;
    private int duration;
    private int remainingDuration;
    private double chance;

    public enum spellEffect {
        HP_CHANGE, MANA_CHANGE, STUN, SELF_HP_CHANGE, SELF_INCOMING_DMG_MOD, SELF_MANA_CHANGE, SELF_MANA_COST_MOD, SELF_SPELL_DMG, SELF_EFFECT_IMMUNITY
    }

    public SpellEffect(spellEffect type, double value, int duration, double chance) {
        this.type = type;
        this.value = value;
        this.duration = duration;
        remainingDuration = duration;
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

    public boolean reduceDuration() {
        // see if there's a cleaner way of doing this
        remainingDuration--;
        if (remainingDuration <= 0) {
            remainingDuration = duration;
            return true;
        }
        return false;
    }

//    public boolean isExpired() {
//        return remainingDuration <= 0;
//    }

    @Override
    public String toString() {
        return type + " (" + value + ") for " + duration + " turns";
    }
}
