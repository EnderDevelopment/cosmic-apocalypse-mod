package com.sara30montes.cosmicapocalypsemod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import static net.minecraft.server.command.CommandManager.literal;

public
class ApocalypseCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("apocalypse")
        .then(literal("start").executes(ApocalypseCommand::startApocalypse)));
    }

    private static int startApocalypse(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player != null) {
            ApocalypseEvent.startApocalypse(player.getServer());
            player.sendMessage(Text.literal("The cosmic apocalypse has begun!").formatted(Formatting.RED));
            return 1;
        }
        return 0;
    }
}
