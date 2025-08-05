import java.util.List;

public class Story {

    public static Area setUpWorld(Player player) {

        // Weapons/items
        Weapon duelbook = new Weapon("Duelbook", 1.5, 1.2);
        Weapon spellbook = new Weapon("Spellbook", 1.2, 1.5);
        Weapon battlestaff = new Weapon("Battlestaff", 1.8, 1.2);
        Weapon spellstaff = new Weapon("Spellstaff", 1.2, 1.8);
        Item lockpick = new Item("Lockpick");
        Item heathPotion = new Item("Health Potion");
        Item manaPotion = new Item("Mana Potion");

        // Spells
        // add logic to support this
        Spell flameCoat = new Spell("Flame coat", "Engulf yourself living flames that hunt enemies that deal damage to you", 30, 0, 4, 100, 30, Spell.spellType.PYRO, null);
        LearnSpellEvent learnFlameCoat = new LearnSpellEvent(flameCoat);

        List<SpellEffect> crystalCoffinEffects = List.of(new SpellEffect(SpellEffect.spellEffect.STUN, 0, 3, 100));
        Spell crystalCoffin = new Spell("Crystal coffin", "Buries your enemy in a large rectangle of ice", 0, 0, 6, 60, 45, Spell.spellType.ICE, crystalCoffinEffects);
        LearnSpellEvent learnCrystalCoffin = new LearnSpellEvent(crystalCoffin);

        List<SpellEffect> lightningBurstEffects = List.of(new SpellEffect(SpellEffect.spellEffect.MANA_DMG, 20, 0, 100));
        Spell lightningBurst = new Spell("Lightning burst", "Unleash a burst of lightning, damaging the enemy's health and mana", 30, 0, 1, 80, 10, Spell.spellType.SHOCK, lightningBurstEffects);
        LearnSpellEvent learnLightingBurst = new LearnSpellEvent(lightningBurst);

        List<SpellEffect> lightningStormEffects = List.of(new SpellEffect(SpellEffect.spellEffect.MANA_DMG, 50, 0, 100));
        Spell lightningStorm = new Spell("Lightning storm", "Conjure a fully fledged lightning storm, dealing damage to your enemy's health and massive damage to their mana", 50, 0, 3, 90, 25, Spell.spellType.SHOCK, lightningStormEffects);
        LearnSpellEvent learnLightningStorm = new LearnSpellEvent(lightningStorm);

        List<SpellEffect> stoneskinEffects = List.of(new SpellEffect(SpellEffect.spellEffect.DMG_MOD, 0.60, 3, 100));
        Spell stoneskin = new Spell("Stoneskin", "Earthy material from the surroundings binds to your skin, reducing incoming damage by 40% for 3 turns", 0, 0, 4, 100, 20, Spell.spellType.EARTH, stoneskinEffects);
        LearnSpellEvent learnStoneskin = new LearnSpellEvent(stoneskin);

        Spell gaiasWrath = new Spell("Gaia's wrath", "", 60, 0, 3, 90, 40, Spell.spellType.EARTH, null);
        LearnSpellEvent learnGaiasWrath = new LearnSpellEvent(gaiasWrath);

        List<SpellEffect> sanguinePactEffects = List.of(new SpellEffect(SpellEffect.spellEffect.SPELL_DMG, 1.6, 3, 100), new SpellEffect(SpellEffect.spellEffect.HP_CHANGE, -25, 0, 100));
        Spell sanguinePact = new Spell("Sanguine pact", "Make a sacrifice for a portion of your current health to massively increase your spell power.", 0 , 0, 5, 100, 0, Spell.spellType.BLOOD, sanguinePactEffects);
        LearnSpellEvent learnSanquinePact = new LearnSpellEvent(sanguinePact);

        List<SpellEffect> bloodThirstEffects = List.of(new SpellEffect(SpellEffect.spellEffect.HP_CHANGE, 25, 0, 100));
        Spell bloodThirst = new Spell("Blood thirst", "Create a brief channel between yourself and your enemy, dragging their lifeforce into your own", 25, 0, 3, 80, 30, Spell.spellType.BLOOD, bloodThirstEffects);
        LearnSpellEvent learnBloodthirst = new LearnSpellEvent(bloodThirst);

        List<SpellEffect> runePrisonEffects = List.of(new SpellEffect(SpellEffect.spellEffect.STUN, 0, 2, 100));
        Spell runePrison = new Spell("Rune prison", "Damage and stun the enemy for 2 turn by trapping them in pure mana.", 10, 10, 4, 95, 65, Spell.spellType.SPIRIT, runePrisonEffects);
        LearnSpellEvent learnRunePrison = new LearnSpellEvent(runePrison);

        List<SpellEffect> ethrealMendEffects = List.of(new SpellEffect(SpellEffect.spellEffect.HP_CHANGE, 25, 3, 100), new SpellEffect(SpellEffect.spellEffect.DMG_MOD, 0.75, 3, 100));
        Spell etherealMend = new Spell("Ethreal mend", "Call upon a spirit to heal you over three turns whilst protecting you from further attacks", 0, 0, 5, 100, 50, Spell.spellType.SPIRIT, ethrealMendEffects);
        LearnSpellEvent learnEthrealMend = new LearnSpellEvent(etherealMend);

        List<SpellEffect> siphonSoulEffects = List.of(new SpellEffect(SpellEffect.spellEffect.HP_CHANGE, 40, 1, 100), new SpellEffect(SpellEffect.spellEffect.DMG_MOD, 0.60, 1, 100));
        Spell siphonSoul = new Spell("Siphon soul", "Siphons the enemy's energy, healing you and causing them to deal reduced damage with their next attack.", 40, 0, 3, 100, 30, Spell.spellType.SHADOW, siphonSoulEffects);
        LearnSpellEvent learnSiphonSoul = new LearnSpellEvent(siphonSoul);

        Spell phantomCleave = new Spell("Phantom cleave", "Unleash an arc of spirit energy, dealing massive percent health damage.", 10, 22.5, 3, 95, 65, Spell.spellType.SHADOW, null);
        LearnSpellEvent learnPhantomCleave = new LearnSpellEvent(phantomCleave);


        // Enemies
        Enemy darkMage = new Enemy("Dark Mage", Enemy.enemyType.SHADOW, 100, 150);
        Battle fightDarkMage = new Battle(player, darkMage);
        BattleEvent fightDarkMageEvent = new BattleEvent(fightDarkMage, darkMage);

        Enemy soulboundKnight = new Enemy("Soulbound Knight", Enemy.enemyType.SPIRIT, 200, 200);
        Battle fightSoulboundKnight = new Battle(player, soulboundKnight);
        BattleEvent fightSoulboundKnightEvent = new BattleEvent(fightSoulboundKnight, soulboundKnight);

        Enemy dreadRevenant = new Enemy("Dread Revenant", Enemy.enemyType.SHADOW, 200, 200);
        Battle fightdreadRevenant = new Battle(player, dreadRevenant);
        BattleEvent fightdreadRevenantEvent = new BattleEvent(fightdreadRevenant, dreadRevenant);

        Enemy blazehound = new Enemy("Blazehound", Enemy.enemyType.PYRO, 100, 100);
        Battle fightBlazehound = new Battle(player, blazehound);
        BattleEvent fightBlazehoundEvent = new BattleEvent(fightBlazehound, blazehound);

        Enemy frostDrake = new Enemy("Frost Drake", Enemy.enemyType.ICE, 150, 200);
        Battle fightFrostDrake = new Battle(player, frostDrake);
        BattleEvent fightFrostDrakeEvent = new BattleEvent(fightFrostDrake, frostDrake);

        // Final boss, switches between Shadow type Umbravex and Spirit type Veloria
        Enemy umbravex = new Enemy("Umbravex", Enemy.enemyType.SHADOW, 1000, 250);
        Battle fightUmbravex = new Battle(player, umbravex);
        BattleEvent fightUmbravexEvent = new BattleEvent(fightUmbravex, umbravex);


        Area area00 = new Area(0,0, null, """
                A scorched stretch of barren land. Beads of sweat drip down your forehead which you wipe away with the sleeve of your robe.
                """);
        Area area01 = new Area(0, 1, battlestaff, "You enter a narrow canyon carved by time. The air is dry and flames flicker through the charred crevices in the stone.");
        Area area02 = new Area(0, 2, null, "02");
        Area area03 = new Area(0, 3, null, "The light here bends strangely. Trees cast no shadows, yet darkness creeps over every surface. The silent thick air is almost suffocating.");
        Area area10 = new Area(1, 0, null, "A warm breeze brushes through your hair, yet the sun still feels uncomfortable as it radiates onto your skin.");
        Area area11 = new Area(1, 1, null, "The temperature is noticeably cooler than the areas to the south and west. ");
        Area area12 = new Area(1, 2, lockpick, "12");
        Area area13 = new Area(1, 3, heathPotion, "13");
        Area area20 = new Area(2, 0, lockpick, "20");
        Area area21 = new Area(2, 1, null, "21");
        Area area22 = new Area(2, 2, null, "22");
        Area area23 = new Area(2, 3, null, "23");
        Area area30 = new Area(3, 0, null, "You are fighting against the wind as you traverse through the area.");
        Area area31 = new Area(3, 1, spellbook, "31");
        Area area32 = new Area(3, 2, null, "32");
        Area area33 = new Area(3, 3, null, "33");
        Area exit = new Area(4, 4, null, "You have completed the game!");


        area00.setExits("North", area01);
        area00.setExits("East", area10);
        area00.setSearchEvent(learnStoneskin);

        area10.setExits("East", area20);
        area10.setExits("West", area00);
        area10.setSearchEvent(learnLightingBurst);

        area20.setExits("North", area21);
        area20.setExits("East", area30);
        area20.setExits("West", area10);

        area30.setExits("North", area31);
        area30.setExits("West", area20);
        area30.setEnterEvent(fightdreadRevenantEvent);
        area30.setSearchEvent(learnPhantomCleave);

        area01.setExits("North", area02);
        area01.setExits("East", area11);
        area01.setExits("South", area00);

        area11.setExits("North", area12);
        area11.setExits("West", area01);
        area11.setEnterEvent(fightBlazehoundEvent);
        area11.setSearchEvent(learnFlameCoat);

        area21.setExits("East", area31);
        area21.setExits("South", area20);

        area31.setExits("North", area32);
        area31.setExits("South", area30);
        area31.setExits("West", area21);
        area31.setEnterEvent(fightDarkMageEvent);
        area31.setSearchEvent(learnRunePrison);

        //search: lockpick
        area02.setExits("North", area03);
        area02.setExits("East", area12);
        area02.setExits("South", area01);

        area12.setExits("North", area13);
        area12.setExits("South", area11);
        area12.setExits("West", area02);

        //boss room
        area22.setExits("North", area23);
        area22.setExits("East", area32);
        area22.setEnterEvent(fightUmbravexEvent);

        area32.setExits("North", area33);
        area32.setExits("South", area31);
        area32.setExits("West", area22);

        area03.setExits("East", area13);
        area03.setExits("South", area02);
        area03.setEnterEvent(fightSoulboundKnightEvent);
        area03.setSearchEvent(learnEthrealMend);

        area13.setExits("East", area23);
        area13.setExits("South", area12);
        area13.setExits("West", area03);
        area13.setEnterEvent(fightFrostDrakeEvent);

        area23.setExits("East", area33);
        area23.setExits("South", area22);
        area23.setExits("West", area13);

        //Exit
        area33.setExits("South", area32);
        area33.setExits("East", exit);
        area33.setExits("West", area23);
        
        return area00;
    }
}
