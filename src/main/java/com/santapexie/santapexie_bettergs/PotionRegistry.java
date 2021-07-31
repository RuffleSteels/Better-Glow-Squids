package com.santapexie.santapexie_bettergs;

import com.santapexie.santapexie_bettergs.mixin.BrewingRecipeRegistryAccessor;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class PotionRegistry {
    public static final Potion GLOWING = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.GLOWING, 3600)});
    public static final Potion LONG_GLOWING = new Potion("glowing", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.GLOWING, 9600)});

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
        Registry.register(Registry.POTION, new Identifier(BetterGlowSquidsMain.MOD_ID, "glowing"), GLOWING);
        Registry.register(Registry.POTION, new Identifier(BetterGlowSquidsMain.MOD_ID, "long_glowing"), LONG_GLOWING);
        mapPotions(Potions.AWKWARD, Items.GLOW_INK_SAC, PotionRegistry.GLOWING);
        mapPotions(PotionRegistry.GLOWING, Items.GLOWSTONE_DUST, PotionRegistry.LONG_GLOWING);
    }


}
