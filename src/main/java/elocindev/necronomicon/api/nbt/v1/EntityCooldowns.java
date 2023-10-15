package elocindev.necronomicon.api.nbt.v1;

//#if FABRIC==1
import net.minecraft.entity.LivingEntity;
//#else
//$$ import net.minecraft.world.entity.LivingEntity;
//#endif


public class EntityCooldowns {
    
    /**
     * Adds a cooldown value to the entity.
     * 
     * @param entity        The entity to add the cooldown to
     * @param key           Key/identifier for the cooldown
     * @param duration      Duration of the cooldown in ticks
     * 
     * @platform            Both
     * 
     * @author ElocinDev
     * @since 1.0.8
     */
    public static void setCooldown(LivingEntity entity, String key, int duration) {
        NecNbtAPI.Entity.putLong(entity, key, 
            //#if FABRIC==1
                entity.getWorld().getTime()
            //#else
            //$$ entity.getLevel().getGameTime()
            //#endif
                + duration
        );
    }
}
