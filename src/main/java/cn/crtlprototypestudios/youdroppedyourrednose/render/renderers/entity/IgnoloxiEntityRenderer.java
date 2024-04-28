package cn.crtlprototypestudios.youdroppedyourrednose.render.renderers.entity;

import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import cn.crtlprototypestudios.youdroppedyourrednose.render.models.entity.IgnoloxiEntityModel;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class IgnoloxiEntityRenderer extends GeoEntityRenderer<EntityIgnoloxi> {

    public IgnoloxiEntityRenderer(RenderManager renderManager) {
        super(renderManager, new IgnoloxiEntityModel());
    }
}
