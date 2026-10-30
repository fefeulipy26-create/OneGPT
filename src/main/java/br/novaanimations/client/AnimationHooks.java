
package br.novaanimations.client;

import br.novaanimations.config.ModConfig;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public final class AnimationHooks {

    private AnimationHooks() {
    }

    public static void apply(
        ModelBiped model,
        Entity entity
    ) {

        if (!ModConfig.get("walking_1_11")) {
            return;
        }

        if (!(entity instanceof EntityPlayer)) {
            return;
        }

        if (!entity.onGround || entity.isRiding()) {
            return;
        }

        double dx = entity.posX - entity.prevPosX;
        double dz = entity.posZ - entity.prevPosZ;

        double radians = Math.toRadians(entity.rotationYaw);

        double forwardX = -Math.sin(radians);
        double forwardZ = Math.cos(radians);

        double direction =
            dx * forwardX + dz * forwardZ;

        if (direction < -0.0001D) {

            model.bipedRightLeg.rotateAngleX *= -1.0F;
            model.bipedLeftLeg.rotateAngleX *= -1.0F;

            model.bipedRightArm.rotateAngleX *= -1.0F;
            model.bipedLeftArm.rotateAngleX *= -1.0F;
        }
    }
}
