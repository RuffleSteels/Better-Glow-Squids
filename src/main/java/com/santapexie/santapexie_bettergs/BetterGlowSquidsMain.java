package com.santapexie.santapexie_bettergs;

import com.santapexie.santapexie_bettergs.potion_and_effects.PotionEffectRegistry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.tag.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BetterGlowSquidsMain implements ModInitializer {
    public static final String MOD_ID = "santapexie_bettergs";
    public static final int SP_LUMINANCE_EFFECT_FLAG_INDEX = 8;

    @Override
    public void onInitialize() {
        PotionEffectRegistry.registerAll();
    }

}
