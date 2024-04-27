package cn.crtlprototypestudios.youdroppedyourrednose.script.classes;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    @Override
    public void registerModel() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    public ItemBase(String name, CreativeTabs tabs) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);

        ModContent.ITEMS.add(this);
    }
}
