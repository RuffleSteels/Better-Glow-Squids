package com.santapexie.santapexie_bettergs.mixin.jebbed_glow_squid;

import com.santapexie.santapexie_bettergs.BetterGlowSquidsMain;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GlowSquidEntityRenderer;
import net.minecraft.client.render.entity.SquidEntityRenderer;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowSquidEntityRenderer.class)
public class GlowSquidEntityRendererMixin{
    private static final Identifier JEBBED_TEXTURE = new Identifier(BetterGlowSquidsMain.MOD_ID, "textures/entity/squid/glow_squid.png");

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void setJebbedTexture(GlowSquidEntity glowSquidEntity, CallbackInfoReturnable<Identifier> cir) {
        if(glowSquidEntity.hasCustomName() && "jeb_".equals(glowSquidEntity.getName().asString())) {
            cir.setReturnValue(JEBBED_TEXTURE);
        } else { cir.getReturnValue(); }
    }
}
