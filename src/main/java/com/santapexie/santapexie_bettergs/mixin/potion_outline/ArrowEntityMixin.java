package com.santapexie.santapexie_bettergs.mixin.potion_outline;

import com.santapexie.santapexie_bettergs.ducks.ArrowOutlineDuck;
import com.santapexie.santapexie_bettergs.potion_and_effects.PotionEffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends Entity implements ArrowOutlineDuck {

    @Shadow private Potion potion;

    private static final TrackedData<Boolean> ARROW_OUTLINE;

    @Override
    public boolean getArrowOutline() {
        return dataTracker.get(ARROW_OUTLINE);
    }

    public ArrowEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void init(CallbackInfo callbackInfo) {
        dataTracker.startTracking(ARROW_OUTLINE, false);
    }

    @Inject(method = "tick", at = @At("INVOKE"))
    public void tickMixin(CallbackInfo callbackInfo) {
        if(this.potion == PotionEffectRegistry.SPECTRAL_POTION || this.potion == PotionEffectRegistry.LONG_SPECTRAL_POTION) {
            this.getDataTracker().set(ARROW_OUTLINE, true);
        }
    }

    static {
        ARROW_OUTLINE = DataTracker.registerData(ArrowEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}
