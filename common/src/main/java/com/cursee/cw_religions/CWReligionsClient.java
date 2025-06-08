package com.cursee.cw_religions;

import com.cursee.cw_religions.client.gui.screens.inventory.AltarScreen;
import com.cursee.cw_religions.core.registry.ModMenus;
import com.cursee.cw_religions.platform.Services;

public class CWReligionsClient {

    public static void init() {
        Services.PLATFORM.registerScreen(ModMenus.ALTAR, AltarScreen::new);
    }
}
