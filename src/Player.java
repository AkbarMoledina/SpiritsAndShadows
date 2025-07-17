import java.util.Scanner;

public class Player {

    private String name;
    private String charClass;
    private int level;
    private int maxHP;
    private int maxStamina;
    private int currentHP;
    private int currentStamina;


    public Player(String name, String charClass) {
        this.name = name;
        this.charClass = charClass;
        this.level = 1;
        setBaseStats(charClass);
        this.currentHP = this.maxHP;
        this.currentStamina = this.maxStamina;
    }

    private void setBaseStats(String charClass) {
        switch (charClass.toLowerCase()) {
            case "assassin":
                maxHP = 100;
                maxStamina = 200;
                break;
            case "fighter":
                maxHP = 150;
                maxStamina = 150;
                break;
            case "tank":
                maxHP = 200;
                maxStamina = 100;
                break;
        }
    }

    public static String askName() {
        String name;
        int nameOption;
        GameLogic.printEmptyLines();
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
        GameLogic.printEmptyLines();
        System.out.println("What class are you?\n1. Fighter\n2. Assassin\n3. Tank\n");
        charClassOption = GameLogic.promptAndReadInt(3);
        switch (charClassOption) {
            case 1:
                charClass = "Fighter";
                break;
            case 2:
                charClass = "Assassin";
                break;
            case 3:
                charClass = "Tank";
                break;
        }
        return charClass;
    }


    public void changeHP(int amount) {
        currentHP += amount;
        if (currentHP < 0) currentHP = 0;
        if (currentHP > maxHP) currentHP = maxHP;
    }

    public void changeStamina(int amount) {
        currentStamina += amount;
        if (currentStamina < 0) currentStamina = 0;
        if (currentStamina > maxStamina) currentStamina = maxStamina;
    }

    public String getName() {
        return name;
    }

    public String getCharClass() {
        return charClass;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public void displayStats() {
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("Stamina: " + currentStamina + "/" + maxStamina);
    }
}
