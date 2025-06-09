package com.cursee.cw_religions.core.religion.util;

import com.cursee.cw_religions.Constants;
import com.cursee.cw_religions.core.religion.Religion;
import com.google.common.collect.Lists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.List;

import static net.minecraft.world.level.Level.OVERWORLD;

public class ReligionsSaverAndLoader extends SavedData {

    public List<Religion> religions = Lists.newArrayList();

    @Override
    public CompoundTag save(CompoundTag compoundTag) {

        for (int i = 0; i < religions.size(); i++) {
            Religion religion = religions.get(i);
            compoundTag.putString("R" + String.valueOf(i), religion.name() + "," + String.valueOf(religion.symbol()));
        }

        /// pseudo-representation of written data
        // R0: "Esquires,╘",
        // R1: "La Bomba-Testo,▌",
        // R2: "Rick!Rick,á"

        return compoundTag;
    }

    public static ReligionsSaverAndLoader createFromNbt(CompoundTag compoundTag) {
        ReligionsSaverAndLoader state = new ReligionsSaverAndLoader();
        for (int i = 0; i < state.religions.size(); i++) {
            String religionString = compoundTag.getString("R" + String.valueOf(i));
            Religion religion = new Religion(religionString.charAt(religionString.length() - 1), religionString.substring(0, religionString.lastIndexOf(",") - 1));
            state.religions.add(religion);
        }
        return state;
    }

    public static ReligionsSaverAndLoader createNew() {
        ReligionsSaverAndLoader state = new ReligionsSaverAndLoader();
        state.religions = Lists.newArrayList();
        return state;
    }

    public static ReligionsSaverAndLoader getServerState(MinecraftServer server) {
        // (Note: arbitrary choice to use 'World.OVERWORLD' instead of 'World.END' or 'World.NETHER'.  Any work)
        ServerLevel serverWorld = server.getLevel(OVERWORLD);
        assert serverWorld != null;

        // The first time the following 'getOrCreate' function is called, it creates a brand new 'StateSaverAndLoader' and
        // stores it inside the 'PersistentStateManager'. The subsequent calls to 'getOrCreate' pass in the saved
        // 'StateSaverAndLoader' NBT on disk to our function 'StateSaverAndLoader::createFromNbt'.
        ReligionsSaverAndLoader state = serverWorld.getDataStorage().computeIfAbsent(ReligionsSaverAndLoader::createFromNbt, ReligionsSaverAndLoader::createNew, Constants.MOD_ID);

        // If state is not marked dirty, when Minecraft closes, 'writeNbt' won't be called and therefore nothing will be saved.
        // Technically it's 'cleaner' if you only mark state as dirty when there was actually a change, but the vast majority
        // of mod writers are just going to be confused when their data isn't being saved, and so it's best just to 'markDirty' for them.
        // Besides, it's literally just setting a bool to true, and the only time there's a 'cost' is when the file is written to disk when
        // there were no actual change to any of the mods state (INCREDIBLY RARE).
        state.setDirty();

        return state;
    }
}
