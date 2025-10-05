package com.kizokumaru.minecraftmod.network;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class C2SVillagerInfoRequestPacketTest {

    @Test
    void packetCanBeCreated() {
        // This test verifies that the packet can be instantiated without errors.
        int villagerId = 123;
        C2SVillagerInfoRequestPacket packet = new C2SVillagerInfoRequestPacket(villagerId);

        // Verify that the data is stored correctly.
        assertEquals(villagerId, packet.villagerId(), "The villager ID should match the one provided in the constructor.");
    }

    @Test
    void packetIdIsCorrect() {
        // This test ensures the packet's unique ID has not been accidentally changed.
        assertNotNull(C2SVillagerInfoRequestPacket.ID, "Packet ID should not be null.");
        assertEquals("workbenchvillager:villager_info_request", C2SVillagerInfoRequestPacket.ID.id().toString(), "Packet ID should have the correct resource location.");
    }
}
