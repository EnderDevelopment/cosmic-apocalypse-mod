package com.sara30montes.cosmicapocalypsemod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public
class ApocalypseHUD {
    private static final Identifier HUD_TEXTURE = new Identifier(MainMod.MOD_ID, "textures/gui/apocalypse_hud.png");
    private static boolean isApocalypseActive = false;
    private static BlockPos apocalypseCenter;

    public static void startApocalypseHUD(ServerPlayerEntity player, BlockPos center) {
        isApocalypseActive = true;
        apocalypseCenter = center;
    }

    public static void renderApocalypseHUD(DrawContext context, float tickDelta) {
        if (isApocalypseActive) {
            MatrixStack matrices = context.getMatrices();
            int width = context.getScaledWindowWidth();
            int height = context.getScaledWindowHeight();

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, HUD_TEXTURE);

            // Draw full-screen HUD
            context.drawTexture(HUD_TEXTURE, 0, 0, 0, 0, width, height, width, height);

            // Draw additional HUD elements based on player position
            double distance = apocalypseCenter.getSquaredDistance(MinecraftClient.getInstance().player.getPos());
            if (distance < 100) {
                // Draw warning message
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, "WARNING: Cosmic Apocalypse", width / 2 - 50, height / 2, 0xFFFF0000);
            }
        }
    }
}
