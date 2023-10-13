package elocindev.necronomicon.api;

import elocindev.necronomicon.math.MathUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

/**
 * A class containing a series of general utilities.
 * 
 * @author  ElocinDev
 * @since   1.0.0
 */
public class NecUtilsAPI {
    /**
     * Gets the movement vector of a LivingEntity's looking direction.
     * Useful for throwing projectiles or dashes.
     * 
     * @param entity   The entity to get the vector from.
     *
     * @see            MathUtils#getLookingVec
     * @return         Vec3d representing the entity's looking direction.
     * 
     * @author         ElocinDev
     */
    public static Vec3d getLookVec(LivingEntity entity) {
        return MathUtils.getLookingVec(entity);
    }
}
