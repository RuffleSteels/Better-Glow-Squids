package com.santapexie.santapexie_bettergs.potion_and_effects;

import com.santapexie.santapexie_bettergs.BetterGlowSquidsMain;
import com.santapexie.santapexie_bettergs.mixin.BrewingRecipeRegistryAccessor;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.client.render.entity.GlowSquidEntityRenderer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.awt.*;
import java.util.Optional;

public class PotionEffectRegistry {
    public static final Potion SPECTRAL_POTION = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.GLOWING, 3600)});
    public static final Potion LONG_SPECTRAL_POTION = new Potion("spectral_potion", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.GLOWING, 9600)});
    public static final StatusEffect LUMINANCE_EFFECT = new LuminanceEffect(StatusEffectType.BENEFICIAL, new Color(255, 255, 255).getRGB());
    public static final Potion GLOWING = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(PotionEffectRegistry.LUMINANCE_EFFECT, 3600)});
    public static final Potion LONG_GLOWING = new Potion("luminance_potion", new StatusEffectInstance[]{new StatusEffectInstance(PotionEffectRegistry.LUMINANCE_EFFECT, 9600)});

    private static void mapPotions(Potion in, Item ingredient, Potion result) {
        Identifier potionInId = Registry.POTION.getId(in);
        Identifier potionOutId = Registry.POTION.getId(result);
        Optional<Potion> inLong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "long_" + potionInId.getPath()));
        Optional<Potion> inStrong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "strong_" + potionInId.getPath()));
        Optional<Potion> outLong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "long_" + potionOutId.getPath()));
        Optional<Potion> outStrong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "strong_" + potionOutId.getPath()));
        if(outLong.isPresent() && inLong.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inLong.get(), ingredient, outLong.get());
        }
        if(outStrong.isPresent() && inStrong.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inStrong.get(), ingredient, outStrong.get());
        }
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(in, ingredient, result);
    }

    public static void registerAll() {
        Registry.register(Registry.POTION, new Identifier(BetterGlowSquidsMain.MOD_ID, "spectral_potion"), SPECTRAL_POTION);
        Registry.register(Registry.POTION, new Identifier(BetterGlowSquidsMain.MOD_ID, "long_spectral_potion"), LONG_SPECTRAL_POTION);
        Registry.register(Registry.POTION, new Identifier(BetterGlowSquidsMain.MOD_ID, "luminance_potion"), GLOWING);
        Registry.register(Registry.POTION, new Identifier(BetterGlowSquidsMain.MOD_ID, "long_luminance_potion"), LONG_GLOWING);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(BetterGlowSquidsMain.MOD_ID, "luminance"), LUMINANCE_EFFECT);

        mapPotions(Potions.AWKWARD, Items.GLOWSTONE_DUST, PotionEffectRegistry.SPECTRAL_POTION);
        mapPotions(PotionEffectRegistry.SPECTRAL_POTION, Items.GLOWSTONE_DUST, PotionEffectRegistry.LONG_SPECTRAL_POTION);
        mapPotions(Potions.AWKWARD, Items.GLOW_INK_SAC, PotionEffectRegistry.GLOWING);
        mapPotions(PotionEffectRegistry.GLOWING, Items.GLOWSTONE_DUST, PotionEffectRegistry.LONG_GLOWING);

    }
}
