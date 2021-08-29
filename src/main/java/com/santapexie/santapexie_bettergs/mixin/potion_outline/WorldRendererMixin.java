package com.santapexie.santapexie_bettergs.mixin.potion_outline;

import com.santapexie.santapexie_bettergs.ducks.ArrowOutlineDuck;
import com.santapexie.santapexie_bettergs.ducks.PotionOutlineDuck;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow
    @Final
    private BufferBuilderStorage bufferBuilders;

    @Unique
    private Entity entity;

    @Unique
    private Direction direction3;

    @Unique
    private Direction direction2;

    @Unique
    private BufferBuilder bufferBuilder;

    @ModifyVariable(method = "render", at = @At("STORE"))
    private Entity entityVariable(Entity entity) {
        return this.entity = entity;
    }

    @ModifyVariable(method = "renderChunkDebugInfo", at = @At("STORE"))
    private Direction direction3Variable(Direction direction3) {return this.direction3 = direction3; }

    @ModifyVariable(method = "renderChunkDebugInfo", at = @At("STORE"))
    private Direction direction2Variable(Direction direction2) {return this.direction2 = direction2; }

    @ModifyVariable(method = "renderChunkDebugInfo", at = @At("STORE"))
    private BufferBuilder bufferBuilderVariable(BufferBuilder bufferBuilder) {return this.bufferBuilder = bufferBuilder; }


    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/OutlineVertexConsumerProvider;setColor(IIII)V", shift = At.Shift.AFTER))
    private void renderMixin(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo callbackInfo) {
            if (this.entity instanceof SpectralArrowEntity || this.entity instanceof ArrowEntity && ((ArrowOutlineDuck) this.entity).getArrowOutline() || entity instanceof ThrownItemEntity && ((PotionOutlineDuck)entity).getPotionOutline()) {
                this.bufferBuilders.getOutlineVertexConsumers().setColor(255, 255, 0, 255);

            }
    }
    @Inject(method = "renderChunkDebugInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Direction;getOffsetZ()I", shift = At.Shift.AFTER))
    private void changeGlowingOutlineColor(Camera camera, CallbackInfo ci) {
        bufferBuilder.vertex((double)(8 + 8 * direction2.getOffsetX()), (double)(8 + 8 * direction2.getOffsetY()), (double)(8 + 8 * direction2.getOffsetZ())).color(242, 202, 0, 1).next();
        bufferBuilder.vertex((double)(8 + 8 * direction3.getOffsetX()), (double)(8 + 8 * direction3.getOffsetY()), (double)(8 + 8 * direction3.getOffsetZ())).color(242, 202, 0, 1).next();
    }
}
