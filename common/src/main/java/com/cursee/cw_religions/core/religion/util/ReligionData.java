package com.cursee.cw_religions.core.religion.util;

import com.cursee.cw_religions.core.religion.Religion;
import net.minecraft.nbt.CompoundTag;

public class ReligionData {

    public static void setReligion(IEntityReligionSaver player, char religionSymbol, String religionName) {
        CompoundTag data = player.getReligion();
        data.putString("symbol", String.valueOf(religionSymbol));
        data.putString("name", religionName);
    }

    public static void removeReligion(IEntityReligionSaver player) {
        CompoundTag data = player.getReligion();
        data.putString("symbol", String.valueOf(Religion.NONE.symbol()));
        data.putString("name", Religion.NONE.name());
    }
}
