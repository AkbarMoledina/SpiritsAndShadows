import java.util.Scanner;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);


    public static Player startGame() {
        GameLogic.printHeading("TEXT RPG");
        String name = Player.askName();
        String charClass = Player.askClass();

        Player player = new Player(name, charClass);
        Inventory.getInstance().startingInventory();
        Inventory.getInstance().displayInventory();

        return player;
    }

    public static void gameLoop(Player player) {
        boolean isRunning = true;
        Area currentArea = Story.setUpWorld(player);

        while (isRunning) {
            currentArea.describe();
            currentArea.onEnter(player);
            currentArea.options();
            int choice = promptAndReadInt(currentArea.getNumberOfOptions());
            currentArea = currentArea.processChoice(choice, player);
//            System.out.println(currentRoom);

            if (player.getCurrentHP() <= 0) {
                System.out.println("You have died");
                isRunning = false;
            }
        }
    }

//    public Room getCurrentRoom() {
//
//    }

    public static int promptAndReadInt(int options) {

        int input;

        do {
            try {
                input = Integer.parseInt(scanner.nextLine());

                if (input < 1 || input > options) {
                    System.out.printf("Please enter an option between %d and %d \n", 1, options);
                }
            } catch (NumberFormatException e) {
                input = -1;
                System.out.printf("Please enter an option between %d and %d \n", 1, options);
            }
        }
        while (input < 1 || input > options);
        printEmptyLines(1);

        return input;
    }

    public static void printEmptyLines(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }

    public static void printHeading(String text) {
        System.out.println("########################");
        System.out.println(text);
        System.out.println("########################");
    }
}
