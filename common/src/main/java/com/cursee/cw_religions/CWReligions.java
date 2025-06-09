package com.cursee.cw_religions;

import com.cursee.cw_religions.core.religion.Religion;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class CWReligions {

    private static final boolean addDebugDefaultReligions = true;
    public static List<Religion> RELIGIONS = new ArrayList<>();

    public static void init() {
        if (addDebugDefaultReligions) {
            RELIGIONS.add(new Religion('╘', "Esquires"));
            RELIGIONS.add(new Religion('▌', "La Bomba-Testo"));
            RELIGIONS.add(new Religion('á', "Rick!Rick"));
        }
    }

    public static ResourceLocation identifier(String path) {
        return new ResourceLocation(Constants.MOD_ID, path);
    }
}