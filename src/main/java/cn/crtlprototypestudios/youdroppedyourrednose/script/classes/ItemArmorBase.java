package cn.crtlprototypestudios.youdroppedyourrednose.script.classes;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor implements IHasModel {
    public ItemArmorBase(String name, CreativeTabs tabs, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        super(material, renderIndex, slot);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);
        setMaxStackSize(1);

        ModContent.ITEMS.add(this);
    }

    public ItemArmorBase(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot slot) {
        this(name, CreativeTabs.COMBAT, material, renderIndex, slot);
    }

    @Override
    public void registerModel() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
