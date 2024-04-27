package cn.crtlprototypestudios.youdroppedyourrednose.script.classes;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class ItemArmorGeckoBase extends GeoArmorItem implements IAnimatable, IHasModel {

    protected static final AnimationBuilder IDLE_ANIM = new AnimationBuilder().addAnimation("state.idle", true);
    public final AnimationFactory factory = new AnimationFactory(this);

    public ItemArmorGeckoBase(String name, CreativeTabs tabs, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(material, renderIndex, slot);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);
        setMaxStackSize(1);

        ModContent.ITEMS.add(this);
    }

    public ItemArmorGeckoBase(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        this(name, CreativeTabs.COMBAT, material, renderIndex, slot);
    }

    @Override
    public void registerModel() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    private <T extends ItemArmorGeckoBase> PlayState idleAnimController(final AnimationEvent<T> event) {
        event.getController().setAnimation(IDLE_ANIM);

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "Idle Controller", 0, this::idleAnimController));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
