package com.kizokumaru.minecraftmod;

import com.kizokumaru.minecraftmod.network.C2SVillagerInfoRequestPacket;
import com.kizokumaru.minecraftmod.network.S2CVillagerInfoPacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1.0";

    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(WorkBenchVillager.MODID)
                .versioned(PROTOCOL_VERSION);

        // Register packets here
        registrar.playToServer(C2SVillagerInfoRequestPacket.ID, C2SVillagerInfoRequestPacket.STREAM_CODEC, C2SVillagerInfoRequestPacket::handle);
        registrar.playToClient(S2CVillagerInfoPacket.ID, S2CVillagerInfoPacket.STREAM_CODEC, S2CVillagerInfoPacket::handle);
    }
}
