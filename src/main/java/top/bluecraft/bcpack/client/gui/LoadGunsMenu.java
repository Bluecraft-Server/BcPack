package top.bluecraft.bcpack.client.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import top.bluecraft.bcpack.common.init.Registration;

public class LoadGunsMenu extends AbstractContainerMenu {
    public LoadGunsMenu(int containerId, Inventory inventory) {
        super(Registration.TERMINAL_MENU.get(), containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
