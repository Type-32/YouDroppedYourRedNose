package cn.crtlprototypestudios.youdroppedyourrednose.util.handlers;

import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class LootTableHandler {
    public static ResourceLocation registerLootTable(String name) {
        ResourceLocation temp = new ResourceLocation(Reference.MODID, name);
        ModContent.LOOT_TABLES.add(temp);
        LootTableList.register(temp);
        return temp;
    }
}
