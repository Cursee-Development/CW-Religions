package com.cursee.cw_religions;

import com.cursee.cw_religions.core.religion.Religion;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class CWReligions {

    private static final boolean addDebugDefaultReligions = true;
    public static List<Religion> RELIGIONS = new ArrayList<>();

    public static void init() {
        addDebugDefaultReligions(RELIGIONS);
    }

    public static void addDebugDefaultReligions(List<Religion> religionList) {
        if (addDebugDefaultReligions && religionList.isEmpty()) {
            religionList.add(new Religion('╘', "Esquires"));
            religionList.add(new Religion('▌', "La Bomba-Testo"));
            religionList.add(new Religion('á', "Rick!Rick"));
        }
    }

    public static ResourceLocation identifier(String path) {
        return new ResourceLocation(Constants.MOD_ID, path);
    }
}