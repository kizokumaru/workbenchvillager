package com.kizokumaru.minecraftmod;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class PacketHandlerTest {

    @Test
    void handlerCanBeInstantiated() {
        // Smoke test to ensure the class can be created without errors.
        assertDoesNotThrow(() -> {
            new PacketHandler();
        }, "Instantiating the PacketHandler should not throw an exception.");
    }

    @Test
    void protocolVersionIsDefined() throws Exception {
        // This test verifies that the protocol version, which is crucial for network
        // compatibility, is correctly defined using reflection.
        Field field = PacketHandler.class.getDeclaredField("PROTOCOL_VERSION");
        field.setAccessible(true);
        String protocolVersion = (String) field.get(null);

        assertEquals("1.0", protocolVersion, "The network protocol version should be '1.0'.");
    }
}
