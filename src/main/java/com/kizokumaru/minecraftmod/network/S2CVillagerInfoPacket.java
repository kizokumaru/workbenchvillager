package com.kizokumaru.minecraftmod.network;

import com.kizokumaru.minecraftmod.WorkBenchVillager;
import com.kizokumaru.minecraftmod.client.gui.VillagerInfoScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record S2CVillagerInfoPacket(
        String profession,
        int level,
        String workstation,
        String workstationCoords,
        int trades
) implements CustomPacketPayload {
    public static final Type<S2CVillagerInfoPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(WorkBenchVillager.MODID, "villager_info_response"));
    public static final StreamCodec<RegistryFriendlyByteBuf, S2CVillagerInfoPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, S2CVillagerInfoPacket::profession,
            ByteBufCodecs.VAR_INT, S2CVillagerInfoPacket::level,
            ByteBufCodecs.STRING_UTF8, S2CVillagerInfoPacket::workstation,
            ByteBufCodecs.STRING_UTF8, S2CVillagerInfoPacket::workstationCoords,
            ByteBufCodecs.VAR_INT, S2CVillagerInfoPacket::trades,
            S2CVillagerInfoPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void handle(final S2CVillagerInfoPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            // This is client-side code
            Minecraft.getInstance().setScreen(new VillagerInfoScreen(packet));
        });
    }
}
