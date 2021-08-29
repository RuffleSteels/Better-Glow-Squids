package com.santapexie.santapexie_bettergs.mixin.jebbed_glow_squid;

import com.santapexie.santapexie_bettergs.ducks.GlowSquidHueDuck;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.GlowSquidEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.awt.*;

@SuppressWarnings("target")
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements FeatureRendererContext<T, M> {
    @Shadow protected M model;

    private float saturation = 1.0F;
    private float brightness = 0.5F;
    private Color color = Color.BLACK;

    protected LivingEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"))
    public void jebGlowSquid(Args args, T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity instanceof GlowSquidEntity && livingEntity.hasCustomName() && "jeb_".equals(livingEntity.getName().asString())) {


            ((GlowSquidHueDuck) livingEntity).setGlowSquidHue((((GlowSquidHueDuck) livingEntity).getGlowSquidHue() + 0.005F) % 1.0F);
            color = new Color(Color.HSBtoRGB(((GlowSquidHueDuck) livingEntity).getGlowSquidHue(), saturation, brightness));
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            args.set(4, (float)red / 255F);
            args.set(5, (float)green / 255F);
            args.set(6, (float)blue / 255F);

            /**
            float hue;
            hue = (livingEntity.age % 100) / 99F;
            color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            args.set(4, (float)red / 255F);
            args.set(5, (float)green / 255F);
            args.set(6, (float)blue / 255F);
             **/
        }
    }
}
