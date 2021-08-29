package com.santapexie.santapexie_bettergs.potion_and_effects;

import com.santapexie.santapexie_bettergs.ducks.HasLuminanceDuck;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class LuminanceEffect extends StatusEffect {
    protected LuminanceEffect(StatusEffectType type, int color) {
        super(type, color);
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        ((HasLuminanceDuck)entity).setSantaPexieLuminance(true);
        super.onApplied(entity, attributes, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        ((HasLuminanceDuck)entity).setSantaPexieLuminance(false);
        super.onRemoved(entity, attributes, amplifier);
    }
}