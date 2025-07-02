package com.cursee.cw_religions;

import com.cursee.cw_religions.core.network.CWReligionsNetwork;
import com.cursee.cw_religions.core.network.packet.ReligionsSyncS2CPacket;
import com.cursee.cw_religions.core.registry.ModRegistryForge;
import com.cursee.cw_religions.core.religion.util.ReligionsSaverAndLoader;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class CWReligionsForge {

    public static IEventBus EVENT_BUS;

    public CWReligionsForge(FMLJavaModLoadingContext context) {
        CWReligions.init();
        EVENT_BUS = context.getModEventBus();
        if (FMLEnvironment.dist == Dist.CLIENT) new CWReligionsClientForge(EVENT_BUS);
        ModRegistryForge.register(EVENT_BUS);

        CWReligionsNetwork.init();

        MinecraftForge.EVENT_BUS.addListener((Consumer<ServerStartedEvent>) event -> {
            ReligionsSaverAndLoader data = ReligionsSaverAndLoader.getServerState(event.getServer());
            CWReligions.RELIGIONS = data.religions;
            // CWReligions.addDebugDefaultReligions(CWReligions.RELIGIONS);
        });

        MinecraftForge.EVENT_BUS.addListener((Consumer<EntityJoinLevelEvent>) event -> {
            if (event.getLevel().isClientSide()) return;
            if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
            Constants.LOG.info("{} sending sync packet", Constants.PREFIX);
            ReligionsSaverAndLoader religionsData = ReligionsSaverAndLoader.getServerState(event.getEntity().getServer());
            CWReligionsNetwork.sendToPlayer(new ReligionsSyncS2CPacket(religionsData.religions.size(), religionsData.religions), serverPlayer);
        });
    }

    @SuppressWarnings("removal")
    public CWReligionsForge() {
        this(FMLJavaModLoadingContext.get());
    }
}