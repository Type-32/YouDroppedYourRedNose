package cn.crtlprototypestudios.youdroppedyourrednose;

import cn.crtlprototypestudios.youdroppedyourrednose.proxy.CommonProxy;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import cn.crtlprototypestudios.youdroppedyourrednose.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
    public static Logger logger;

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        RegistryHandler.preInitRegistries(event);
//        ModSounds.registerSounds();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("[{}] Initialization", Reference.NAME);

        GeckoLib.initialize();
        RegistryHandler.initRegistries(event);
    }
}
