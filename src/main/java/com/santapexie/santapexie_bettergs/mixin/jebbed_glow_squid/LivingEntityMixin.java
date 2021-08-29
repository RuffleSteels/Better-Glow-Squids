package com.santapexie.santapexie_bettergs.mixin.jebbed_glow_squid;

import com.santapexie.santapexie_bettergs.ducks.GlowSquidHueDuck;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements GlowSquidHueDuck {
    private static final TrackedData<Float> GLOW_SQUID_HUE;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void init(CallbackInfo callbackInfo) {
        dataTracker.startTracking(GLOW_SQUID_HUE, 0.0F);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void hueAddonWrite(NbtCompound nbt, CallbackInfo ci) {
        nbt.putFloat("santapexie_bettergs:glow_squid_hue", this.getGlowSquidHue());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void hueAddonRead(NbtCompound nbt, CallbackInfo ci) {
        setGlowSquidHue(nbt.getFloat("santapexie_bettergs:glow_squid_hue"));
    }

    static {
        GLOW_SQUID_HUE = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);
    }

    @Override
    public float getGlowSquidHue() {
        return dataTracker.get(GLOW_SQUID_HUE);
    }

    @Override
    public void setGlowSquidHue(float glowSquidHue) {
        dataTracker.set(GLOW_SQUID_HUE, glowSquidHue);
    }
}
