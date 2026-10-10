package com.sara30montes.cosmicapocalypsemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public
class MainMod implements ModInitializer {
    public static final String MOD_ID = "cosmicapocalypsemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Identifier APOCALYPSE_CHANNEL = new Identifier(MOD_ID, "apocalypse");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Cosmic Apocalypse Mod");

        // Register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ApocalypseCommand.register(dispatcher);
        });

        // Register network channel
        ServerPlayNetworking.registerGlobalReceiver(APOCALYPSE_CHANNEL, ApocalypseEvent::handleApocalypsePacket);

        // Register events
        ServerLifecycleEvents.SERVER_STARTED.register(ApocalypseEvent::onServerStart);
        ServerLifecycleEvents.SERVER_STOPPING.register(ApocalypseEvent::onServerStop);
    }
}
