
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package top.bluecraft.bcpack.init;

import top.bluecraft.bcpack.client.gui.LoadGunsGuiScreen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BcpackModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(BcpackModMenus.LOAD_GUNS_GUI.get(), LoadGunsGuiScreen::new);
		});
	}
}
