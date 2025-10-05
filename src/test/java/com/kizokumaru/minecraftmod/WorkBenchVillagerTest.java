package com.kizokumaru.minecraftmod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkBenchVillagerTest {

    @Test
    void modIdIsCorrect() {
        // This is a crucial test to ensure the MODID, which is used for networking,
        // resource locations, and more, is not accidentally changed.
        assertEquals("workbenchvillager", WorkBenchVillager.MODID, "The MODID should be 'workbenchvillager'.");
    }
}
