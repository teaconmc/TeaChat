package org.teacon.teachat;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod("tea_chat")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = "tea_chat")
public class TeaChat {

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            MinecraftForge.EVENT_BUS.register(PlayerPrefixAndSuffix.class);
        }
    }

}
