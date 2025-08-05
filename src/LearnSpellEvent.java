public class LearnSpellEvent implements Event {
    private Spell spell;

    public LearnSpellEvent(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void trigger(Player player, Area area) {
        player.learnSpell(spell);
    }
}
