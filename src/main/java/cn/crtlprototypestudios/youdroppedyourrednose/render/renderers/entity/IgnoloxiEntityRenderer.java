package cn.crtlprototypestudios.youdroppedyourrednose.render.renderers.entity;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import cn.crtlprototypestudios.youdroppedyourrednose.render.models.entity.IgnoloxiEntityModel;
import com.sun.javafx.sg.prism.NodeEffectInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.util.MatrixStack;

public class IgnoloxiEntityRenderer extends GeoEntityRenderer<EntityIgnoloxi> {

    public IgnoloxiEntityRenderer(RenderManager renderManager) {
        super(renderManager, new IgnoloxiEntityModel());
//        ModContent.ENTITY_RENDERERS.put(EntityIgnoloxi.class, this);
//        Main.logger.info("Registered Renderers");
    }

    public IgnoloxiEntityRenderer(){
        this(Minecraft.getMinecraft().getRenderManager());
    }

    private void renderHeldItem(EntityIgnoloxi entity, ItemStack itemStack) {
        GlStateManager.pushMatrix();

        // Translate to the desired position (adjust as needed)
        Vec3d itemPlaceholderPos = null;
        try {
            itemPlaceholderPos = entity.getItemPlaceholderPosition();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        GlStateManager.translate((float)itemPlaceholderPos.x, (float)itemPlaceholderPos.y, (float)itemPlaceholderPos.z);

        // Rotate the item (adjust as needed)
//        GlStateManager.rotate(Vector3f.XP.rotationDegrees(180));
//        GlStateManager.rotate(Vector3f.YP.rotationDegrees(180));

        // Scale the item (adjust as needed)
        GlStateManager.scale(0.5f, 0.5f, 0.5f);

        // Render the item
        Minecraft.getMinecraft().getRenderItem().renderItem(itemStack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);

        GlStateManager.popMatrix();
    }

    @Override
    public void renderEarly(EntityIgnoloxi animatable, float ticks, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, ticks, red, green, blue, partialTicks);
        ItemStack itemStack = animatable.getHandoutItem();
        if (itemStack != null && !itemStack.isEmpty()) {
            renderHeldItem(animatable, itemStack);
        }
    }

}
