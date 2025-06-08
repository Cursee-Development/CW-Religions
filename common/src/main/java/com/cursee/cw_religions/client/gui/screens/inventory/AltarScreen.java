package com.cursee.cw_religions.client.gui.screens.inventory;

import com.cursee.cw_religions.CWReligions;
import com.cursee.cw_religions.core.world.inventory.AltarMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class AltarScreen extends AbstractContainerScreen<AltarMenu> {

    private static final ResourceLocation ALTAR_LOCATION = CWReligions.identifier("textures/gui/container/altar.png");

    private final Player player;

    public AltarScreen(AltarMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.player = playerInventory.player;
        this.inventoryLabelY = this.height + 5;
        this.titleLabelY = this.height + 5;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(ALTAR_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
