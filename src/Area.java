import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Area {
    private final int x;
    private final int y;
    private boolean entered;
    private boolean searched;
    private String description;
    private Map<String, Area> exits = new LinkedHashMap<>();
    private Event enterEvent;
    private Event searchEvent;

    public Area(int x, int y, String description) {
        this.x = x;
        this.y = y;
        this.entered = false;
        this.searched = false;
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
        for (String dir : exits
                .keySet()) {
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
                    System.out.println("You don't have enough mana.");
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


    public void takeItem(Item item) {
        System.out.println("You found a " + item.getName() + "!");
        Inventory inventory = Inventory.getInstance();
        if ((Objects.equals(item.getName(), "Spellstaff") && Objects.equals(inventory.getWeapon().getName(), "Battlestaff")) || (Objects.equals(item.getName(), "Battlestaff") && Objects.equals(inventory.getWeapon().getName(), "Spellstaff"))) {
            Weapon elderStaff = new Weapon("Elder staff", 2.0, 2.0);
            inventory.removeItem(inventory.getWeapon());
            inventory.addItem(elderStaff);
            System.out.println("You feel a mysterious force pulling on your staff. You grip it tighter until you can hold on no longer and it is ripped from your hand.\nThe " + item.getName() + " and your staff fuse into one. You walk over and pick up the new staff and immediately feel incredible power flowing through you.\nYou are now in possession of the legendary Elder Staff!");
            System.out.println("\n" + elderStaff.toString());
            this.searched = true;
        }
        else if (item instanceof Weapon) {
            System.out.println("\nNew " + item);
            System.out.println("Current " + inventory.getWeapon().toString());
            System.out.println("\nWould you like to drop your current weapon and pick up the " + item.getName() + "?\n1. Yes\n2. No");
            int option = GameLogic.promptAndReadInt(2);
            if (option == 1) {
                inventory.addItem(item);

                Weapon oldWeapon = inventory.getWeapon();
//                Event oldWeaponEvent = new ItemEvent(oldWeapon);

                this.searched = true;
                System.out.println();
                System.out.println("You left behind your " + oldWeapon.getName() + " and picked up a " + item.getName());
                inventory.removeItem(oldWeapon);
            }
        } else {
            System.out.println("Would you like to pick up the " + item.getName() + "?\n1. Yes\n2. No");
            int option = GameLogic.promptAndReadInt(2);
            if (option == 1) {
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
