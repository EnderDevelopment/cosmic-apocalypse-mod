package com.sara30montes.cosmicapocalypsemod;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public
class ApocalypseWorld {
    public static void startApocalypse(World world) {
        if (world instanceof ServerWorld serverWorld) {
            // Set red-tinted sky
            serverWorld.setSkyColor(0.5F, 0.1F, 0.1F);

            // Increase fog density
            serverWorld.setFogDensity(0.1F);

            // Schedule random lightning strikes
            serverWorld.getServer().getTickManager().runTaskTimer(() -> {
                BlockPos pos = serverWorld.getRandomPosInChunk(serverWorld.getRandom().nextInt(16), 0, serverWorld.getRandom().nextInt(16), 15);
                serverWorld.getServer().getPlayerManager().getPlayerList().forEach(player -> {
                    player.sendMessage(Text.literal("A cosmic lightning strike has occurred!").formatted(Formatting.RED));
                });
                serverWorld.getServer().getPlayerManager().getPlayerList().forEach(player -> {
                    player.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0F, 1.0F);
                });
            }, 200, 200);
        }
    }
}
