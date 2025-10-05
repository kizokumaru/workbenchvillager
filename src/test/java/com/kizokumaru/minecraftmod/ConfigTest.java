package com.kizokumaru.minecraftmod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void configSpecIsNotNull() {
        // This test ensures that the configuration specification object, which FML uses
        // to create the config file, is not null.
        assertNotNull(Config.SPEC, "The ModConfigSpec should not be null.");
    }

    @Test
    void configValuesAreDefined() {
        // This test verifies that the config value holders themselves are not null.
        // It doesn't check the value, just that they are defined in the code.
        assertAll("Config values should be defined",
            () -> assertNotNull(Config.LOG_DIRT_BLOCK, "LOG_DIRT_BLOCK config value should be defined."),
            () -> assertNotNull(Config.MAGIC_NUMBER, "MAGIC_NUMBER config value should be defined."),
            () -> assertNotNull(Config.MAGIC_NUMBER_INTRODUCTION, "MAGIC_NUMBER_INTRODUCTION config value should be defined."),
            () -> assertNotNull(Config.ITEM_STRINGS, "ITEM_STRINGS config value should be defined.")
        );
    }
}
