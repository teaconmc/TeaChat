package org.teacon.teachat;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod("tea_chat")
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = "tea_chat")
public class TeaChat {

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            NeoForge.EVENT_BUS.register(PlayerPrefixAndSuffix.class);
        }
    }

}
