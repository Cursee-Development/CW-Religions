package com.cursee.cw_religions.client.network.packet;

import com.cursee.cw_religions.CWReligions;
import com.cursee.cw_religions.core.network.packet.ReligionsSyncS2CPacket;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ReligionsSyncClientHandler {

    public static void registerS2CPacketHandler(ReligionsSyncS2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            CWReligions.RELIGIONS = packet.religions;
        });
    }
}
