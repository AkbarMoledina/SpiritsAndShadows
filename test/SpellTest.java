import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpellTest {

    private Player player;
    private Enemy enemy;
    private Spell spiritBomb;
    private Spell deathBall;
    private Spell kiBlast;

    @BeforeEach
    void setUp() {
        player = new Player("Goku", "Spellcaster");
        enemy = new Enemy("Freiza", Enemy.enemyType.SHADOW, 500, 150);

        Weapon transformation = new Weapon("Super Saiyan", 1.5, 2);
        Inventory.getInstance().addItem(transformation);

        List<SpellEffect> spiritBombEffects = List.of(new SpellEffect(SpellEffect.spellEffect.STUN, 0, 1, 50));
        spiritBomb = new Spell("Spirit Bomb", "Gather energy from surrounding lifeforms and unleash a destructive energy sphere at the enemy.", 85, 15, 3, 100, 50, Spell.spellType.SPIRIT, spiritBombEffects);
        deathBall = new Spell("Death Ball","Channel a massive ball of energy and launch it at the target",120,10,4,100,50, Spell.spellType.SHADOW, null);
        kiBlast = new Spell("Ki Blast", "Fire a blast of energy that will miss it's target", 15, 1, 1, 0, 10, Spell.spellType.PYRO, null);
    }

    @Test
    void calculateDamageToEnemy() {
        double manaBefore = player.getCurrentMana();
        double enemyHpBefore = enemy.getCurrentHP();
        double damage = spiritBomb.calculateDamageToEnemy(player, enemy);

        assertEquals(manaBefore - spiritBomb.getManaCost(), player.getCurrentMana(), 0.0001);
        assertEquals(enemyHpBefore - damage, enemy.getCurrentHP(), 0.0001);
        assertTrue(damage > 0);
        assertEquals(enemyHpBefore - enemy.getCurrentHP(), damage, 0.0001);
    }

    @Test
    void calculateDamageToPlayer() {
        double manaBefore = enemy.getCurrentMana();
        double playerHpBefore = player.getCurrentHP();
        double damage = deathBall.calculateDamageToPlayer(player, enemy);

        assertEquals(manaBefore - deathBall.getManaCost(), enemy.getCurrentMana(), 0.0001);
        assertEquals(playerHpBefore - damage, player.getCurrentHP(), 0.0001);
        assertTrue(damage > 0);
        assertEquals(playerHpBefore - player.getCurrentHP(), damage, 0.0001);
    }

    @Test
    void zeroAccuracySpell() {
        double manaBefore = player.getCurrentMana();
        double enemyHpBefore = enemy.getCurrentHP();
        double damage = kiBlast.calculateDamageToEnemy(player, enemy);

        assertEquals(manaBefore - kiBlast.getManaCost(), player.getCurrentMana(), 0.0001);
        assertEquals(enemyHpBefore, enemy.getCurrentHP(), 0.0001);
        assertEquals(0, damage, 0.0001);
    }
}