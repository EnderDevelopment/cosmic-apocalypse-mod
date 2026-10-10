package com.sara30montes.cosmicapocalypsemod;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public
class ApocalypseSounds {
    public static final SoundEvent APOCALYPSE_AMBIENT = registerSound("apocalypse_ambient");
    public static final SoundEvent APOCALYPSE_BOSS_ROAR = registerSound("apocalypse_boss_roar");

    private static SoundEvent registerSound(String name) {
        Identifier id = new Identifier(MainMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        // Sounds are registered during the registration process
    }
}
