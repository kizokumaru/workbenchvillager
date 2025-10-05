package com.kizokumaru.minecraftmod.network;

import com.kizokumaru.minecraftmod.WorkBenchVillager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Optional;

public record C2SVillagerInfoRequestPacket(int villagerId) implements CustomPacketPayload {
    public static final Type<C2SVillagerInfoRequestPacket> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(WorkBenchVillager.MODID, "villager_info_request"));
    public static final StreamCodec<RegistryFriendlyByteBuf, C2SVillagerInfoRequestPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, C2SVillagerInfoRequestPacket::villagerId,
            C2SVillagerInfoRequestPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void handle(final C2SVillagerInfoRequestPacket packet, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            Entity target = player.level().getEntity(packet.villagerId);

            if (target instanceof Villager villager) {
                try {
                    VillagerData villagerData = villager.getVillagerData();
                    VillagerProfession profession = villagerData.getProfession();
                    ResourceLocation professionKey = BuiltInRegistries.VILLAGER_PROFESSION.getKey(profession);

                    String professionString = profession == VillagerProfession.NONE ?
                            "None" :
                            "entity.minecraft.villager." + professionKey.getPath();

                    int level = villagerData.getLevel();

                    Optional<GlobalPos> jobSiteOptional = villager.getBrain().getMemory(MemoryModuleType.JOB_SITE);
                    String workstationString = "None";
                    String workstationCoordsString = "";

                    if (jobSiteOptional.isPresent()) {
                        BlockPos jobSitePos = jobSiteOptional.get().pos();
                        BlockState workstationBlock = player.level().getBlockState(jobSitePos);
                        workstationString = workstationBlock.getBlock().getName().getString(); // Get the raw string
                        workstationCoordsString = String.format(" (%d, %d, %d)", jobSitePos.getX(), jobSitePos.getY(), jobSitePos.getZ());
                    }

                    MerchantOffers offers = villager.getOffers();
                    int trades = offers.size();

                    S2CVillagerInfoPacket responsePacket = new S2CVillagerInfoPacket(professionString, level, workstationString, workstationCoordsString, trades);
                    PacketDistributor.sendToPlayer(player, responsePacket);

                } catch (Exception e) {
                    WorkBenchVillager.LOGGER.error("Failed to get villager info on server:", e);
                }
            }
        });
    }
}
