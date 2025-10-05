package com.kizokumaru.minecraftmod.client.gui;

import com.kizokumaru.minecraftmod.network.S2CVillagerInfoPacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VillagerInfoScreenTest {

    // Mockito will create a fake instance of our packet class.
    @Mock
    private S2CVillagerInfoPacket mockPacket;

    @Test
    void screenCanBeInitializedWithMockPacket() {
        // This test ensures that the screen can be created with a valid (mocked)
        // packet without crashing. It doesn't render the screen, but it tests
        // the constructor's robustness.
        assertDoesNotThrow(() -> {
            new VillagerInfoScreen(mockPacket);
        }, "The VillagerInfoScreen should be created without throwing an exception when given a mock packet.");
    }
}
