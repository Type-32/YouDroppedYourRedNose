package cn.crtlprototypestudios.youdroppedyourrednose.script.classes;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorCustomModelBase extends ItemArmorBase implements IHasModel {
    public ItemArmorCustomModelBase(String name, CreativeTabs tabs, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(name, tabs, material, renderIndex, slot);
    }

    public ItemArmorCustomModelBase(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(name, material, renderIndex, slot);
    }

    @Override
    public void registerModel(){
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        if(itemStack != ItemStack.EMPTY)
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
//                ModelCustomArmour model = new ModelCustomArmour();
//                TODO Need full Impl.

                model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;

                model.isChild = _default.isChild;
                model.isRiding = _default.isRiding;
                model.isSneak = _default.isSneak;
                model.rightArmPose = _default.rightArmPose;
                model.leftArmPose = _default.leftArmPose;

                return model;
            }
        }

        return null;
    }
}
