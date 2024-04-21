package cn.crtlprototypestudios.youdroppedyourrednose.items;

import cn.crtlprototypestudios.youdroppedyourrednose.script.classes.ItemRecordBase;
import cn.crtlprototypestudios.youdroppedyourrednose.util.handlers.SoundHandler;
import cn.crtlprototypestudios.youdroppedyourrednose.items.discs.RickRollDisc;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item RICK_ROLL_DISC = new RickRollDisc();
}
