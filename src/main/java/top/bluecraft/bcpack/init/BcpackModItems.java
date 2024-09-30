
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package top.bluecraft.bcpack.init;

import top.bluecraft.bcpack.item.TerminalItem;
import top.bluecraft.bcpack.BcpackMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

public class BcpackModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, BcpackMod.MODID);
	public static final RegistryObject<Item> TERMINAL = REGISTRY.register("terminal", () -> new TerminalItem());
	// Start of user code block custom items
	// End of user code block custom items
}
