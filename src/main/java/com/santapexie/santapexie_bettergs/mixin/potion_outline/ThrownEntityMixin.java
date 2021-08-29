package com.santapexie.santapexie_bettergs.mixin.potion_outline;

import com.santapexie.santapexie_bettergs.ducks.PotionOutlineDuck;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThrownItemEntity.class)
public abstract class ThrownEntityMixin extends ThrownEntity implements PotionOutlineDuck {

    private static final TrackedData<Boolean> POTION_OUTLINE;

    @Final
    @Shadow
    private static TrackedData<ItemStack> ITEM;

    @Shadow
    protected ItemStack getItem() {
        return (ItemStack)this.getDataTracker().get(ITEM);
    }

    @Override
    public boolean getPotionOutline() {
        return dataTracker.get(POTION_OUTLINE);
    }

    protected ThrownEntityMixin(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void init(CallbackInfo callbackInfo) {
        dataTracker.startTracking(POTION_OUTLINE, false);
    }

    @Inject(method = "getStack", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/projectile/thrown/ThrownItemEntity;getItem()Lnet/minecraft/item/ItemStack;"))
    public void splashPotionOutline(CallbackInfoReturnable<ItemStack> ci) {
        if (this.getItem().getTranslationKey().endsWith("spectral_potion")) {
            this.getDataTracker().set(POTION_OUTLINE, true);
        }
    }

    static {
        POTION_OUTLINE = DataTracker.registerData(ThrownItemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}
