package com.cursee.cw_religions.core.religion.util;

import com.cursee.cw_religions.CWReligions;
import com.cursee.cw_religions.Constants;
import com.cursee.cw_religions.core.religion.Religion;
import com.google.common.collect.Lists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static net.minecraft.world.level.Level.OVERWORLD;

public class ReligionsSaverAndLoader extends SavedData {

    public List<Religion> religions = Lists.newArrayList();

    public boolean createNewReligion(char symbol, String name) {
        AtomicBoolean created = new AtomicBoolean(true);

        religions.forEach(religion -> {
            if (religion.name().equalsIgnoreCase(name)) created.set(false);
            else if (religion.symbol() == symbol) created.set(false);
        });

        if (!created.get()) {
            religions.add(new Religion(symbol, name));
        }

        return created.get();
    }









    @Override
    public CompoundTag save(CompoundTag compoundTag) {

        compoundTag.putInt("total", religions.size());
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

        for (int i = 0; i < compoundTag.getInt("total"); i++) {
            String religionString = compoundTag.getString("R" + String.valueOf(i));
            Religion religion = new Religion(religionString.charAt(religionString.length() - 1), religionString.substring(0, religionString.lastIndexOf(",")));
            state.religions.add(religion);
        }

//        if (state.religions.isEmpty()) {
//            CWReligions.addDebugDefaultReligions(state.religions);
//        }

        Constants.LOG.info("{} Read religions from server:", Constants.PREFIX);
        state.religions.forEach(religion -> {
            Constants.LOG.info("{} Symbol: {} Name: {}", Constants.PREFIX, String.valueOf(religion.symbol()), religion.name());
        });

        return state;
    }

    public static ReligionsSaverAndLoader createNew() {
        Constants.LOG.info("{} Created new religion data for server.", Constants.PREFIX);
        ReligionsSaverAndLoader state = new ReligionsSaverAndLoader();
        state.religions = Lists.newArrayList();
        return state;
    }

    public static ReligionsSaverAndLoader getServerState(MinecraftServer server) {
        ServerLevel serverWorld = server.getLevel(OVERWORLD);
        ReligionsSaverAndLoader state = Objects.requireNonNull(serverWorld).getDataStorage().computeIfAbsent(ReligionsSaverAndLoader::createFromNbt, ReligionsSaverAndLoader::createNew, Constants.MOD_ID);
        state.setDirty();
        return state;
    }
}
