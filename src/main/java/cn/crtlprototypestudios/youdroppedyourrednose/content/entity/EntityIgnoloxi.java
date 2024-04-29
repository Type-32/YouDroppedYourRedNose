package cn.crtlprototypestudios.youdroppedyourrednose.content.entity;

import cn.crtlprototypestudios.youdroppedyourrednose.content.ModContent;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIFollowPlayer;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIHandoutItem;
import cn.crtlprototypestudios.youdroppedyourrednose.content.entity.ai.EntityAIWandering;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModLootTables;
import cn.crtlprototypestudios.youdroppedyourrednose.content.ModSounds;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class EntityIgnoloxi extends EntityCreature implements IAnimatable {

    public static final String ENTITY_REGISTRY_NAME = "ignoloxi";
    public static final int ENTITY_REGISTRY_ID = 120;

    protected static final AnimationBuilder IDLE_1_ANIM = new AnimationBuilder().addAnimation("state.idle_1", true);
    protected static final AnimationBuilder IDLE_2_ANIM = new AnimationBuilder().addAnimation("state.idle_2", true);
    protected static final AnimationBuilder WALK_ANIM = new AnimationBuilder().addAnimation("state.walking", true);
    protected static final AnimationBuilder SPRINT_ANIM = new AnimationBuilder().addAnimation("state.sprinting", true);
    protected static final AnimationBuilder HANDOUT_START_ANIM = new AnimationBuilder().addAnimation("state.handout", true);
    protected static final AnimationBuilder HANDOUT_IDLE_ANIM = new AnimationBuilder().addAnimation("state.handout_idle", true);
    protected static final AnimationBuilder HANDOUT_END_ANIM = new AnimationBuilder().addAnimation("state.handout_end", true);
    protected static final AnimationBuilder HANDOUT_TAKEN_ANIM = new AnimationBuilder().addAnimation("state.handout_taken", true);
    private final AnimationFactory factory = new AnimationFactory(this);

    public static final int FOLLOW_DURATION_MIN = 20 * 20; // 20 seconds
    public static final int FOLLOW_DURATION_MAX = 60 * 20; // 1 minute
    public static final int HANDOUT_DURATION_MIN = 8 * 20; // 8 seconds
    public static final int HANDOUT_DURATION_MAX = 14 * 20; // 14 seconds
    public static final int WANDERING_DURATION = 60 * 20; // 1 minute
    public static final double FOLLOW_SPEED = 0.15D;
    public static final double FOLLOW_DISTANCE = 3.0D;

    private EntityPlayer targetPlayer;
    private int followDuration;
    private int handoutDuration;
    private int wanderingDuration;
    private ItemStack handoutItem;
    private final Random rand = new Random();

    public EntityIgnoloxi(World worldIn) {
        // Make sure this class has no usages; The entity'll be initialized not by creating a physical object in the ModContent class, but via Implicit Registry in the EntityHandler class
        super(worldIn);
        ModContent.ENTITIES.add(this);
        this.setCustomNameTag("Ignoloxi");
        this.setAlwaysRenderNameTag(true);
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
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
        if (!handoutItem.isEmpty() && player.getHeldItem(hand).isEmpty()) {
            player.setHeldItem(hand, handoutItem);
            handoutItem = ItemStack.EMPTY;
            handoutDuration = 0;
            wanderingDuration = WANDERING_DURATION;
            return true;
        }
        return super.processInteract(player, hand);
    }

    protected <E extends EntityIgnoloxi> PlayState stateController(final AnimationEvent<E> event) {
        if(rand.nextBoolean())
            event.getController().setAnimation(IDLE_1_ANIM);
        else
            event.getController().setAnimation(IDLE_2_ANIM);

        if (handoutDuration > 0) {
            event.getController().setAnimation(HANDOUT_IDLE_ANIM);
        } else if (this.isSprinting()){
            event.getController().setAnimation(SPRINT_ANIM);
        } else if (event.isMoving()){
            event.getController().setAnimation(WALK_ANIM);
        }

        return PlayState.CONTINUE;
//        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "ignoloxi", 5, this::stateController));
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
