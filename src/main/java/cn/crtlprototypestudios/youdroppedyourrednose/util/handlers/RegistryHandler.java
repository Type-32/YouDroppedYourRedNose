package cn.crtlprototypestudios.youdroppedyourrednose.util.handlers;

import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModContent.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for(Item item : ModContent.ITEMS){
            if(item instanceof IHasModel)
                ((IHasModel) item).registerModel();
        }
    }

    @SubscribeEvent
    public static void onRenderersRegister(ModelRegistryEvent event) {
        // Register Armor Renderers
        Class[] armorRenderersKeys = ModContent.ARMOR_RENDERERS.keySet().toArray(new Class[0]);

        for(int i = 0; i < ModContent.ARMOR_RENDERERS.size(); i++){
            GeoArmorRenderer.registerArmorRenderer(armorRenderersKeys[i], ModContent.ARMOR_RENDERERS.get(armorRenderersKeys[i]));
        }
    }

    public static void preInitRegistries(FMLPreInitializationEvent event){
        EntityHandler.registerAllEntities();
    }
}
