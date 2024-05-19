package cn.crtlprototypestudios.youdroppedyourrednose.content.entity;

import cn.crtlprototypestudios.youdroppedyourrednose.Main;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIFollowPlayer;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIHandoutItem;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIWandering;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModLootTables;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModSounds;
import cn.crtlprototypestudios.youdroppedyourrednose.render.models.entity.IgnoloxiEntityModel;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.snapshot.BoneSnapshot;
import software.bernie.geckolib3.geo.render.built.GeoModel;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import java.util.List;
import java.util.Random;

public class EntityIgnoloxi extends EntityCreature implements IAnimatable {

    public BehaviorState getBehaviorState() {
        return behaviorState;
    }

    public void setBehaviorState(BehaviorState behaviorState) {
        this.behaviorState = behaviorState;
    }

    public enum BehaviorState {
        FOLLOW_PLAYER,
        HANDOUT_ITEM,
        WANDER,
        IDLE
    }

    public static final String ENTITY_REGISTRY_NAME = "ignoloxi";
    public static final int ENTITY_REGISTRY_ID = 120;

    protected static final AnimationBuilder IDLE_1_ANIM = new AnimationBuilder().loop("state.idle_1");
    protected static final AnimationBuilder IDLE_2_ANIM = new AnimationBuilder().loop("state.idle_2");
    protected static final AnimationBuilder WALK_ANIM = new AnimationBuilder().loop("state.walking");
    protected static final AnimationBuilder SPRINT_ANIM = new AnimationBuilder().addAnimation("state.sprinting", true);
    protected static final AnimationBuilder HANDOUT_START_ANIM = new AnimationBuilder().playAndHold("state.handout");
    protected static final AnimationBuilder HANDOUT_IDLE_ANIM = new AnimationBuilder().addAnimation("state.handout_idle", true);
    protected static final AnimationBuilder HANDOUT_END_ANIM = new AnimationBuilder().addAnimation("state.handout_end", true);
    protected static final AnimationBuilder HANDOUT_TAKEN_ANIM = new AnimationBuilder().addAnimation("state.handout_taken", true);
    private final AnimationFactory factory;

    public static final int FOLLOW_DURATION_MIN = 20; // 20 seconds
    public static final int FOLLOW_DURATION_MAX = 30; // 30 seconds
    public static final int HANDOUT_DURATION_MIN = 14; // 8 seconds
    public static final int HANDOUT_DURATION_MAX = 20; // 14 seconds
    public static final int WANDERING_DURATION = 20; // 1 minute, debug 20 seconds
    public static final double FOLLOW_SPEED = 1.5D;
    public static final double FOLLOW_DISTANCE = 3.0D;

    private BehaviorState behaviorState = BehaviorState.FOLLOW_PLAYER;
    private EntityPlayer targetPlayer;
    private int followDuration;
    private int handoutDuration;
    private int wanderingDuration;
    private ItemStack handoutItem;

    public EntityIgnoloxi(World worldIn) {
        // Make sure this class has no usages; The entity'll be initialized not by creating a physical object in the ModContent class, but via Implicit Registry in the EntityHandler class
        super(worldIn);
        ModContent.ENTITIES.add(this);
//        this.setCustomNameTag("Ignoloxi");
//        this.setAlwaysRenderNameTag(true);
        handoutItem = ItemStack.EMPTY;
        factory = new AnimationFactory(this);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, (float)FOLLOW_DISTANCE));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(4, new EntityAIFollowPlayer(this));
        this.tasks.addTask(5, new EntityAIHandoutItem(this));
        this.tasks.addTask(4, new EntityAIWandering(this));
    }

    @Override
    public float getEyeHeight() {
        return 1.62F - 0.08F;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return ModLootTables.ENTITY_IGNOLOXI;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        // TODO record sound for the entity in the resources folder
        return ModSounds.ENTITY_IGNOLOXI_IDLE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        // TODO record sound for the entity in the resources folder
        return ModSounds.ENTITY_IGNOLOXI_HURT;
    }

    @Nullable
    protected SoundEvent getHandoutSound() {
        // TODO record sound for the entity in the resources folder
        return ModSounds.ENTITY_IGNOLOXI_HANDOUT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        // TODO record sound for the entity in the resources folder
        return ModSounds.ENTITY_IGNOLOXI_DEATH;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (handoutItem != null && !handoutItem.isEmpty() && player.getHeldItem(hand).isEmpty() && isHandingOut() && getBehaviorState() == BehaviorState.HANDOUT_ITEM) {
            setHandoutDuration(0);
            setFollowDuration(0);
            player.setHeldItem(hand, handoutItem);
            handoutItem = ItemStack.EMPTY;
            setWanderingDuration(WANDERING_DURATION);
            setBehaviorState(BehaviorState.WANDER);
            return true;
        }
        return super.processInteract(player, hand);
    }

    protected <E extends EntityIgnoloxi> PlayState stateController(final AnimationEvent<E> event) {
        if (event.getAnimatable().isHandingOut() || event.getAnimatable().getBehaviorState() == BehaviorState.HANDOUT_ITEM) {
            event.getController().setAnimation(HANDOUT_START_ANIM);
        } else if (event.isMoving() && event.getAnimatable().getTargetPlayer() != null && event.getAnimatable().getTargetPlayer().isSprinting()){
            event.getController().setAnimation(SPRINT_ANIM);
        } else if (event.isMoving()){
            event.getController().setAnimation(WALK_ANIM);
        } else {
            event.getController().setAnimation(IDLE_1_ANIM);
        }

        return PlayState.CONTINUE;
    }



    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        Main.logger.info(String.format("Entity State: %s", behaviorState));
        Main.logger.info(String.format("On AI Tasks Updated Context -- Player: %s; FD %s; HD: %s; WD: %s", getTargetPlayer() == null ? "null" : getTargetPlayer(), getFollowDuration(), getHandoutDuration(), getWanderingDuration()));
    }

    public boolean isFollowingPlayer() { return followDuration > 0 && targetPlayer != null; }
    public boolean isWandering() { return wanderingDuration > 0; }
    public boolean isHandingOut() { return handoutDuration > 0; }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<EntityIgnoloxi>(this, "ignoloxi", 5, this::stateController));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void generateHandoutItem() {
        LootTable loottable = world.getLootTableManager().getLootTableFromLocation(ModLootTables.ENTITY_IGNOLOXI_HANDOUT);
        LootContext.Builder lootcontext$builder = (new LootContext.Builder((WorldServer)world)).withLootedEntity(this);
        List<ItemStack> loot = loottable.generateLootForPools(world.rand, lootcontext$builder.build());
        if (!loot.isEmpty()) {
            handoutItem = loot.get(0);
        }
    }

    public Vec3d getItemPlaceholderPosition() throws Exception {
        IgnoloxiEntityModel model = new IgnoloxiEntityModel();
        IBone itemPlaceholderBone = model.getBone("placeholderItemStack");

        return new Vec3d(itemPlaceholderBone.getPositionX(), itemPlaceholderBone.getPositionY(), itemPlaceholderBone.getPositionZ());
    }


    // Getters and Setters
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
}
