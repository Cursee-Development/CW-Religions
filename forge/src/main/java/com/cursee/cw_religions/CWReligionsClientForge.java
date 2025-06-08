package com.cursee.cw_religions;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Consumer;

public class CWReligionsClientForge {

    public CWReligionsClientForge(final IEventBus modEventBus) {
        modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> event.enqueueWork(CWReligionsClient::init));
    }
}
