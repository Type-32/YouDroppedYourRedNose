package cn.crtlprototypestudios.youdroppedyourrednose.util.handlers;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import cn.crtlprototypestudios.youdroppedyourrednose.render.renderers.entity.IgnoloxiEntityRenderer;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.logging.Logger;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModContent.ITEMS.toArray(new Item[0]));
        Main.logger.info(String.format("Registered %s Item(s)", ModContent.ITEMS.size()));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for(Item item : ModContent.ITEMS){
            if(item instanceof IHasModel)
                ((IHasModel) item).registerModel();
        }
        Main.logger.info(String.format("Registered %s Model(s)", ModContent.ITEMS.size()));
    }

    @SubscribeEvent
    public static void onRenderersRegister(ModelRegistryEvent event) {
        // Register Armor Renderers
        Class[] armorRenderersKeys = ModContent.ARMOR_RENDERERS.keySet().toArray(new Class[0]);
        for(int i = 0; i < ModContent.ARMOR_RENDERERS.size(); i++)
            GeoArmorRenderer.registerArmorRenderer(armorRenderersKeys[i], ModContent.ARMOR_RENDERERS.get(armorRenderersKeys[i]));

        Main.logger.info(String.format("Registered %s Armor Renderers", ModContent.ARMOR_RENDERERS.keySet().size()));

        // Register Entity Renderers
//        Class[] entityRenderersKeys = ModContent.ENTITY_RENDERERS.keySet().toArray(new Class[0]);
//        for(int i = 0; i < ModContent.ENTITY_RENDERERS.size(); i++)
//            RenderingRegistry.registerEntityRenderingHandler(entityRenderersKeys[i], ModContent.ENTITY_RENDERERS.get(entityRenderersKeys[i]).newInstance());
//
//        Main.logger.info(String.format("Registered %s Entity Renderers", ModContent.ENTITY_RENDERERS.keySet().size()));
    }

    public static void preInitRegistries(FMLPreInitializationEvent event){
//        EntityHandler.registerAllEntities();
//        Main.logger.info("Registered Entities");
    }

    public static void initRegistries(FMLInitializationEvent event){
        EntityHandler.registerAllEntities();
        Main.logger.info("Registered Entities");
    }
}
