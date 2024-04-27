package cn.crtlprototypestudios.youdroppedyourrednose.render.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ModelJokerMask extends ModelBiped {
    public ModelRenderer Mask;

    public ModelJokerMask() {
        this.textureWidth = 160;
        this.textureHeight = 160;

        this.Mask = new ModelRenderer(this, 0, 15);
        this.Mask.setRotationPoint(-4.0F, -7.8F, -4.0F);
        this.Mask.addBox(0.0F, 0.0F, -1.0F, 160, 127, 1, 0.0F);

        this.bipedHead.addChild(Mask);
        this.bipedHeadwear.addChild(Mask);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Mask.offsetX, this.Mask.offsetY, this.Mask.offsetZ);
        GlStateManager.translate(this.Mask.rotationPointX * f5, this.Mask.rotationPointY * f5, this.Mask.rotationPointZ * f5);
        GlStateManager.scale(0.05D, 0.06D, 1.0D);
        GlStateManager.translate(-this.Mask.offsetX, -this.Mask.offsetY, -this.Mask.offsetZ);
        GlStateManager.translate(-this.Mask.rotationPointX * f5, -this.Mask.rotationPointY * f5, -this.Mask.rotationPointZ * f5);
        super.render(entity, f, f1, f2, f3, f4, f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula Model Editor to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
