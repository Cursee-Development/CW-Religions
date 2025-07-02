# 🛐 Minecraft Religion Mod – Design Document

## ❓ What is this mod?

A modular RPG-enhancing Minecraft mod that allows players to:
- Create and join religions (faiths, cults, churches, etc.)
- Build piety through in-world rituals
- Gain collective miracles and buffs
- Engage in holy wars
- Consecrate relics
- Enforce sacred item laws
- Roleplay through religion-based mechanics

---

## 🔹 Core Features Overview

### 📦 Altar Block
- Central access point for all religion features.
- Catholic-themed model: gilded gold and white with a book on top.
- GUI opens on right-click.

#### GUI Options

| Button           | Availability                        | Description                                      |
|------------------|-------------------------------------|--------------------------------------------------|
| View Religions   | Always                              | Browse all religions, their stats, and join      |
| Create Religion  | If not already in a religion        | Begin founding a religion                        |
| Request to Join  | If viewing another religion         | Sends join request to Prophet or Priest          |
| Manage Religion  | If Prophet or Priest                | Invite, kick, restrict items, declare war, etc.  |
| Confess          | If Layman in a religion             | Adds 10 piety (once per Minecraft day)           |
| Sacrifice        | If Prophet/Priest                   | Convert items into piety                         |
| Pray for Miracle | If Prophet/Priest                   | Spend 100 piety for a chance at miracles         |

---

## 🧎‍♂️ Religion System

### 📜 Religion Creation

- Done via Altar GUI → “Create Religion”
- Costs **8 Gold Blocks**
- Requires: name, symbol (char), color, optional rank names
- Broadcasts chat message:  
  `Prophet (Name) has founded (Religion Name)!`

### 🏅 Ranks

| Rank    | Abilities                                                                 |
|---------|--------------------------------------------------------------------------|
| Prophet | Full control. Manages settings, invites/kicks, sacrifices, miracles.     |
| Priest  | Can invite, sacrifice, pray for miracles. No config access.              |
| Layman  | Can confess only. Counts toward piety cap.                               |

---

## 🧿 Piety System

### 📈 Piety Cap

- Starts at 100
- +100 per Layman member

**Additional cap from altar floor blocks:**

| Block Type              | Piety Cap Bonus |
|-------------------------|-----------------|
| Glazed Terracotta       | +5              |
| Iron Block              | +5              |
| Gold Block              | +10             |
| Diamond Block           | +20             |
| Netherite Block         | +50             |
| Create:brass_block      | +75             |

### 🕯️ Gaining Piety

- **Confession** (Layman): +10 piety, once per day
- **Sacrifice** (Prophet/Priest):
    - Destroys item
    - Piety value is configurable
    - Cooldown per item is configurable (e.g., 1 per hour/day)

### ❌ Losing Piety

- Banned item usage: -10 per use
- Holy relic held by another religion: -25 per day
- Player deaths during holy war

---

## 🌟 Miracles

### 🛐 Pray for Miracle

- Cost: **100 Piety**
- Success chance:  
  `(Piety Cap / 100)%`  
  Example: 500 cap = 5% success chance
- If spammed, optional penalty:
    - All members struck by lightning
    - Receive bad luck effect

### 🔮 Types of Miracles (50/50 split)

1. **Item Miracle**
    - Each member receives 1 item from a configurable list

2. **Buff Miracle**
    - Each member receives a strong positive buff  
      (e.g., Strength V, Speed V, Luck V)

---

## ⚔️ Holy War

### ☠️ Declaring War

- Prophet only
- Once per Minecraft day
- Cost: **500 Piety**
- Target: one specific religion *or* all others
- Accessed via “Relations” tab in Altar GUI

### ⚔️ War Mechanics

- Lasts 1 day
- Killing enemy members rewards piety:

| Target Killed | Piety Gained | Piety Lost (if your player dies) |
|---------------|--------------|----------------------------------|
| Prophet       | +500         | -500                             |
| Priest        | +100         | -100                             |
| Layman        | +50          | -50                              |

- 10% chance for a miracle after every kill

---

## 🛡️ Item Restrictions

### 📜 Rules

- Prophet defines:
    - **Encouraged Items**
        - Give piety bonuses when used, sacrificed, or confessed
    - **Banned Items**
        - -10 piety on use or crafting
- Configurable via GUI

---

## 🔮 Holy Relics (Optional)

### 🛠️ Item Consecration

- Prophet can consecrate an item using altar GUI
- Cost: **500 Piety**
- Item becomes:
    - **Undestroyable**
    - +5 piety per sacrifice (if stored in altar)
    - Tracks religion of origin
    - Tooltip: “Holy Relic of [Religion]”

### ⚠️ Relic Theft

- If a relic is held by a different religion:
    - Original religion loses -25 piety per day
    - Loss stops once item is returned

---

## 🎨 Visual & GUI Aesthetic

- **Altar Block**:  
  Gold/white table with a central book (Catholic-inspired)
- **GUI Theme**:  
  Gilded white/gold border styling across all menus

---

## 🛠️ Technical Notes

- Religions stored server-side
- Uses UUIDs for Prophet & members
- Piety operations tracked with per-player cooldowns
- Config files:
    - Sacrificeable items + values
    - Item/Effect miracle rewards
    - Block types for piety cap
    - Banned/encouraged item rules
- Persistent storage
- Localization support
