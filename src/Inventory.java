import java.util.*;
import java.util.stream.Collectors;

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

    public Weapon getWeapon() {
        List<Weapon> weapons = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Weapon) {
                weapons.add((Weapon) item);
            }
        }
        return weapons.getFirst();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void startingInventory() {
        Inventory inventory = Inventory.getInstance();
        Weapon sword = new Weapon("Wand", 1.2, 1.2);
        inventory.addItem(sword);
    }

    public void displayInventory() {
        GameLogic.printEmptyLines(1);

        items.sort(Comparator.comparing(Item::getName));
        items.stream().collect(Collectors.groupingBy(Item::getName));

        for(Item item: items) {

            if (item instanceof Weapon) {
                Weapon weapon = (Weapon) item;
                System.out.println("Name: " + weapon.getName());
                System.out.println("Basic attack multiplier: " + weapon.getBasicDmg());
                System.out.println("Spell multiplier: " + weapon.getSpellDmg());
                break;
            }
        }
        Map<String, Long> grouped = items.stream().filter(item -> !(item instanceof Weapon)).collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
        List<String> sortedNames = new ArrayList<>(grouped.keySet());
        Collections.sort(sortedNames);

        for (String name : sortedNames) {
            System.out.println(name + (grouped.get(name) > 1 ? " x" + grouped.get(name) : ""));
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
