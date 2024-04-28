package cn.crtlprototypestudios.youdroppedyourrednose.render.renderers.item;

import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.content.armor.JokerMaskItem;
import cn.crtlprototypestudios.youdroppedyourrednose.render.models.armor.JokerMaskItemModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class JokerMaskItemRenderer extends GeoArmorRenderer<JokerMaskItem> {
    public JokerMaskItemRenderer() {
        super(new JokerMaskItemModel());
        this.headBone = "bipedHead";
        this.bodyBone = "bipedBody";
        this.rightArmBone = "bipedRightArm";
        this.leftArmBone = "bipedLeftArm";
        this.rightLegBone = "bipedRightLeg";
        this.leftLegBone = "bipedLeftLeg";

        ModContent.ARMOR_RENDERERS.put(JokerMaskItem.class, this);
    }
}
