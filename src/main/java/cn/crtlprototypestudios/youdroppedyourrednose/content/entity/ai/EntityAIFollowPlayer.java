package cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai;

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
    public boolean shouldExecute() {
        if (entity.getTargetPlayer() == null) {
            EntityPlayer player = entity.world.getClosestPlayerToEntity(entity, 5.0D);
            if (player != null) {
                entity.setTargetPlayer(player);
                entity.setFollowDuration(rand.nextInt(EntityIgnoloxi.FOLLOW_DURATION_MAX - EntityIgnoloxi.FOLLOW_DURATION_MIN + 1) + EntityIgnoloxi.FOLLOW_DURATION_MIN);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (entity.getTargetPlayer() != null && entity.getFollowDuration() > 0) {
            double distance = entity.getDistanceSq(entity.getTargetPlayer());
            return distance <= 5.0D * 5.0D;
        }
        return false;
    }

    @Override
    public void updateTask() {
        if (entity.getTargetPlayer() != null) {
            entity.getNavigator().tryMoveToEntityLiving(entity.getTargetPlayer(), entity.getTargetPlayer().isSneaking() ? EntityIgnoloxi.FOLLOW_SPEED / 2 : EntityIgnoloxi.FOLLOW_SPEED);
            entity.getLookHelper().setLookPositionWithEntity(entity.getTargetPlayer(), 10.0F, entity.getVerticalFaceSpeed());
            entity.setFollowDuration(entity.getFollowDuration()-1);
        }
    }

    @Override
    public void resetTask() {
        entity.setTargetPlayer(null);
        entity.setFollowDuration(0);
    }
}
