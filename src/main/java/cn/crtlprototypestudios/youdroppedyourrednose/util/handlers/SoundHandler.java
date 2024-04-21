package cn.crtlprototypestudios.youdroppedyourrednose.util.handlers;

import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler {
    public static SoundEvent RECORD_RICKROLL;

    public static void registerSounds() {
        RECORD_RICKROLL = registerSound("surprise_disc_music");
    }

    private static SoundEvent registerSound(String name){
        ResourceLocation loc = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(loc);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
