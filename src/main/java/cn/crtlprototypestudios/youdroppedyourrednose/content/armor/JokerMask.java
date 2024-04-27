package cn.crtlprototypestudios.youdroppedyourrednose.content.armor;

import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemArmorBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class JokerMask extends ItemArmorBase {

    public JokerMask(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(name, CreativeTabs.MISC, material, renderIndex, slot);
    }

//    @Override
//    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
//    {
//        if(itemStack != ItemStack.EMPTY)
//        {
//            if(itemStack.getItem() instanceof ItemArmor)
//            {
//                ModelJokerMask model = new ModelJokerMask();
//
//                model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
//
//                model.isChild = _default.isChild;
//                model.isRiding = _default.isRiding;
//                model.isSneak = _default.isSneak;
//                model.rightArmPose = _default.rightArmPose;
//                model.leftArmPose = _default.leftArmPose;
//
//                return model;
//            }
//        }
//
//        return null;
//    }
}
