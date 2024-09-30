package top.bluecraft.bcpack.common.items;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;
import top.bluecraft.bcpack.BcPack;
import top.bluecraft.bcpack.client.gui.LoadGunsMenu;
import top.bluecraft.bcpack.client.gui.LoadGunsScreen;

public class Terminal extends Item implements MenuProvider {
    public Terminal() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            // 打开自定义 GUI
            player.openMenu(this);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("loadgunsmenu");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new LoadGunsMenu(i, inventory);
    }
}
