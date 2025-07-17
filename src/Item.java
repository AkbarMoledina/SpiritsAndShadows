public class Item {
    private String name;
//    private int quantity;
//    private boolean equipped = false;

    // Constructor that is used to set object attributes
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


//    public boolean getEquipped() {
//        return equipped;
//    }
//
//    // Used in the Inventory class to unequip all other items when a new one is equipped
//    public void setEquipped(boolean equipped) {
//        this.equipped = equipped;
//    }
}
