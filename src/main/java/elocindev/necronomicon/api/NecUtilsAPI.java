package elocindev.necronomicon.api;

import elocindev.necronomicon.math.MathUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * A class containing a series of general utilities.
 * 
 * @platform    Forge, Fabric
 * @side        Client, Server
 * @minecraft   >= 1.17
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
     * @platform        Forge, Fabric
     * @minecraft       >= 1.17
     * 
     * @author         ElocinDev
     */
    public static Vec3 getLookVec(LivingEntity entity) {
        return MathUtils.getLookingVec(entity);
    }

    /**
     * Gets the id of an entity.
     * 
     * @param entity    The entity to get the id from.
     * @return          [String] The id of the entity as string (ex. minecraft:pig)
     * 
     * @platform        Forge, Fabric
     * @minecraft       >= 1.17
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
     * @platform        Forge, Fabric
     * @minecraft       >= 1.17
     * 
     * @see             #getEntityId(Entity)
     * 
     * @author ElocinDev
     */
    public static ResourceLocation getEntityIdentifier(Entity entity) { 
        return EntityType.getKey(entity.getType());
    }

    /**
     * Gets the world's game time of an entity.
     * 
     * @platform        Forge, Fabric
     * @minecraft       >= 1.17
     * 
     * @param entity    The entity to get the world time from.
     * @return          [long] The time in ticks of the entity's world.
     */
    public static long getWorldTime(LivingEntity entity) {
        //? if >=1.20.1 {
        
        return entity.level().getGameTime();
        //?} else {
        /*return entity.getLevel().getGameTime();
        *///?}
    }

    /**
     * Gets the world's game time.
     * 
     * @platform        Forge, Fabric
     * @minecraft       >= 1.17
     * 
     * @param world     The world/level to get the world time from.
     * @return          [long] The time in ticks of the entity's world.
     */
    public static long getWorldTime(Level world) { 
        return world.getGameTime();

    }

    /**
     * Returns true if a mod is loaded.
     * 
     * @platform        Forge, Fabric
     * @minecraft       >= 1.17
     * 
     * @param modid     The Mod's ID
     * @return          true if the mod is loaded, false otherwise.
     */
    public static boolean isModLoaded(String modid) {
        return
            //? if fabric {
            net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded(modid);
            //?} elif forge {
            
            /*net.minecraftforge.fml.loading.FMLLoader.getLoadingModList().getModFileById(modid) != null;
             
            *///?} elif neoforge {
            
            /*net.neoforged.fml.loading.FMLLoader.getLoadingModList().getModFileById(modid) != null;
             
            *///?}
    }
}
