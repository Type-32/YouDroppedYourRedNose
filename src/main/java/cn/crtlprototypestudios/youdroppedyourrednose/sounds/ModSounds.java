package cn.crtlprototypestudios.youdroppedyourrednose.sounds;

import cn.crtlprototypestudios.youdroppedyourrednose.util.handlers.SoundHandler;
import net.minecraft.util.SoundEvent;

public class ModSounds {

    public static SoundEvent RECORD_RICKROLL;

    public static void registerSounds() {
        ModSounds.RECORD_RICKROLL = SoundHandler.registerSound("surprise_disc_music");
    }
}
