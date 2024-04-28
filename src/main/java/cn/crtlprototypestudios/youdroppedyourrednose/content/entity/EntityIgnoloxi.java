package cn.crtlprototypestudios.youdroppedyourrednose.content.entity;

import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIFollowPlayer;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIHandoutItem;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIWandering;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityIgnoloxi extends EntityCreature {

    public static final int FOLLOW_DURATION_MIN = 20 * 20; // 20 seconds
    public static final int FOLLOW_DURATION_MAX = 60 * 20; // 1 minute
    public static final int HANDOUT_DURATION_MIN = 8 * 20; // 8 seconds
    public static final int HANDOUT_DURATION_MAX = 14 * 20; // 14 seconds
    public static final int WANDERING_DURATION = 60 * 20; // 1 minute
    public static final double FOLLOW_SPEED = 1.0D;
    public static final double FOLLOW_DISTANCE = 1.0D;

    private EntityPlayer targetPlayer;
    private int followDuration;
    private int handoutDuration;
    private int wanderingDuration;
    private ItemStack handoutItem;

    public EntityIgnoloxi(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
        this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAIHandoutItem(this));
        this.tasks.addTask(4, new EntityAIWandering(this));
        this.tasks.addTask(5, new EntityAIFollowPlayer(this));
    }

    public EntityPlayer getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(EntityPlayer targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public int getFollowDuration() {
        return followDuration;
    }

    public void setFollowDuration(int followDuration) {
        this.followDuration = followDuration;
    }

    public int getHandoutDuration() {
        return handoutDuration;
    }

    public void setHandoutDuration(int handoutDuration) {
        this.handoutDuration = handoutDuration;
    }

    public int getWanderingDuration() {
        return wanderingDuration;
    }

    public void setWanderingDuration(int wanderingDuration) {
        this.wanderingDuration = wanderingDuration;
    }

    public ItemStack getHandoutItem() {
        return handoutItem;
    }

    public void setHandoutItem(ItemStack handoutItem) {
        this.handoutItem = handoutItem;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!handoutItem.isEmpty() && player.getHeldItem(hand).isEmpty()) {
            player.setHeldItem(hand, handoutItem);
            handoutItem = ItemStack.EMPTY;
            handoutDuration = 0;
            wanderingDuration = WANDERING_DURATION;
            return true;
        }
        return super.processInteract(player, hand);
    }
}
