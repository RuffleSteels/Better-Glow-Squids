package com.santapexie.santapexie_bettergs.mixin.potion_outline;

import com.santapexie.santapexie_bettergs.ducks.ArrowOutlineDuck;
import com.santapexie.santapexie_bettergs.ducks.PotionOutlineDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin  {

    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    public void mixin(Entity entity, CallbackInfoReturnable<Boolean> ci) {
            if(entity instanceof SpectralArrowEntity || entity instanceof ArrowEntity && ((ArrowOutlineDuck)entity).getArrowOutline() || entity instanceof ThrownItemEntity && ((PotionOutlineDuck)entity).getPotionOutline()) {
                ci.setReturnValue(true);
            } else {
                ci.getReturnValue();
            }
    }

}
