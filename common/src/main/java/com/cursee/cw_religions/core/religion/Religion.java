package com.cursee.cw_religions.core.religion;

public record Religion(char symbol, String name) {
    public static final Religion NONE = new Religion('\u00A0', "");
}
