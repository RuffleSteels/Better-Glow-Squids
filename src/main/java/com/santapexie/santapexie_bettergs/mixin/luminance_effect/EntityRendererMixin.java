package com.santapexie.santapexie_bettergs.mixin.luminance_effect;


import com.santapexie.santapexie_bettergs.ducks.HasLuminanceDuck;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

    @Shadow protected abstract int getBlockLight(T entity, BlockPos pos);

    @Inject(method = "getBlockLight", at = @At("TAIL"), cancellable = true)
    public void glowingEffectLighting(T entity, BlockPos pos, CallbackInfoReturnable<Integer> cir){
        if(entity instanceof LivingEntity livingEntity) {
            if(((HasLuminanceDuck)livingEntity).isSantaPexieLuminance()) {
                cir.setReturnValue(15);
            }
        }
    }
}
