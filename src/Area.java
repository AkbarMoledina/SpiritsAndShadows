import java.util.HashMap;
import java.util.Map;

public class Area {
    private final int x;
    private final int y;
    private boolean entered;
    private boolean searched;
    private Item item;
    private String description;
    private Map<String, Area> exits = new HashMap<>();
    private Event enterEvent;
    private Event searchEvent;

    public Area(int x, int y, Item item, String description) {
        this.x = x;
        this.y = y;
        this.entered = false;
        this.searched = false;
        this.item = item;
        this.description = description;
    }

    public void setExits(String direction, Area area) {
        exits.put(capitalize(direction), area);
    }

    public void describe() {
        System.out.println("\n" + description);
    }

    public void onEnter(Player player) {
        if (!entered && enterEvent != null) {
            enterEvent.trigger(player, this);
            entered = true;
        }
    }

    public void options() {

        int option = 1;
        for (String dir : exits.keySet()) {
            System.out.println(option +". Go " + dir);
            option++;
        }

        if (!searched) {
            System.out.printf("%d. Search room (-20 mana)\n", option++);
        }

        System.out.printf("%d. View Inventory\n", option++);
        System.out.printf("%d. View Stats\n", option++);
        System.out.printf("%d. View Spells\n", option++);
    }

    public int getNumberOfOptions() {
        int count = exits.size();
        if (!searched) count++;
        return count + 3; // always includes 'View Inventory' and 'View Stats'
    }

    /**
     * Process the player's input and return the new room (or the current room if no change).
     */
    public Area processChoice(int choice, Player player) {
        int index = 1;

        for (Map.Entry<String, Area> exit : exits.entrySet()) {
            if (choice == index) {
                System.out.println("You move " + exit.getKey() + ".");
                return exit.getValue();
            }
            index++;
        }

        if (!searched) {
            if (choice == index) {
                if (player.getCurrentMana() >= 20) {
                    player.changeMana(-20);
                    searched = true;
//                    if (item != null) {
//                        takeItem(item);
//                    } else {
//                        System.out.println("You searched the room but found nothing...");
//                        return this;
//                    }
                    if (searchEvent != null) {
                        searchEvent.trigger(player, this);
                        searchEvent = null;
                    } else {
                        System.out.println("You searched the room but found nothing...");
                    }
                } else {
                    System.out.println("You don't have enough mana. You need a nap.");
                }
                return this;
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
        index++;

        if (choice == index) {
            for (Spell spell : player.getKnownSpells()) {
                System.out.println(spell);
            }
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

    public void setEnterEvent(Event event) {
        this.enterEvent = event;
    }

    public void setSearchEvent(Event event) {
        this.searchEvent = event;
    }

    private String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    // Maybe need @Override equals when comparing rooms by x and y co-ordinates
}
