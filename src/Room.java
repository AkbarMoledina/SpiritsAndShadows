import java.util.HashMap;
import java.util.Map;

public class Room {
    private final int x;
    private final int y;
    private boolean searched;
    private Item item;
    private String description;
    private Map<String, Room> exits = new HashMap<>();


    public Room(int x, int y, Item item, String description) {
        this.x = x;
        this.y = y;
        this.searched = false;
        this.item = item;
        this.description = description;
    }

    public void setExits(String direction, Room room) {
        exits.put(capitalize(direction), room);
    }

    public void describe() {
        GameLogic.printHeading("Current Area");
        System.out.println(description);
    }

    public void options(Player player) {

        int option = 1;
        for (String dir : exits.keySet()) {
            System.out.println(option +". Go " + dir);
            option++;
        }

        if (!searched) {
            System.out.printf("%d. Search room (-20 Stamina)\n", option++);
        }

        System.out.printf("%d. View Inventory\n", option++);
        System.out.printf("%d. View Stats\n", option++);

    }

    public int getNumberOfOptions() {
        int count = exits.size();
        if (!searched) count++;
        return count + 2; // always includes 'View Inventory' and 'View Stats'
    }

    /**
     * Process the player's input and return the new room (or the current room if no change).
     */
    public Room processChoice(int choice, Player player) {
        int index = 1;

        for (Map.Entry<String, Room> exit : exits.entrySet()) {
            if (choice == index) {
                System.out.println("You move " + exit.getKey() + ".");
                return exit.getValue();
            }
            index++;
        }

        //Need to add code for dropping your current weapon when picking up a new one

        if (!searched) {
            if (choice == index) {
                if (player.getCurrentStamina() >= 20) {
                    player.changeStamina(-20);
                    searched = true;
                    if (item != null) {
                        takeItem(item);
                    } else {
                        System.out.println("You searched the room but found nothing...");
                        return this;
                    }
                } else {
                    System.out.println("You don't have enough stamina. You need a nap.");
                    return this;
                }
            }
            index++;
        }

        // View Inventory
        if (choice == index) {
            Inventory.getInstance().displayInventory();
            return this;
        }
        index++;

        if (choice == index) {
            player.displayStats();
            return this;
        }

        return this; //no movement, stay in the same room
    }

    private void takeItem(Item item) {
        System.out.println("You found a " + item.getName() + "!\n" + item);
        if (item instanceof Weapon) {
            System.out.println("Would you like to drop your current weapon and pick up the " + item.getName() + "?\n1. Yes\n2. No");
            int option = GameLogic.promptAndReadInt(2);
            if (option == 1) {
                Inventory inventory = Inventory.getInstance();
                inventory.addItem(item);
                Weapon oldWeapon = inventory.getWeapon();
                this.item = oldWeapon;
                this.searched = false;
                inventory.removeItem(oldWeapon);
                System.out.println("You picked up a " + item.getName());
            }
        } else {
            System.out.println("Would you like to pick up the " + item.getName() + "?\n1. Yes\n2. No");
            int option = GameLogic.promptAndReadInt(2);
            if (option == 1) {
                Inventory inventory = Inventory.getInstance();
                inventory.addItem(item);
            }
        }
    }

    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
