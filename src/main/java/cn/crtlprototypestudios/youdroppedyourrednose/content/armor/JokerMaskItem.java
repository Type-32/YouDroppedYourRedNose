package cn.crtlprototypestudios.youdroppedyourrednose.content.armor;

import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemArmorBase;
import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemArmorGeckoBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class JokerMaskItem extends ItemArmorGeckoBase implements IAnimatable {

    public final AnimationFactory factory = new AnimationFactory(this);

    public JokerMaskItem(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(name, CreativeTabs.MISC, material, renderIndex, slot);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "Idle Controller", 0, animationEvent -> PlayState.CONTINUE));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
