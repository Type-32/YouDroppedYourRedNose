package cn.crtlprototypestudios.youdroppedyourrednose.custom;

import cn.crtlprototypestudios.youdroppedyourrednose.custom.armor.JokerMask;
import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemArmorBase;
import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemRecordBase;
import cn.crtlprototypestudios.youdroppedyourrednose.sounds.ModSounds;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    // Materials
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_JOKER = EnumHelper.addArmorMaterial("armor_material_joker", Reference.MODID + ":joker_material", 15, new int[]{2, 5, 6, 2}, 9, ModSounds.ITEM_ARMOR_EQUIP_JOKER, 0.0F);

    // Discs
    public static final ItemRecordBase RICK_ROLL_DISC = new ItemRecordBase("surprise_disc", ModSounds.RECORD_RICKROLL);
    public static final ItemRecordBase KOBE_DISC = new ItemRecordBase("kobe_disc", ModSounds.RECORD_SEEYOUAGAIN);
    public static final ItemRecordBase LOOSE_ASS_DISC = new ItemRecordBase("loose_ass_disc", ModSounds.RECORD_LONLEY_ROCK);
    public static final ItemRecordBase NUMB_DISC = new ItemRecordBase("numb_disc", ModSounds.RECORD_NUMB);
    public static final ItemRecordBase BBBUS_DISC = new ItemRecordBase("bbbus_disc", ModSounds.RECORD_BABY_BUS);
    public static final ItemRecordBase MDM_DISC = new ItemRecordBase("mdm_disc", ModSounds.RECORD_MA_DONG_MEI);
    public static final ItemRecordBase TELKOM_INDONESIA_DISC = new ItemRecordBase("telkom_indonesia_disc", ModSounds.RECORD_PAKET_PHOENIX);

    // Armor
    public static final ItemArmorBase JOKER_MASK = new JokerMask("joker_mask", ARMOR_MATERIAL_JOKER, 1, EntityEquipmentSlot.HEAD);
}
