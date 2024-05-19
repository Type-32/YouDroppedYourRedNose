package cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
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
        if(entity.getBehaviorState() != EntityIgnoloxi.BehaviorState.HANDOUT_ITEM) return false;

        return entity.getFollowDuration() <= 0 && entity.getBehaviorState() == EntityIgnoloxi.BehaviorState.HANDOUT_ITEM && !entity.isWandering();
    }

    @Override
    public void startExecuting() {
        entity.setHandoutDuration(rand.nextInt(EntityIgnoloxi.HANDOUT_DURATION_MAX - EntityIgnoloxi.HANDOUT_DURATION_MIN + 1) + EntityIgnoloxi.HANDOUT_DURATION_MIN);
        entity.generateHandoutItem();
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (entity.getHandoutDuration() <= 0 && entity.getBehaviorState() == EntityIgnoloxi.BehaviorState.HANDOUT_ITEM) {
            entity.setBehaviorState(EntityIgnoloxi.BehaviorState.WANDER);
            return false;
        }

        return entity.getHandoutDuration() > 0 && entity.getBehaviorState() == EntityIgnoloxi.BehaviorState.HANDOUT_ITEM;
    }

    @Override
    public void updateTask() {
        Main.logger.info("EntityAIHandoutItem Task is running");
        entity.setHandoutDuration(entity.getHandoutDuration()-1);
        // TODO: Display handoutItem in entity's hand
    }

    @Override
    public void resetTask() {
        entity.setHandoutDuration(0);
        entity.setHandoutItem(ItemStack.EMPTY);
    }
}
