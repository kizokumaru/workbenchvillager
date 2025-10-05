package com.kizokumaru.minecraftmod;

import com.kizokumaru.minecraftmod.network.C2SVillagerInfoRequestPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class VillagerInfoEventHandler {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getHand() != InteractionHand.MAIN_HAND) {
            return;
        }

        Player player = event.getEntity();
        // We only need to send a packet from the client side.
        if (player.level().isClientSide && event.getTarget() instanceof Villager) {
            if (player.isShiftKeyDown()) {
                event.setCanceled(true);

                // Send a packet to the server to request villager info
                int villagerId = event.getTarget().getId();
                PacketDistributor.sendToServer(new C2SVillagerInfoRequestPacket(villagerId));
            }
        }
    }
}
