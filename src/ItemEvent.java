public class ItemEvent implements Event {
    private Item item;

    public ItemEvent(Item item) {
        this.item = item;
    }

    @Override
    public void trigger(Player player, Area area) {
        area.takeItem(item);
    }
}
