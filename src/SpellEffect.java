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

    public void applyPlayerSpellEffect(Player player, Enemy enemy) {

        if (type == spellEffect.HP_CHANGE || type == spellEffect.MANA_CHANGE || type == spellEffect.STUN) {
            printEffectMessage("The enemy");
            enemy.addEffect(this);
        } else {
            printEffectMessage("You");
            player.addEffect(this);
        }
    }

    public void applyEnemySpellEffect(Player player, Enemy enemy) {
        if (type == spellEffect.HP_CHANGE || type == spellEffect.MANA_CHANGE || type == spellEffect.STUN) {
            printEffectMessage("You");
            enemy.addEffect(this);
        } else {
            printEffectMessage("The enemy");
            player.addEffect(this);
        }
    }


    public void printEffectMessage(String targetName) {

        switch (type) {
            case HP_CHANGE -> {
                String action = (value < 0 ? (duration == 1 ? " took " : " will take ")
                        : (duration == 1 ? " healed " : " is healing "));
                String suffix = (value < 0 ? (duration == 1 ? " damage." : " damage for " + duration + " turns.")
                        : (duration == 1 ? " health." : " health for " + duration + " turns."));
                System.out.println(targetName + action + Math.abs(value) + suffix);
            }

            case MANA_CHANGE -> {
                String action = (value < 0 ? (duration == 1 ? " lost " : " will lose ")
                        : (duration == 1 ? " gained " : " will gain "));
                String suffix = " mana" + (duration > 1 ? " for " + duration + " turns." : ".");
                System.out.println(targetName + action + Math.abs(value) + suffix);
            }

            case STUN -> {
                String msg = targetName + (duration == 1
                        ? " is stunned for 1 turn."
                        : " is stunned for " + duration + " turns.");
                System.out.println(msg);
            }

            case SELF_HP_CHANGE -> {
                String action = (value < 0 ? (duration == 1 ? " took " : " will take ")
                        : (duration == 1 ? " healed " : " are healing "));
                String suffix = (value < 0 ? (duration == 1 ? " damage." : " damage over " + duration + " turns.")
                        : (duration == 1 ? " health." : " health each turn for " + duration + " turns."));
                System.out.println("You" + action + Math.abs(value) + suffix);
            }

            case SELF_INCOMING_DMG_MOD -> {
                if (value < 1) {
                    String reduction = (1 - value) * 100 + "%";
                    String msg = "Incoming damage is reduced by " + reduction +
                            (duration == 1 ? " this turn." : " for " + duration + " turns.");
                    System.out.println(msg);
                } else {
                    String increase = (value - 1) * 100 + "%";
                    String msg = "Incoming damage is increased by " + increase +
                            (duration == 1 ? " this turn." : " for " + duration + " turns.");
                    System.out.println(msg);
                }
            }

            case SELF_MANA_CHANGE -> {
                String action = (value < 0 ? (duration == 1 ? " lost " : " are losing ")
                        : (duration == 1 ? " gained " : " are gaining "));
                String suffix = " mana" + (duration > 1 ? " each turn for " + duration + " turns." : ".");
                System.out.println("You" + action + Math.abs(value) + suffix);
            }

            case SELF_MANA_COST_MOD -> {
                if (value < 1) {
                    String reduction = (1 - value) * 100 + "%";
                    System.out.println("Your spells will cost " + reduction +
                            (duration == 1 ? " less this turn." : " less for " + duration + " turns."));
                } else {
                    String increase = (value - 1) * 100 + "%";
                    System.out.println("Your spells will cost " + increase +
                            (duration == 1 ? " more this turn." : " more for " + duration + " turns."));
                }
            }

            case SELF_SPELL_DMG -> {
                if (value < 1) {
                    String reduction = (1 - value) * 100 + "%";
                    System.out.println("Your spells will deal " + reduction +
                            (duration == 1 ? " less damage this turn." : " less damage for " + duration + " turns."));
                } else {
                    String increase = (value - 1) * 100 + "%";
                    System.out.println("Your spells will deal " + increase +
                            (duration == 1 ? " more damage this turn." : " more damage for " + duration + " turns."));
                }
            }

            case SELF_EFFECT_IMMUNITY -> {
                String msg = "You are immune to spell effects " +
                        (duration == 1 ? "this turn." : "for " + duration + " turns.");
                System.out.println(msg);
            }
        }
    }

    public void wornOffMessages(boolean isPlayer) {
        String target = isPlayer ? "Your " : "The enemy's ";
        switch(type) {
            case HP_CHANGE -> System.out.println(target + "damage over time effect has worn off");
            case MANA_CHANGE -> System.out.println(target + "mana drain effect has worn off");
            case STUN -> System.out.println(target + "stun has worn off");
            case SELF_HP_CHANGE -> System.out.println(target + "health effect has worn off");
            case SELF_INCOMING_DMG_MOD -> System.out.println(target + "damage modification has worn off");
            case SELF_MANA_CHANGE -> System.out.println(target + "mana effect has worn off");
            case SELF_MANA_COST_MOD -> System.out.println(target + "mana cost modification has worn off");
            case SELF_SPELL_DMG -> System.out.println(target + "spell damage modification has worn off");
            case SELF_EFFECT_IMMUNITY -> System.out.println(target + "effect immunity has worn off");
            default -> System.out.println(target + "effect has worn off");
        }
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

    public double getChance() {
        return chance;
    }

    @Override
    public String toString() {
        return type + " (" + value + ") for " + duration + " turns";
    }
}
