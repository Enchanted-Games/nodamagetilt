package games.enchanted.nodamagetilt.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(GameRenderer.class)
public class tiltViewMixin {
    MinecraftClient client;
    
    @Overwrite
    private void tiltViewWhenHurt(MatrixStack matrices, float tickDelta) {
        if (this.client.getCameraEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)this.client.getCameraEntity();
            float f = (float)livingEntity.hurtTime - tickDelta;
            float g;
            if (livingEntity.isDead()) {
                g = Math.min((float)livingEntity.deathTime + tickDelta, 20.0F);
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(40.0F - 8000.0F / (g + 200.0F)));
            }
    
            if (f < 0.0F) {
                return;
            }
  
            f /= (float)livingEntity.maxHurtTime;
            f = MathHelper.sin(f * f * f * f * 3.1415927F);
            // g = livingEntity.getDamageTiltYaw();
            g = 0.0F;
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-g));
            float h = (float)((double)(-f) * 14.0D * (Double)this.client.options.getDamageTiltStrength().getValue());
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(h));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
           
        }
  
     }
}