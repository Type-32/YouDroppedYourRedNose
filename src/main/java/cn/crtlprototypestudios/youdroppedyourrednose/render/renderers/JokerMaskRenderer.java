package cn.crtlprototypestudios.youdroppedyourrednose.render.renderers;

import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.content.armor.JokerMaskItem;
import cn.crtlprototypestudios.youdroppedyourrednose.render.models.armor.JokerMaskModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class JokerMaskRenderer extends GeoArmorRenderer<JokerMaskItem> {
    public JokerMaskRenderer() {
        super(new JokerMaskModel());
        this.headBone = "bipedHead";
        this.bodyBone = "bipedBody";
        this.rightArmBone = "bipedRightArm";
        this.leftArmBone = "bipedLeftArm";
        this.rightLegBone = "bipedRightLeg";
        this.leftLegBone = "bipedLeftLeg";

        ModContent.ARMOR_RENDERERS.put(JokerMaskItem.class, this);
    }
}
