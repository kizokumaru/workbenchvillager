package com.kizokumaru.minecraftmod.client.gui;

import com.kizokumaru.minecraftmod.network.S2CVillagerInfoPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class VillagerInfoScreen extends Screen {
    private final S2CVillagerInfoPacket info;
    private Component professionComponent;
    private Component workstationComponent;

    public VillagerInfoScreen(S2CVillagerInfoPacket info) {
        super(Component.literal("Villager Info"));
        this.info = info;
    }

    @Override
    protected void init() {
        super.init();
        // Add a button to close the screen
        this.addRenderableWidget(Button.builder(Component.literal("Done"), (button) -> this.onClose())
                .bounds(this.width / 2 - 50, this.height - 30, 100, 20)
                .build());

        // Convert strings to components here, on the client side
        this.professionComponent = info.profession().equals("None") ? Component.literal("None") : Component.translatable(info.profession());
        this.workstationComponent = Component.literal(info.workstation()).append(Component.literal(info.workstationCoords()));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        // Draw a semi-transparent background
        guiGraphics.fill(0, 0, this.width, this.height, 0x80000000);

        // Title
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Information
        int y = 50;
        guiGraphics.drawString(this.font, Component.literal("Profession: ").append(this.professionComponent), this.width / 2 - 80, y, 0xFFFFFF);
        y += 20;
        guiGraphics.drawString(this.font, "Level: " + info.level(), this.width / 2 - 80, y, 0xFFFFFF);
        y += 20;
        guiGraphics.drawString(this.font, Component.literal("Workstation: ").append(this.workstationComponent), this.width / 2 - 80, y, 0xFFFFFF);
        y += 20;
        guiGraphics.drawString(this.font, "Trades available: " + info.trades(), this.width / 2 - 80, y, 0xFFFFFF);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
