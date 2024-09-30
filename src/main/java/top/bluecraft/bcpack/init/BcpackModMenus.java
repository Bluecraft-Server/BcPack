
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package top.bluecraft.bcpack.init;

import top.bluecraft.bcpack.world.inventory.LoadGunsGuiMenu;
import top.bluecraft.bcpack.BcpackMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class BcpackModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BcpackMod.MODID);
	public static final RegistryObject<MenuType<LoadGunsGuiMenu>> LOAD_GUNS_GUI = REGISTRY.register("load_guns_gui", () -> IForgeMenuType.create(LoadGunsGuiMenu::new));
}
