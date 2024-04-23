package cn.crtlprototypestudios.youdroppedyourrednose.items;

import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemArmorBase;
import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemRecordBase;
import cn.crtlprototypestudios.youdroppedyourrednose.sounds.ModSounds;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import cn.crtlprototypestudios.youdroppedyourrednose.util.handlers.SoundHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    // Materials
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_JOKER = EnumHelper.addArmorMaterial("armor_material_joker", Reference.MODID + ":joker_material", 15, new int[]{2, 5, 6, 2}, 9, ModSounds.ITEM_ARMOR_EQUIP_JOKER, 0.0F);

    // Discs
    public static final Item RICK_ROLL_DISC = new ItemRecordBase("surprise_disc", ModSounds.RECORD_RICKROLL);
    public static final Item KOBE_DISC = new ItemRecordBase("kobe_disc", ModSounds.RECORD_SEEYOUAGAIN);
    public static final Item LOOSE_ASS_DISC = new ItemRecordBase("loose_ass_disc", ModSounds.RECORD_LONLEY_ROCK);
    public static final Item NUMB_DISC = new ItemRecordBase("numb_disc", ModSounds.RECORD_NUMB);
    public static final Item BBBUS_DISC = new ItemRecordBase("bbbus_disc", ModSounds.RECORD_BABY_BUS);
    public static final Item MDM_DISC = new ItemRecordBase("mdm_disc", ModSounds.RECORD_MA_DONG_MEI);
    public static final Item TELKOM_INDONESIA_DISC = new ItemRecordBase("telkom_indonesia_disc", ModSounds.RECORD_PAKET_PHOENIX);

    // Armor
    public static final Item JOKER_MASK = new ItemArmorBase("joker_mask", ARMOR_MATERIAL_JOKER, 1, EntityEquipmentSlot.HEAD);
}
