package com.santapexie.santapexie_bettergs.mixin.potion_outline;

import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

import java.awt.*;

@Mixin(StatusEffects.class)
public class StatusEffectsMixin {
    @SuppressWarnings("UnresolvedMixinReference")
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=glowing")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffect;<init>(Lnet/minecraft/entity/effect/StatusEffectType;I)V", ordinal = 0)
    )
    private static int modifyGlowingColor(int original) {
        return new Color(255, 253, 0).getRGB();
    }
}
