package cn.crtlprototypestudios.youdroppedyourrednose.util.handlers;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler {
    public static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
        // For color, use decimal value: https://www.mathsisfun.com/hexadecimal-decimal-colors.html
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), entity, name, id, Main.instance, range, 1, true, color1, color2);
    }
    public static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2, int updateTickFrequency){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), entity, name, id, Main.instance, range, updateTickFrequency, true, color1, color2);
    }

    public static void registerAllEntities() {
        registerEntity(EntityIgnoloxi.ENTITY_REGISTRY_NAME, EntityIgnoloxi.class, EntityIgnoloxi.ENTITY_REGISTRY_ID, 50, 1704740, 4784287);
    }
}
