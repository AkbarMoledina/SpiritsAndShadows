import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Inventory {

    // Singleton
    private static final Inventory instance = new Inventory();
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Used in Main to get the Singleton instance
    public static Inventory getInstance() {
        return instance;
    }

    public List<Item> getItems() {
        // Allows outside classes to read but not modify (better encapsulation)
        return Collections.unmodifiableList(items);
    }

    public List<Weapon> getWeapons() {
        List<Weapon> weapons = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Weapon) {
                weapons.add((Weapon) item);
            }
        }
        return weapons;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            System.out.println("You left behind your " + item.getName());
        }
    }

    public void startingInventory() {
        Inventory inventory = Inventory.getInstance();
        Weapon sword = new Weapon("Sword", Weapon.WeaponType.ONE_HANDED, 100, 1.0);
        inventory.addItem(sword);
    }

    public void displayInventory() {
        GameLogic.printEmptyLines();
        for(Item item: items) {
            System.out.println("Name: " + item.getName());

            if (item instanceof Weapon) {
                Weapon weapon = (Weapon) item;
                System.out.println("Weapon type: " + weapon.getItemType());
                System.out.println("Attack damage: " + weapon.getAtkDmg());
                System.out.println("Attack speed: " + weapon.getAtkSpd());
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(items, inventory.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }

//    public void equipItem(String name) {
//        for (Item item : items) {
//            item.setEquipped(false);
//
//            if (item.getName().equals(name)) {
//                item.setEquipped(true);
//            }
//        }
//    }

}
