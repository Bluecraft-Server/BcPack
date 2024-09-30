package top.bluecraft.bcpack.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.bluecraft.bcpack.BcPack;
import top.bluecraft.bcpack.client.gui.LoadGunsMenu;
import top.bluecraft.bcpack.common.items.Terminal;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BcPack.MODID);
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BcPack.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BcPack.MODID);

    // Item Registration
    public static final RegistryObject<Item> TERMINAL = ITEMS.register("terminal", Terminal::new);

    // Menu Registration
    public static final RegistryObject<MenuType<LoadGunsMenu>> TERMINAL_MENU = MENUS.register("terminal_menu", () -> IForgeMenuType.create((int containerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf) -> new LoadGunsMenu(containerId, inventory)));


    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("bc_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> TERMINAL.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TERMINAL.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());





}
