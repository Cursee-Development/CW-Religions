package com.cursee.cw_religions.core.world.inventory;

import com.cursee.cw_religions.client.gui.screens.inventory.AltarScreen;
import com.cursee.cw_religions.core.registry.ModMenus;
import com.cursee.cw_religions.core.religion.Religion;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class AltarMenu extends AbstractContainerMenu {

    public final NonNullList<Religion> religions = NonNullList.create();

    public AltarMenu(int containerID, Inventory playerInventory) {
        this(containerID, playerInventory, ContainerLevelAccess.NULL);
    }

    public AltarMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(ModMenus.ALTAR, containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public int getRowIndexForScroll(float scrollOffs) {
        return Math.max((int)((double)(scrollOffs * (float)this.calculateRowCount()) + (double)0.5F), 0);
    }

    public int calculateRowCount() {
        return Mth.positiveCeilDiv(this.religions.size(), 9) - 5;
    }

    public float getScrollForRowIndex(int rowIndex) {
        return Mth.clamp((float)rowIndex / (float)this.calculateRowCount(), 0.0F, 1.0F);
    }

    // todo impl scrolling / searching
    public void scrollTo(float pos) {
        int i = this.getRowIndexForScroll(pos);

        for(int j = 0; j < 5; ++j) {
            for(int k = 0; k < 9; ++k) {
                int l = k + (j + i) * 9;
                if (l >= 0 && l < this.religions.size()) {
                    // CreativeModeInventoryScreen.CONTAINER.setItem(k + j * 9, (ItemStack)this.items.get(l));
                }
                else {
                    // CreativeModeInventoryScreen.CONTAINER.setItem(k + j * 9, ItemStack.EMPTY);
                }
            }
        }

    }
}
