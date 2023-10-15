package elocindev.necronomicon.api;

import elocindev.necronomicon.math.MathUtils;
//#if FABRIC==1
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
//#else
//$$ import net.minecraft.world.entity.Entity;
//$$ import net.minecraft.world.entity.EntityType;
//$$ import net.minecraft.world.entity.LivingEntity;
//$$ import net.minecraft.resources.ResourceLocation;
//$$ import net.minecraft.world.phys.Vec3;
//$$ import net.minecraft.world.level.Level;
//#endif
import net.minecraft.world.World;

/**
 * A class containing a series of general utilities.
 * 
 * @platform    Forge, Fabric
 * @side        Client, Server
 * 
 * @author      ElocinDev
 * @since       1.0.0
 */
public class NecUtilsAPI {
    /**
     * Gets the movement vector of a LivingEntity's looking direction.
     * Useful for throwing projectiles or dashes.
     * 
     * @param entity   The entity to get the vector from.
     *
     * @see            MathUtils#getLookingVec
     * @return         [Vec3d | Vec3] representing the entity's looking direction.
     * 
     * @author         ElocinDev
     */
    //#if FABRIC==1
    public static Vec3d getLookVec(LivingEntity entity) {
    //#else
    //$$ public static Vec3 getLookVec(LivingEntity entity) {
    //#endif

        return MathUtils.getLookingVec(entity);
    }

    /**
     * Gets the id of an entity.
     * 
     * @param entity    The entity to get the id from.
     * @return          [String] The id of the entity as string (ex. minecraft:pig)
     * 
     * @see             #getEntityIdentifier(Entity)
     * 
     * @author ElocinDev
     */
    public static String getEntityId(Entity entity) {
        return getEntityIdentifier(entity).toString();
    }

    /**
     * Gets the id of an entity.
     * 
     * @param entity    The entity to get the id from.
     * @return          [Identifier | ResourceLocation] The id of the entity.
     * 
     * @see             #getEntityId(Entity)
     * 
     * @author ElocinDev
     */
    //#if FABRIC==1
    public static Identifier getEntityIdentifier(Entity entity) { 
        return EntityType.getId(entity.getType());
    //#else
    //$$ public static ResourceLocation getEntityIdentifier(Entity entity) {
    //$$     return EntityType.getKey(entity.getType());
    //#endif
    }

    /**
     * Gets the world time of an entity.
     * 
     * @platform        Forge, Fabric
     * 
     * @param entity    The entity to get the world time from.
     * @return          [long] The time of the entity's world.
     */
    public static long getWorldTime(LivingEntity entity) {
        return 
            //#if FABRIC==1
            entity.getWorld().getTime();
            //#else
            //$$ entity.getLevel().getGameTime();
            //#endif;
    }

    public static long getWorldTime(
        //#if FABRIC==1
        World world
        //#else
        //$$ Level world
        //#endif
    ) { 
        return 
            //#if FABRIC==1
            world.getTime();
            //#else
            //$$ world.getGameTime();
            //#endif
    }
}
