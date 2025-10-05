package com.kizokumaru.minecraftmod.network;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class S2CVillagerInfoPacketTest {

    @Test
    void packetCanBeCreated() {
        // Create dummy string data.
        String profession = "entity.minecraft.villager.farmer";
        int level = 3;
        String workstation = "Composter";
        String workstationCoords = " (1, 2, 3)";
        int trades = 5;

        // Instantiate the packet.
        S2CVillagerInfoPacket packet = new S2CVillagerInfoPacket(profession, level, workstation, workstationCoords, trades);

        // Verify that all data is stored correctly.
        assertAll("Packet data should be stored correctly",
            () -> assertEquals(profession, packet.profession(), "Profession string should match."),
            () -> assertEquals(level, packet.level(), "Level should match."),
            () -> assertEquals(workstation, packet.workstation(), "Workstation string should match."),
            () -> assertEquals(workstationCoords, packet.workstationCoords(), "Workstation coordinates should match."),
            () -> assertEquals(trades, packet.trades(), "Number of trades should match.")
        );
    }

    @Test
    void packetIdIsCorrect() {
        // Verify the packet's unique ID.
        assertNotNull(S2CVillagerInfoPacket.ID, "Packet ID should not be null.");
        assertEquals("workbenchvillager:villager_info_response", S2CVillagerInfoPacket.ID.id().toString(), "Packet ID should have the correct resource location.");
    }
}
