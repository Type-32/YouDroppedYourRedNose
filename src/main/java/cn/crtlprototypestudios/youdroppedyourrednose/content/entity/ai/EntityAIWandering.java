package cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIWandering extends EntityAIBase {
    private final EntityIgnoloxi entity;

    public EntityAIWandering(EntityIgnoloxi entity) {
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute() {
//        return !entity.isHandingOut() && !entity.isFollowingPlayer() && !entity.timeForHandout;
        return entity.getBehaviorState() == EntityIgnoloxi.BehaviorState.WANDER;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        entity.setWanderingDuration(EntityIgnoloxi.WANDERING_DURATION);
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (entity.getWanderingDuration() <= 0){
            entity.setBehaviorState(EntityIgnoloxi.BehaviorState.IDLE);
            return false;
        }

        return entity.getWanderingDuration() > 0;
    }

    @Override
    public void updateTask() {
        Main.logger.info("EntityAIWandering Task is running");
        entity.setWanderingDuration(entity.getWanderingDuration()-1);
    }

    @Override
    public void resetTask() {
        entity.setWanderingDuration(0);
    }
}
