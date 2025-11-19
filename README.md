# ⚔️ Elemental RPG

A small turn-based RPG written in **Java**, featuring elemental magic, area exploration, inventory management, and flexible combat mechanics.

---

## 🌍 Overview

In this game, the player explores a **4x4 world grid**, encounters enemies, and uses spells and weapons to battle through themed regions — each aligned to an elemental type.

Each spell can have multiple effects such as damage, healing, or buffs/debuffs, allowing for rich combinations and strategic gameplay.

---

## 🧱 Core Systems

### 🧙‍♂️ Player
- The player has health, mana, basic attack multipliers and spell multipliers based on class, weapons and spell buffs/debuffs.
- The player can equip weapons, cast spells, and use items.

### 🔮 Spells
Each spell defines:
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
