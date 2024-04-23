package cn.crtlprototypestudios.youdroppedyourrednose.script.classes;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.items.ModItems;
import cn.crtlprototypestudios.youdroppedyourrednose.script.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemRecordBase extends ItemRecord implements IHasModel {
    @Override
    public void registerModel() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    public ItemRecordBase(String name, SoundEvent soundEvent, CreativeTabs tabs) {
        super(name, soundEvent);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);

        ModItems.ITEMS.add(this);
    }

    public ItemRecordBase(String name, SoundEvent soundEvent) {
        this(name, soundEvent, CreativeTabs.MISC);
    }
}
