# ⚔️ Elemental RPG

A small turn-based RPG written in **Java**, featuring elemental magic, area exploration, inventory management, and flexible combat mechanics.

---

## 🌍 Overview

In this game, the player explores a **4x4 world grid**, encounters enemies, and uses spells and weapons to battle through elementally themed regions.

Each spell can have multiple effects such as damage, healing, or buffs/debuffs, allowing for rich combinations and strategic gameplay.

---

## 🧱 Core Systems

### 🧙‍♂️ Player
- The player has health, mana, basic attack multipliers and spell multipliers based on chosen class, weapons and spell buffs/debuffs.
- The battlemage class has stronger basic attacks but little mana to cast spells. The utility class has a massive mana pool for spells, but low basic attack damage and health. The spellcaster class has good mana and health but still fairly low basic attack damage.
- Items can be found by searching the room (costing 20 mana per search). Each of the weapons will generally benefit either the basic attack or casting spells playstyle more, depending on the weapon. If you have enough mana, try to find both the best basic attack weapon and the best casting spells weapon and see what happens!

### 🔮 Spells
Spells are balanced over several factors. Ones with high flat damage will generally be strong against targets with low max HP, meanwhile high percentage damage will be stronger against enemies with a high HP pool. There are also several spell effects: damage over time, mana damage, stun, self damage/heal over time, self damage modification, self mana restore, self mana cost modification, self spell damage modifiction, self effect immunity.

- `damage` — base power
- `percentageDamage` — scaling factor based on the caster’s stats
- `cooldown` — the number of turns the caster has to wait before using the spell again
- `accuracy` — hit chance percentage
- `manaCost` — resource consumed per cast
- `spellType` — elemental affinity (Pyro, Ice, Earth, Shock, Spirit, Shadow, Blood)
- `effects` — additional status modifiers (e.g. stun, heal, damage reduction)

Example:
Spell iceSpike = new Spell(
"Ice spike",
"Launch a low mana cost but inaccurate spear of ice.",
50, 0, 1, 70, 10,
Spell.spellType.ICE, null);
