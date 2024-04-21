package cn.crtlprototypestudios.youdroppedyourrednose.items;

import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemRecordBase;
import cn.crtlprototypestudios.youdroppedyourrednose.sounds.ModSounds;
import cn.crtlprototypestudios.youdroppedyourrednose.util.handlers.SoundHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item RICK_ROLL_DISC = new ItemRecordBase("surprise_disc", ModSounds.RECORD_RICKROLL, CreativeTabs.MISC);
    public static final Item KOBE_DISC = new ItemRecordBase("kobe_disc", ModSounds.RECORD_SEEYOUAGAIN, CreativeTabs.MISC);
    public static final Item LOOSE_ASS_DISC = new ItemRecordBase("loose_ass_disc", ModSounds.RECORD_LONLEY_ROCK, CreativeTabs.MISC);
    public static final Item NUMB_DISC = new ItemRecordBase("numb_disc", ModSounds.RECORD_NUMB, CreativeTabs.MISC);
    public static final Item BBBUS_DISC = new ItemRecordBase("bbbus_disc", ModSounds.RECORD_BABY_BUS, CreativeTabs.MISC);
    public static final Item MDM_DISC = new ItemRecordBase("mdm_disc", ModSounds.RECORD_MA_DONG_MEI, CreativeTabs.MISC);
    public static final Item TELKOM_INDONESIA_DISC = new ItemRecordBase("telkom_indonesia_disc", ModSounds.RECORD_PAKET_PHOENIX, CreativeTabs.MISC);
}
