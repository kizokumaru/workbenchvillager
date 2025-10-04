package com.kizokumaru.minecraftmod;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class VillagerInfoEventHandler {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Villager && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.isShiftKeyDown()) {
                event.setCanceled(true);

                if (!player.level().isClientSide) {
                    try {
                        Villager villager = (Villager) event.getTarget();

                        // Get Villager Data
                        String profession = villager.getVillagerData().getProfession().toString();
                        int level = villager.getVillagerData().getLevel();
                        MerchantOffers offers = villager.getOffers();
                        int trades = offers.size();

                        // Send information to the player
                        player.sendSystemMessage(Component.literal("--- Villager Info ---"));
                        player.sendSystemMessage(Component.literal("Profession: " + profession));
                        player.sendSystemMessage(Component.literal("Level: " + level));
                        player.sendSystemMessage(Component.literal("Trades available: " + trades));
                        player.sendSystemMessage(Component.literal("---------------------"));

                    } catch (Exception e) {
                        // If any error occurs, log it for debugging and inform the player.
                        WorkBenchVillager.LOGGER.error("Failed to get villager info:", e);
                        player.sendSystemMessage(Component.literal("§cError: Could not retrieve villager information. Check server logs."));
                    }
                }
            }
        }
    }
}
