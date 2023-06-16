package games.enchanted.nodamagetilt;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(GameRenderer.class)
public class tiltViewMixin {
    @Redirect(
            method = "tiltViewWhenHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;getDamageTiltYaw()F"
            )
    )
    private float injected(LivingEntity instance) {
        return 0.0F;
    }
}