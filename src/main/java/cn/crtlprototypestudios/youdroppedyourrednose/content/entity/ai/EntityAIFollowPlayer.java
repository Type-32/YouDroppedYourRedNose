package cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.EntityIgnoloxi;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Random;

public class EntityAIFollowPlayer extends EntityAIBase {
    private final EntityIgnoloxi entity;
    private final Random rand = new Random();

    public EntityAIFollowPlayer(EntityIgnoloxi entity) {
        this.entity = entity;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        entity.setFollowDuration(rand.nextInt(EntityIgnoloxi.FOLLOW_DURATION_MAX - EntityIgnoloxi.FOLLOW_DURATION_MIN + 1) + EntityIgnoloxi.FOLLOW_DURATION_MIN);

        EntityPlayer player = entity.world.getClosestPlayerToEntity(entity, EntityIgnoloxi.FOLLOW_DISTANCE);
        if (player != null) {
            entity.setTargetPlayer(player);
            entity.setFollowDuration(rand.nextInt(EntityIgnoloxi.FOLLOW_DURATION_MAX - EntityIgnoloxi.FOLLOW_DURATION_MIN + 1) + EntityIgnoloxi.FOLLOW_DURATION_MIN);
            entity.setBehaviorState(EntityIgnoloxi.BehaviorState.FOLLOW_PLAYER);
        }
    }

    @Override
    public boolean shouldExecute() {
        if (entity.getBehaviorState() != EntityIgnoloxi.BehaviorState.FOLLOW_PLAYER && entity.getBehaviorState() != EntityIgnoloxi.BehaviorState.IDLE) return false;
        return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
        // Do not execute Follow Player Behavior if Behavior State is Idle of Follow Player
        if (entity.getBehaviorState() != EntityIgnoloxi.BehaviorState.IDLE || entity.getBehaviorState() != EntityIgnoloxi.BehaviorState.FOLLOW_PLAYER) return false;

        // Finish execute when follow duration is 0 and player is not null, switch to Handout Item State
        if (entity.getTargetPlayer() != null && entity.getFollowDuration() <= 0 && entity.getBehaviorState() == EntityIgnoloxi.BehaviorState.FOLLOW_PLAYER){
            entity.setBehaviorState(EntityIgnoloxi.BehaviorState.HANDOUT_ITEM);
            return false;
        }

        if (entity.getTargetPlayer() != null) { // If entity haven't lost player
            double distance = entity.getDistanceSq(entity.getTargetPlayer());
            boolean flag = distance <= EntityIgnoloxi.FOLLOW_DISTANCE * EntityIgnoloxi.FOLLOW_DISTANCE;
            if (!flag) {
                entity.setFollowDuration(0);
                entity.setTargetPlayer(null);
                entity.setBehaviorState(EntityIgnoloxi.BehaviorState.IDLE);
            }

            return flag;
        } else { // If entity lost the player
            EntityPlayer player = entity.world.getClosestPlayerToEntity(entity, EntityIgnoloxi.FOLLOW_DISTANCE);
            if (player != null) {
                entity.setTargetPlayer(player);
                entity.setFollowDuration(rand.nextInt(EntityIgnoloxi.FOLLOW_DURATION_MAX - EntityIgnoloxi.FOLLOW_DURATION_MIN + 1) + EntityIgnoloxi.FOLLOW_DURATION_MIN);
                entity.setBehaviorState(EntityIgnoloxi.BehaviorState.FOLLOW_PLAYER);
            } else {
                entity.setFollowDuration(0);
                entity.setBehaviorState(EntityIgnoloxi.BehaviorState.IDLE);
            }
        }
        return false;
    }

    @Override
    public void updateTask() {
        Main.logger.info("EntityAIFollowPlayer Task is running");
        if (entity.getTargetPlayer() != null) {
            entity.getNavigator().tryMoveToEntityLiving(entity.getTargetPlayer(), entity.getTargetPlayer().isSprinting() ? EntityIgnoloxi.FOLLOW_SPEED * 2 : entity.getTargetPlayer().isSneaking() ? EntityIgnoloxi.FOLLOW_SPEED / 2 : EntityIgnoloxi.FOLLOW_SPEED);
            entity.getLookHelper().setLookPositionWithEntity(entity.getTargetPlayer(), 10.0F, entity.getVerticalFaceSpeed());
            entity.setFollowDuration(entity.getFollowDuration()-1);
//            entity.setBehaviorState(EntityIgnoloxi.BehaviorState.FOLLOW_PLAYER);
        }
    }

    @Override
    public void resetTask() {
        entity.setTargetPlayer(null);
        entity.setFollowDuration(0);
    }
}
