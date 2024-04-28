package cn.crtlprototypestudios.youdroppedyourrednose.render.models.entity;

import cn.crtlprototypestudios.youdroppedyourrednose.content.armor.JokerMaskItem;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IgnoloxiEntityModel extends AnimatedGeoModel<EntityIgnoloxi> {
    private static final ResourceLocation modelResource = new ResourceLocation(Reference.MODID, "geo/entity/ignoloxi.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(Reference.MODID, "textures/entity/ignoloxi.png");
    private static final ResourceLocation animationResource = new ResourceLocation(Reference.MODID, "animations/entity/ignoloxi.animation.json");

    @Override
    public ResourceLocation getModelLocation(EntityIgnoloxi entityIgnoloxi) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityIgnoloxi entityIgnoloxi) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityIgnoloxi entityIgnoloxi) {
        return animationResource;
    }
}
