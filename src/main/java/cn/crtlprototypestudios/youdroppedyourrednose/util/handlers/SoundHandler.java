package cn.crtlprototypestudios.youdroppedyourrednose.util.handlers;

import cn.crtlprototypestudios.youdroppedyourrednose.sounds.ModSounds;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundHandler {
    public static SoundEvent registerSound(String name){
        ResourceLocation loc = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(loc);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

    @SubscribeEvent
    public void onSoundRegister(){
        ModSounds.registerSounds();
    }
}
