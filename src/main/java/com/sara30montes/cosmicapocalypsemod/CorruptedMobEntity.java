package com.sara30montes.cosmicapocalypsemod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public
class CorruptedMobEntity extends HostileEntity {
    public CorruptedMobEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
        this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
        this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
    }

    public static DefaultAttributeContainer.Builder createCorruptedMobAttributes() {
        return LivingEntity.createLivingAttributes()
        .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
    }

    public static void spawnCorruptedMob(World world, BlockPos pos) {
        CorruptedMobEntity mob = new CorruptedMobEntity(EntityType.ZOMBIE, world);
        mob.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.spawnEntity(mob);
    }
}
