package cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai;

import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIWandering extends EntityAIBase {
    private final EntityIgnoloxi entity;

    public EntityAIWandering(EntityIgnoloxi entity) {
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute() {
        return entity.getHandoutDuration() <= 0 && entity.getWanderingDuration() == 0;
    }

    @Override
    public void startExecuting() {
        entity.setWanderingDuration(EntityIgnoloxi.WANDERING_DURATION);
    }

    @Override
    public boolean shouldContinueExecuting() {
        return entity.getWanderingDuration() > 0;
    }

    @Override
    public void updateTask() {
        entity.setWanderingDuration(entity.getWanderingDuration()-1);
    }

    @Override
    public void resetTask() {
        entity.setWanderingDuration(0);
    }
}
