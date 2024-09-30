package top.bluecraft.bcpack.common.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.bluecraft.bcpack.BcPack;
import top.bluecraft.bcpack.client.gui.LoadGunsScreen;
import top.bluecraft.bcpack.common.init.Registration;

@Mod.EventBusSubscriber(modid = BcPack.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.TERMINAL_MENU.get(), LoadGunsScreen::new);
        });
    }
}