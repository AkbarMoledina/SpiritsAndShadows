import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {

    private String name;
    private String charClass;
    private double maxHP;
    private double maxMana;
    private double currentHP;
    private double currentMana;
    private double attackspeed;
    private Spell basicAttack;
    private List<Spell> knownSpells;
    private static final int MAX_SPELLS = 5;
    private List<SpellEffect> activeEffects = new ArrayList<>();


    public Player(String name, String charClass) {
        this.name = name;
        this.charClass = charClass;
        setBaseStats(charClass);
        this.currentHP = this.maxHP;
        this.currentMana = this.maxMana;
        this.basicAttack = new Spell("Basic attack", "An attack with no cooldown which deals damage based on your attack speed.", 20 * attackspeed, 0, 0, 95, 0, null, null);
        this.knownSpells = new ArrayList<>();
        setStartingAbilities();
    }

    private void setBaseStats(String charClass) {
        switch (charClass.toLowerCase()) {
            case "battlemage":
                maxHP = 200;
                maxMana = 100;
                attackspeed = 2;
                break;
            case "spellcaster":
                maxHP = 150;
                maxMana = 200;
                attackspeed = 1.2;
                break;
            case "utility":
                maxHP = 100;
                maxMana = 300;
                attackspeed = 1;
                break;
        }
    }

    private void setStartingAbilities() {

        Spell iceSpike = new Spell("Ice spike", "Launch a low mana cost but inaccurate spear of ice.", 50, 0, 1, 65, 10, Spell.spellType.ICE, null);

        List<SpellEffect> flameboltEffects = List.of(new SpellEffect(SpellEffect.spellEffect.DOT, 10, 2, 30));
        Spell flamebolt = new Spell("Flamebolt", "A blast of fire with a chance to burn, that also deals a small amount of percent health damage.", 25, 5, 2, 90, 30, Spell.spellType.PYRO, flameboltEffects);

        knownSpells.add(iceSpike);
        knownSpells.add(flamebolt);
    }

    public static String askName() {
        String name;
        int nameOption;
        GameLogic.printEmptyLines(1);
        do {
            System.out.println("Hello there, what is your name?");
            name = GameLogic.scanner.nextLine();
            System.out.printf("Ah, so you're %s! Is that correct?\n1. Yes\n2. No\n", name);
            nameOption = GameLogic.promptAndReadInt(2);

        } while (nameOption == 2);
        return name;
    }

    public static String askClass() {
        String charClass = "";
        int charClassOption;
        GameLogic.printEmptyLines(1);
        System.out.println("What class are you?\n1. Battlemage\n2. Spellcaster\n3. Utility\n");
        charClassOption = GameLogic.promptAndReadInt(3);
        switch (charClassOption) {
            case 1:
                charClass = "Battlemage";
                break;
            case 2:
                charClass = "Spellcaster";
                break;
            case 3:
                charClass = "Utility";
                break;
        }
        return charClass;
    }

    public void learnSpell(Spell newSpell) {
        if (knownSpells.size() >= MAX_SPELLS) {
            System.out.println("You cannot have more than 5 spells. Pick a spell to be forgotten, to learn " + newSpell.getName() + "!");

            boolean learningSpell = true;
            while (learningSpell) {
                int count = 1;
                for (Spell spell : knownSpells) {
                    System.out.println(count + ". " + spell);
                    count++;
                }
                System.out.println(count + ". Do not learn " + newSpell.getName());

                int choice = GameLogic.promptAndReadInt(6);
                if (choice != 6) {
                    System.out.println("Are you sure you'd like to forget " + knownSpells.get(choice - 1).getName() + " to learn " + newSpell.getName() + "?\n1. Yes\n2. No");
                    if (GameLogic.promptAndReadInt(2) == 1) {
                        knownSpells.set(choice - 1, newSpell);
                        System.out.println("You forgot " + knownSpells.get(choice -1).getName() + " and learnt " + newSpell.getName() + "!");
                        learningSpell = false;
                    }
                }
                else {
                    System.out.println("Are you sure you don't want to learn " + newSpell.getName() + "?\n1. Yes\n2. No");
                    if (GameLogic.promptAndReadInt(2) == 1) {
                        System.out.println("You did not learn " + newSpell.getName());
                        learningSpell = false;
                    }
                }
            }
        }
        else {
            System.out.println("You learnt " + newSpell.getName() + "!");
            knownSpells.add(newSpell);
        }
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
        System.out.println("Player: " + name);
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


    public String getName() {
        return name;
    }

    public String getCharClass() {
        return charClass;
    }

    public double getMaxHP() { return maxHP; }

    public double getCurrentHP() {
        return currentHP;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public Spell getBasicAttack() {
        return basicAttack;
    }

    public List<Spell> getKnownSpells() {
        return knownSpells;
    }
}
