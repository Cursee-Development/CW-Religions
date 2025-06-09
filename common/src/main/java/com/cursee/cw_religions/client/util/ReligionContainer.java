package com.cursee.cw_religions.client.util;

import com.cursee.cw_religions.core.religion.Religion;
import net.minecraft.core.NonNullList;

import java.util.List;

public class ReligionContainer {

    private final NonNullList<Religion> religions;

    public ReligionContainer() {
        this.religions = NonNullList.create();
    }

    public ReligionContainer(List<Religion> religionList) {
        this.religions = NonNullList.create();
        religions.addAll(religionList);
    }

    public void consume(List<Religion> religionList) {
        religionList.forEach(religion -> {
            if (!religionList.contains(religion)) religions.add(religion);
        });
    }

    public NonNullList<Religion> religions() {
        return religions;
    }
}
