package com.kizokumaru.minecraftmod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VillagerInfoEventHandlerTest {

    @Test
    void handlerCanBeInstantiated() {
        // This is a simple smoke test. It ensures that creating an instance
        // of the event handler does not cause any crashes. This is important because
        // the main mod class instantiates it.
        assertDoesNotThrow(() -> {
            new VillagerInfoEventHandler();
        }, "Instantiating the event handler should not throw an exception.");
    }
}
