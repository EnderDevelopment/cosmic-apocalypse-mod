package com.sara30montes.cosmicapocalypsemod;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public
class ApocalypseEvent {
    public static void startApocalypse(MinecraftServer server) {
        World world = server.getOverworld();
        BlockPos spawnPos = world.getSpawnPos();

        // Send apocalypse packet to all players
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(spawnPos);
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ServerPlayNetworking.send(player, MainMod.APOCALYPSE_CHANNEL, buf);
        }

        // Start world changes
        ApocalypseWorld.startApocalypse(world);

        // Spawn celestial boss
        CelestialBossEntity.spawnBoss(world, spawnPos);
    }

    public static void handleApocalypsePacket(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf) {
        BlockPos spawnPos = buf.readBlockPos();
        ApocalypseHUD.startApocalypseHUD(player, spawnPos);
    }

    public static void onServerStart(MinecraftServer server) {
        MainMod.LOGGER.info("Cosmic Apocalypse Mod events initialized");
    }

    public static void onServerStop(MinecraftServer server) {
        MainMod.LOGGER.info("Cosmic Apocalypse Mod events stopped");
    }
}
