package cn.crtlprototypestudios.youdroppedyourrednose.util.handlers;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import cn.crtlprototypestudios.youdroppedyourrednose.render.renderers.entity.IgnoloxiEntityRenderer;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntityHandler {
    private static int idPlaceholder = 120;

    /**
     * @param name The name id of the entity. i.e. "ignoloxi", shows up in the game as "<namespace>:ignoloxi"
     * @param entity The entity class.
     * @param renderer The entity's Geckolib renderer.
     * @param id The entity id. Starts from 120.
     * @param range AI Active range
     * @param color1 The Decimal color code for the egg
     * @param color2 The Decimal color code for the egg 2
     */
    public static void registerEntity(String name, Class<? extends Entity> entity, GeoEntityRenderer renderer, int id, int range, int color1, int color2){
        // For color, use decimal value: https://www.mathsisfun.com/hexadecimal-decimal-colors.html
        registerEntity(name, entity, renderer, id, range, color1, color2, 1, true);
    }
    public static void registerEntity(String name, Class<? extends Entity> entity, GeoEntityRenderer renderer, int id, int range, int color1, int color2, int updateTickFrequency, boolean sendsVelocityUpdates){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), entity, name, id, Main.instance, range, updateTickFrequency, sendsVelocityUpdates, color1, color2);
//        ModContent.ENTITIES.add();
        ModContent.ENTITY_RENDERERS.put(entity, renderer);
    }

    public static void registerAllEntities() {
        registerEntity(EntityIgnoloxi.ENTITY_REGISTRY_NAME, EntityIgnoloxi.class, new IgnoloxiEntityRenderer(), retrieveEntityIdAndRefresh(), 100, 1704740, 4784287);
    }

    public static int retrieveEntityIdAndRefresh(){
        idPlaceholder++;
        return idPlaceholder-1;
    }
}
