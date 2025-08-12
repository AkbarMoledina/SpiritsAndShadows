public class BattleEvent implements Event {
    private Battle battle;
    private Enemy enemy;

    public BattleEvent(Battle battle, Enemy enemy) {
        this.battle = battle;
        this.enemy = enemy;
    }

    @Override
    public void trigger(Player player, Area area) {
        battle.startBattle(player, enemy);

    }
}
