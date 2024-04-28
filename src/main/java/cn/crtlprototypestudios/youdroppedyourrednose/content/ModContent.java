package cn.crtlprototypestudios.youdroppedyourrednose.content;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.*;

public class ModContent {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final List<Entity> ENTITIES = new ArrayList<>();
    public static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();
    public static final Map<Class<? extends EntityCreature>, GeoEntityRenderer> ENTITY_RENDERERS = new HashMap<>();
    public static final Map<Class<? extends ItemArmor>, GeoArmorRenderer> ARMOR_RENDERERS = new HashMap<>();
}
