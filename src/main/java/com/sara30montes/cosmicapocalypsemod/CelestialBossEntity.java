package com.sara30montes.cosmicapocalypsemod;

import net.minecraft.entity.EntityType;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public
class CelestialBossEntity extends HostileEntity {
    public CelestialBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createCelestialBossAttributes() {
        return LivingEntity.createLivingAttributes()
        .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D)
        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0D)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
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

    public static void spawnBoss(World world, BlockPos pos) {
        CelestialBossEntity boss = new CelestialBossEntity(EntityType.ZOMBIE, world);
        boss.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.spawnEntity(boss);
    }
}
