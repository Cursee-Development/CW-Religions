package com.cursee.cw_religions;

import com.cursee.cw_religions.core.registry.ModRegistryFabric;
import com.cursee.cw_religions.core.religion.util.ReligionsSaverAndLoader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.level.ServerLevel;

import static net.minecraft.world.level.Level.OVERWORLD;

public class CWReligionsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CWReligions.init();
        ModRegistryFabric.register();
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            ReligionsSaverAndLoader data = ReligionsSaverAndLoader.getServerState(server);
            CWReligions.RELIGIONS = data.religions;
            // CWReligions.addDebugDefaultReligions(CWReligions.RELIGIONS);
        });

    }
}
