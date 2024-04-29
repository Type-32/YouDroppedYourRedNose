package cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai;

import cn.crtlprototypestudios.youdroppedyourrednose.content.ModLootTables;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import cn.crtlprototypestudios.youdroppedyourrednose.util.handlers.LootTableHandler;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

public class EntityAIHandoutItem extends EntityAIBase {
    private final EntityIgnoloxi entity;
    private final Random rand = new Random();

    public EntityAIHandoutItem(EntityIgnoloxi entity) {
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute() {
        return entity.getFollowDuration() <= 0 && entity.getHandoutDuration() == 0;
    }

    @Override
    public void startExecuting() {
        entity.setHandoutDuration(rand.nextInt(EntityIgnoloxi.HANDOUT_DURATION_MAX - EntityIgnoloxi.HANDOUT_DURATION_MIN + 1) + EntityIgnoloxi.HANDOUT_DURATION_MIN);
        entity.generateHandoutItem();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return entity.getHandoutDuration() > 0;
    }

    @Override
    public void updateTask() {
        entity.setHandoutDuration(entity.getHandoutDuration()-1);
        // TODO: Display handoutItem in entity's hand
    }

    @Override
    public void resetTask() {
        entity.setHandoutDuration(0);
        entity.setHandoutItem(ItemStack.EMPTY);
    }
}
