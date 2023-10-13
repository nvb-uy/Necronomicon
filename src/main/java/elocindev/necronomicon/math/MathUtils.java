package elocindev.necronomicon.math;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathUtils {
    public static Vec3d getLookingVec(LivingEntity entity) {
        float yaw = entity.getYaw(); float pitch = entity.getPitch();
        
        float radian = 0.017453292F;
        float x = -MathHelper.sin(yaw * radian) * MathHelper.cos(pitch * radian);
        float y = -MathHelper.sin(pitch * radian);
        float z = MathHelper.cos(yaw * radian) * MathHelper.cos(pitch * radian);
        
        float m = MathHelper.sqrt(x * x + y * y + z * z);

        return new Vec3d(x/m, y/m, z/m);
    }
}
