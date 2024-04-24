package cn.crtlprototypestudios.youdroppedyourrednose.render.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ModelJokerMask extends ModelBiped {
    public ModelRenderer jokerMask;
    public ModelJokerMask() {
        this.textureWidth = 160;
        this.textureHeight = 160;
        jokerMask = new ModelRenderer(this, 0, 0);

        this.bipedHead.addChild(jokerMask);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    public void SetRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
