import java.util.List;

public class Story {

    public static Area setUpWorld(Player player) {
        ItemEvents itemEvents = setUpItems();
        SpellEvents spellEvents = setUpSpells();
        BattleEvents battleEvents = setUpEnemies(player);
        AreaGrid areas = setUpAreas();

        configureAreaExits(areas);
        assignEventsToAreas(areas, itemEvents, spellEvents, battleEvents);

        return areas.area00;
    }

    private static ItemEvents setUpItems() {
        Weapon duelbook = new Weapon("Duelbook", 1.5, 1.2);
        Weapon spellbook = new Weapon("Spellbook", 1.2, 1.5);
        Weapon battlestaff = new Weapon("Battlestaff", 1.8, 1.2);
        Weapon spellstaff = new Weapon("Spellstaff", 1.2, 1.8);

        return new ItemEvents(
                new ItemEvent(duelbook),
                new ItemEvent(spellbook),
                new ItemEvent(battlestaff),
                new ItemEvent(spellstaff),
                new Item("Lockpick"),
                new Item("Health Potion"),
                new Item("Mana Potion")
        );
    }

    private static SpellEvents setUpSpells() {
        List<SpellEffect> stoneskinEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.SELF_INCOMING_DMG_MOD, 0.60, 3, 100)
        );
        Spell stoneskin = new Spell("Stoneskin",
                "Earthy material from the surroundings binds to your skin, reducing incoming damage by 40% for 3 turns",
                0, 0, 4, 100, 20, Spell.spellType.EARTH, stoneskinEffects);

        List<SpellEffect> firestormEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.HP_CHANGE, -15, 3, 100),
                new SpellEffect(SpellEffect.spellEffect.SELF_HP_CHANGE, -15, 0, 100)
        );
        Spell firestorm = new Spell("Firestorm",
                "Conjure a firestorm that encompasses the entire area, damaging yourself but also dealing damage over time to your enemy.",
                15, 5, 4, 90, 40, Spell.spellType.PYRO, firestormEffects);

        List<SpellEffect> lightningBurstEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.MANA_CHANGE, -20, 0, 100)
        );
        Spell lightningBurst = new Spell("Lightning burst",
                "Unleash a burst of lightning, damaging the enemy's health and mana",
                30, 0, 1, 80, 10, Spell.spellType.SHOCK, lightningBurstEffects);

        List<SpellEffect> sanguinePactEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.SELF_SPELL_DMG, 1.6, 4, 100),
                new SpellEffect(SpellEffect.spellEffect.SELF_HP_CHANGE, -20, 0, 100)
        );
        Spell sanguinePact = new Spell("Sanguine pact",
                "Make a sacrifice for a portion of your current health to massively increase your spell power.",
                0, 0, 5, 100, 0, Spell.spellType.BLOOD, sanguinePactEffects);

        List<SpellEffect> bloodThirstEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.SELF_HP_CHANGE, 25, 0, 100)
        );
        Spell bloodThirst = new Spell("Blood thirst",
                "Create a brief channel between yourself and your enemy, dragging their lifeforce into your own",
                25, 0, 3, 80, 30, Spell.spellType.BLOOD, bloodThirstEffects);

        List<SpellEffect> runePrisonEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.STUN, 0, 2, 100)
        );
        Spell runePrison = new Spell("Rune prison",
                "Damage and stun the enemy for 2 turn by trapping them in pure mana.",
                10, 10, 4, 95, 50, Spell.spellType.SPIRIT, runePrisonEffects);

        List<SpellEffect> ethrealMendEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.SELF_HP_CHANGE, 25, 3, 100),
                new SpellEffect(SpellEffect.spellEffect.SELF_INCOMING_DMG_MOD, 0.60, 3, 100)
        );
        Spell etherealMend = new Spell("Ethreal mend",
                "Call upon a spirit to heal you over three turns whilst protecting you from further attacks",
                0, 0, 5, 100, 50, Spell.spellType.SPIRIT, ethrealMendEffects);

        List<SpellEffect> lightningStormEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.MANA_CHANGE, -50, 0, 100),
                new SpellEffect(SpellEffect.spellEffect.HP_CHANGE, 10, 3, 100)
        );
        Spell lightningStorm = new Spell("Lightning storm",
                "Conjure a fully fledged lightning storm, dealing damage over time to your enemy's health and massive damage to their mana",
                10, 0, 3, 90, 25, Spell.spellType.SHOCK, lightningStormEffects);

        List<SpellEffect> crystalCoffinEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.STUN, 0, 3, 100)
        );
        Spell crystalCoffin = new Spell("Crystal coffin",
                "Buries your enemy in a large rectangle of ice",
                0, 0, 6, 60, 50, Spell.spellType.ICE, crystalCoffinEffects);

        Spell gaiasWrath = new Spell("Gaia's wrath",
                "Call upon the wrath of Gaia to deal massive damage to the enemy.",
                60, 0, 3, 90, 35, Spell.spellType.EARTH, null);

        List<SpellEffect> siphonSoulEffects = List.of(
                new SpellEffect(SpellEffect.spellEffect.SELF_HP_CHANGE, 40, 1, 100),
                new SpellEffect(SpellEffect.spellEffect.SELF_INCOMING_DMG_MOD, 0.60, 1, 100)
        );
        Spell siphonSoul = new Spell("Siphon soul",
                "Siphons the enemy's energy, healing you and causing them to deal reduced damage with their next attack.",
                40, 0, 4, 100, 30, Spell.spellType.SHADOW, siphonSoulEffects);

        Spell phantomCleave = new Spell("Phantom cleave",
                "Unleash an arc of spirit energy, dealing massive percent health damage.",
                10, 22.5, 3, 95, 65, Spell.spellType.SHADOW, null);

        return new SpellEvents(
                new LearnSpellEvent(stoneskin),
                new LearnSpellEvent(firestorm),
                new LearnSpellEvent(lightningBurst),
                new LearnSpellEvent(sanguinePact),
                new LearnSpellEvent(bloodThirst),
                new LearnSpellEvent(runePrison),
                new LearnSpellEvent(etherealMend),
                new LearnSpellEvent(lightningStorm),
                new LearnSpellEvent(crystalCoffin),
                new LearnSpellEvent(gaiasWrath),
                new LearnSpellEvent(siphonSoul),
                new LearnSpellEvent(phantomCleave)
        );
    }

    private static BattleEvents setUpEnemies(Player player) {
        Enemy darkMage = new Enemy("Dark Mage", Enemy.enemyType.SHADOW, 100, 150);
        Enemy soulboundKnight = new Enemy("Soulbound Knight", Enemy.enemyType.SPIRIT, 200, 200);
        Enemy dreadRevenant = new Enemy("Dread Revenant", Enemy.enemyType.SHADOW, 200, 200);
        Enemy blazehound = new Enemy("Blazehound", Enemy.enemyType.PYRO, 100, 100);
        Enemy frostDrake = new Enemy("Frost Drake", Enemy.enemyType.ICE, 150, 200);
        Enemy umbravex = new Enemy("Umbravex", Enemy.enemyType.SHADOW, 400, 250);

        return new BattleEvents(
                new BattleEvent(new Battle(player, darkMage), darkMage),
                new BattleEvent(new Battle(player, soulboundKnight), soulboundKnight),
                new BattleEvent(new Battle(player, dreadRevenant), dreadRevenant),
                new BattleEvent(new Battle(player, blazehound), blazehound),
                new BattleEvent(new Battle(player, frostDrake), frostDrake),
                new BattleEvent(new Battle(player, umbravex), umbravex)
        );
    }

    private static AreaGrid setUpAreas() {
        Area area00 = new Area(0,0,"The ground beneath you radiates heat, cracked and scorched by the relentless sun. Jagged stones and ash-coated ruins hint at a civilization long consumed by fire.");
        Area area01 = new Area(0,1,"You enter a narrow canyon carved by time. The air is dry and flames flicker through the charred crevices in the stone.");
        Area area02 = new Area(0,2,"A winding trail that hums with a warm spiritual energy. You sense the presence of those who walked this path before you.");
        Area area03 = new Area(0,3,"A narrow bridge arches over a mist covered ravine. You feel pulled towards the land across.\nYou feel determined and focused as a knight of spirit challenges you to battle.");
        Area area10 = new Area(1,0,"Fissures glow faintly with molten rock, the bubbling lava nearby pulses like a heartbeat.");
        Area area11 = new Area(1,1,"A scorched stretch of barren land. Beads of sweat drip down your forehead which you wipe away with the sleeve of your robe.");
        Area area12 = new Area(1,2,"Ruins of an old shrine rest beneath the moonlight. The veil between life and death feels thin, as if you could slip between the worlds at will.");
        Area area13 = new Area(1,3,"You step into a glade where time feels frozen. A cool calm presence surrounds you.");
        Area area20 = new Area(2,0,"The terrain fractures into haunting beauty - ghostly embers flare in the foreground of a backdrop of obsidian.");
        Area area21 = new Area(2,1,"You path through a crumbling village. Dark energy pulsing from every structure. You subconsciously break into a jog as your body and mind don't want to be here. ");
        Area area22 = new Area(2,2,"A swirling vortex of light and darkness converges at an abyssal center. There stands Umbravex.");
        Area area23 = new Area(2,3,"The icy wind carries more than just snow and water. As the frost clings to your skin, the spirits speak into your mind.");
        Area area30 = new Area(3,0,"The light here bends strangely. Trees cast no shadows, yet darkness creeps over every surface. Darkness clings unnaturally to everything around you. The silent thick air is almost suffocating.\nYou feel a deep chill as a creature of the darkness approaches.");
        Area area31 = new Area(3,1,"Shadows slither across the walls as if alive. The silence here is oppressive, broken only by the echo of your own heartbeat.");
        Area area32 = new Area(3,2,"Darkness looms, the air chills your spine. You begin to shiver but press onwards.");
        Area area33 = new Area(3,3,"A vast open stretch of snow under a clear sky. The air is crisp and calming.");
        Area exit = new Area(4,4,"You have completed the game!");

        return new AreaGrid(
                area00, area01, area02, area03,
                area10, area11, area12, area13,
                area20, area21, area22, area23,
                area30, area31, area32, area33,
                exit
        );
    }

    private static void configureAreaExits(AreaGrid areas) {
        // Row 0
        areas.area00.setExits("North", areas.area01);
        areas.area00.setExits("East", areas.area10);

        areas.area01.setExits("North", areas.area02);
        areas.area01.setExits("East", areas.area11);
        areas.area01.setExits("South", areas.area00);

        areas.area02.setExits("North", areas.area03);
        areas.area02.setExits("East", areas.area12);
        areas.area02.setExits("South", areas.area01);

        areas.area03.setExits("East", areas.area13);
        areas.area03.setExits("South", areas.area02);

        // Row 1
        areas.area10.setExits("East", areas.area20);
        areas.area10.setExits("West", areas.area00);

        areas.area11.setExits("North", areas.area12);
        areas.area11.setExits("West", areas.area01);

        areas.area12.setExits("North", areas.area13);
        areas.area12.setExits("South", areas.area11);
        areas.area12.setExits("West", areas.area02);

        areas.area13.setExits("East", areas.area23);
        areas.area13.setExits("South", areas.area12);
        areas.area13.setExits("West", areas.area03);

        // Row 2
        areas.area20.setExits("North", areas.area21);
        areas.area20.setExits("East", areas.area30);
        areas.area20.setExits("West", areas.area10);

        areas.area21.setExits("East", areas.area31);
        areas.area21.setExits("South", areas.area20);

        areas.area22.setExits("North", areas.area23);
        areas.area22.setExits("East", areas.area32);

        areas.area23.setExits("East", areas.area33);
        areas.area23.setExits("South", areas.area22);
        areas.area23.setExits("West", areas.area13);

        // Row 3
        areas.area30.setExits("North", areas.area31);
        areas.area30.setExits("West", areas.area20);

        areas.area31.setExits("North", areas.area32);
        areas.area31.setExits("South", areas.area30);
        areas.area31.setExits("West", areas.area21);

        areas.area32.setExits("North", areas.area33);
        areas.area32.setExits("South", areas.area31);
        areas.area32.setExits("West", areas.area22);

        areas.area33.setExits("South", areas.area32);
        areas.area33.setExits("West", areas.area23);
    }

    private static void assignEventsToAreas(AreaGrid areas, ItemEvents weapons, SpellEvents spells, BattleEvents battles) {
        areas.area00.setSearchEvent(spells.learnStoneskin);

        areas.area01.setSearchEvent(weapons.duelbookEvent);

        areas.area02.setSearchEvent(spells.learnLightningBurst);

        areas.area03.setEnterEvent(battles.fightSoulboundKnightEvent);
        areas.area03.setSearchEvent(spells.learnEthrealMend);

        areas.area10.setSearchEvent(weapons.spellbookEvent);

        areas.area11.setEnterEvent(battles.fightBlazehoundEvent);
        areas.area11.setSearchEvent(spells.learnFirestorm);

        areas.area12.setSearchEvent(spells.learnRunePrison);

        areas.area13.setEnterEvent(battles.fightFrostDrakeEvent);
        areas.area13.setSearchEvent(weapons.spellStaffEvent);

        areas.area20.setSearchEvent(spells.learnSanquinePact);

        areas.area21.setSearchEvent(spells.learnBloodthirst);

        areas.area22.setEnterEvent(battles.fightUmbravexEvent);
        areas.area22.setSearchEvent(spells.learnLightningStorm);

        areas.area23.setSearchEvent(spells.learnCrystalCoffin);

        areas.area30.setEnterEvent(battles.fightdreadRevenantEvent);
        areas.area30.setSearchEvent(spells.learnPhantomCleave);

        areas.area31.setEnterEvent(battles.fightDarkMageEvent);
        areas.area31.setSearchEvent(weapons.battlestaffEvent);

        areas.area32.setSearchEvent(spells.learnSiphonSoul);

        areas.area33.setSearchEvent(spells.learnGaiasWrath);
    }

    // Helper classes
    private static class ItemEvents {
        final Event duelbookEvent;
        final Event spellbookEvent;
        final Event battlestaffEvent;
        final Event spellStaffEvent;
        final Item lockpick;
        final Item healthPotion;
        final Item manaPotion;

        ItemEvents(Event duelbookEvent, Event spellbookEvent, Event battlestaffEvent, Event spellStaffEvent,
                   Item lockpick, Item healthPotion, Item manaPotion) {
            this.duelbookEvent = duelbookEvent;
            this.spellbookEvent = spellbookEvent;
            this.battlestaffEvent = battlestaffEvent;
            this.spellStaffEvent = spellStaffEvent;
            this.lockpick = lockpick;
            this.healthPotion = healthPotion;
            this.manaPotion = manaPotion;
        }
    }

    private static class SpellEvents {
        final LearnSpellEvent learnStoneskin;
        final LearnSpellEvent learnFirestorm;
        final LearnSpellEvent learnLightningBurst;
        final LearnSpellEvent learnSanquinePact;
        final LearnSpellEvent learnBloodthirst;
        final LearnSpellEvent learnRunePrison;
        final LearnSpellEvent learnEthrealMend;
        final LearnSpellEvent learnLightningStorm;
        final LearnSpellEvent learnCrystalCoffin;
        final LearnSpellEvent learnGaiasWrath;
        final LearnSpellEvent learnSiphonSoul;
        final LearnSpellEvent learnPhantomCleave;

        SpellEvents(LearnSpellEvent learnStoneskin, LearnSpellEvent learnFirestorm,
                    LearnSpellEvent learnLightningBurst, LearnSpellEvent learnSanquinePact,
                    LearnSpellEvent learnBloodthirst, LearnSpellEvent learnRunePrison,
                    LearnSpellEvent learnEthrealMend, LearnSpellEvent learnLightningStorm,
                    LearnSpellEvent learnCrystalCoffin, LearnSpellEvent learnGaiasWrath,
                    LearnSpellEvent learnSiphonSoul, LearnSpellEvent learnPhantomCleave) {
            this.learnStoneskin = learnStoneskin;
            this.learnFirestorm = learnFirestorm;
            this.learnLightningBurst = learnLightningBurst;
            this.learnSanquinePact = learnSanquinePact;
            this.learnBloodthirst = learnBloodthirst;
            this.learnRunePrison = learnRunePrison;
            this.learnEthrealMend = learnEthrealMend;
            this.learnLightningStorm = learnLightningStorm;
            this.learnCrystalCoffin = learnCrystalCoffin;
            this.learnGaiasWrath = learnGaiasWrath;
            this.learnSiphonSoul = learnSiphonSoul;
            this.learnPhantomCleave = learnPhantomCleave;
        }
    }

    private static class BattleEvents {
        final BattleEvent fightDarkMageEvent;
        final BattleEvent fightSoulboundKnightEvent;
        final BattleEvent fightdreadRevenantEvent;
        final BattleEvent fightBlazehoundEvent;
        final BattleEvent fightFrostDrakeEvent;
        final BattleEvent fightUmbravexEvent;

        BattleEvents(BattleEvent fightDarkMageEvent, BattleEvent fightSoulboundKnightEvent,
                     BattleEvent fightdreadRevenantEvent, BattleEvent fightBlazehoundEvent,
                     BattleEvent fightFrostDrakeEvent, BattleEvent fightUmbravexEvent) {
            this.fightDarkMageEvent = fightDarkMageEvent;
            this.fightSoulboundKnightEvent = fightSoulboundKnightEvent;
            this.fightdreadRevenantEvent = fightdreadRevenantEvent;
            this.fightBlazehoundEvent = fightBlazehoundEvent;
            this.fightFrostDrakeEvent = fightFrostDrakeEvent;
            this.fightUmbravexEvent = fightUmbravexEvent;
        }
    }

    private static class AreaGrid {
        final Area area00, area01, area02, area03;
        final Area area10, area11, area12, area13;
        final Area area20, area21, area22, area23;
        final Area area30, area31, area32, area33;
        final Area exit;

        AreaGrid(Area area00, Area area01, Area area02, Area area03,
                 Area area10, Area area11, Area area12, Area area13,
                 Area area20, Area area21, Area area22, Area area23,
                 Area area30, Area area31, Area area32, Area area33,
                 Area exit) {
            this.area00 = area00;
            this.area01 = area01;
            this.area02 = area02;
            this.area03 = area03;
            this.area10 = area10;
            this.area11 = area11;
            this.area12 = area12;
            this.area13 = area13;
            this.area20 = area20;
            this.area21 = area21;
            this.area22 = area22;
            this.area23 = area23;
            this.area30 = area30;
            this.area31 = area31;
            this.area32 = area32;
            this.area33 = area33;
            this.exit = exit;
        }
    }
}