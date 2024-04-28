package cn.crtlprototypestudios.youdroppedyourrednose.content;

import cn.crtlprototypestudios.youdroppedyourrednose.content.armor.JokerMaskItem;
import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemBase;
import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemRecordBase;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
    // Materials
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_JOKER = EnumHelper.addArmorMaterial("armor_material_joker", Reference.MODID + ":joker_material", 6, new int[]{2, 5, 6, 2}, 9, ModSounds.ITEM_ARMOR_EQUIP_JOKER, 0.0F);
    // Armor
    public static final JokerMaskItem JOKER_MASK = new JokerMaskItem("joker_mask", ARMOR_MATERIAL_JOKER, 1, EntityEquipmentSlot.HEAD);
    // Discs
    public static final ItemRecordBase RICK_ROLL_DISC = new ItemRecordBase("surprise_disc", ModSounds.RECORD_RICKROLL);
    public static final ItemRecordBase KOBE_DISC = new ItemRecordBase("kobe_disc", ModSounds.RECORD_SEEYOUAGAIN);
    public static final ItemRecordBase LOOSE_ASS_DISC = new ItemRecordBase("loose_ass_disc", ModSounds.RECORD_LONLEY_ROCK);
    public static final ItemRecordBase NUMB_DISC = new ItemRecordBase("numb_disc", ModSounds.RECORD_NUMB);
    public static final ItemRecordBase BBBUS_DISC = new ItemRecordBase("bbbus_disc", ModSounds.RECORD_BABY_BUS);
    public static final ItemRecordBase MDM_DISC = new ItemRecordBase("mdm_disc", ModSounds.RECORD_MA_DONG_MEI);
    public static final ItemRecordBase TELKOM_INDONESIA_DISC = new ItemRecordBase("telkom_indonesia_disc", ModSounds.RECORD_PAKET_PHOENIX);
    // Item
    public static final ItemBase WATER_COOLER_PLASTIC = new ItemBase("water_cooler_plastic", CreativeTabs.MISC);
}
