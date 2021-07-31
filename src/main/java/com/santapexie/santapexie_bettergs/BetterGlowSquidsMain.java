package com.santapexie.santapexie_bettergs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistry;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;

public class BetterGlowSquidsMain implements ModInitializer {
    public static final String MOD_ID = "santapexie_bettergs";

    @Override
    public void onInitialize() {
        PotionRegistry.registerAll();
    }
}
