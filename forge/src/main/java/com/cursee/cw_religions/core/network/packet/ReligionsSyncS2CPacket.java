package com.cursee.cw_religions.core.network.packet;

import com.cursee.cw_religions.client.network.packet.ReligionsSyncClientHandler;
import com.cursee.cw_religions.core.religion.Religion;
import com.cursee.cw_religions.core.religion.Religions;
import com.google.common.collect.Lists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class ReligionsSyncS2CPacket {

    public final int total;
    public final List<Religion> religions;

    public ReligionsSyncS2CPacket(int total, List<Religion> religions) {
        this.total = total;
        this.religions = religions;
    }

    public void encode(FriendlyByteBuf compoundTag) {
        compoundTag.writeInt(total);

        for (int i = 0; i < total; i++) {
            Religion religion = religions.get(i);
            compoundTag.writeUtf("R" + String.valueOf(i) + ";" +  religion.name() + "," + String.valueOf(religion.symbol()));
        }

        /// pseudo-representation of written data
        // 3,
        // "R0;Esquires,╘",
        // "R1;La Bomba-Testo,▌",
        // "R2;Rick!Rick,á"
    }

    public static ReligionsSyncS2CPacket decode(FriendlyByteBuf compoundTag) {

        int total = compoundTag.readInt();
        List<Religion> religionsRead = Lists.newArrayList();
        for (int i = 0; i < total; i++) {
            String religionString = compoundTag.readUtf().replaceAll("R([0-9]+);", "");
            Religion religion = new Religion(religionString.charAt(religionString.length() - 1), religionString.substring(0, religionString.lastIndexOf(",")));
            religionsRead.add(religion);
        }

        return new ReligionsSyncS2CPacket(total, religionsRead);
    }

    public static void handle(ReligionsSyncS2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> {
                return () -> {
                    ReligionsSyncClientHandler.registerS2CPacketHandler(packet, contextSupplier);
                };
            });
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
