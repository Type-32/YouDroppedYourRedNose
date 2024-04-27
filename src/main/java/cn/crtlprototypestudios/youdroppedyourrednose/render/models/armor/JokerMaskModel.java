package cn.crtlprototypestudios.youdroppedyourrednose.render.models.armor;

import cn.crtlprototypestudios.youdroppedyourrednose.content.armor.JokerMaskItem;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JokerMaskModel extends AnimatedGeoModel<JokerMaskItem> {
    private static final ResourceLocation modelResource = new ResourceLocation(Reference.MODID, "geo/joker_mask.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(Reference.MODID, "textures/items/joker_mask_texture.png");
    private static final ResourceLocation animationResource = new ResourceLocation(Reference.MODID, "animations/joker_mask.animation.json");

    @Override
    public ResourceLocation getModelLocation(JokerMaskItem jokerMask) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureLocation(JokerMaskItem jokerMask) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(JokerMaskItem jokerMask) {
        return animationResource;
    }
}
