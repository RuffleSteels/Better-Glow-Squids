package com.santapexie.santapexie_bettergs.mixin.luminance_effect;

import com.santapexie.santapexie_bettergs.ducks.HasLuminanceDuck;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements HasLuminanceDuck{

    @Shadow protected abstract void initDataTracker();

    private static final TrackedData<Boolean> HAS_SP_LUMINANCE;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    public void trackLuminance(CallbackInfo callbackInfo) {
        this.dataTracker.startTracking(HAS_SP_LUMINANCE, false);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeLuminanceEffect(NbtCompound nbt, CallbackInfo callbackInfo) {
        nbt.putBoolean("sp_has_luminance_effect", dataTracker.get(HAS_SP_LUMINANCE));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readLuminanceEffect(NbtCompound nbt, CallbackInfo callbackInfo) {
        dataTracker.set(HAS_SP_LUMINANCE, nbt.getBoolean("sp_has_luminance_effect"));
    }

    @Override
    public void setSantaPexieLuminance(boolean sp_luminance_effect) {
        dataTracker.set(HAS_SP_LUMINANCE, sp_luminance_effect);
    }

    @Override
    public boolean isSantaPexieLuminance() {
        return dataTracker.get(HAS_SP_LUMINANCE);
    }

    static {
        HAS_SP_LUMINANCE = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}
