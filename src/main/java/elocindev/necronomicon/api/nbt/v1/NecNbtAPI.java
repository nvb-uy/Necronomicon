package elocindev.necronomicon.api.nbt.v1;

import java.util.UUID;

import elocindev.necronomicon.api.NecUtilsAPI;
//#if FABRIC==1
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
//#else
//$$ import net.minecraft.world.entity.LivingEntity;
//$$ import net.minecraft.nbt.CompoundTag;
//#endif

/**
 * A NBT Utility API for both Forge and Fabric.
 * 
 * Includes easy to access unified methods for both loaders and a cooldown API.
 * @see EntityCooldowns
 * 
 * @platform       Forge, Fabric
 * 
 * @since 1.0.8
 * @author ElocinDev
 */
public class NecNbtAPI {

    /**
     * Cooldown utility API.
     * 
     * @since 1.0.8
     * @author ElocinDev
     */
    abstract class Cooldown {
        /**
         * Adds a cooldown value to the entity.
         * 
         * @param entity        The entity to add the cooldown to
         * @param key           Key/identifier for the cooldown
         * @param duration      Duration of the cooldown in ticks
         * 
         * @platform            Forge, Fabric
         * 
         * @author ElocinDev
         * @since 1.0.8
         */
        public static void setCooldown(LivingEntity entity, String key, long duration) {
            NecNbtAPI.Entity.putLong(entity, key, 
                //#if FABRIC==1
                    entity.getWorld().getTime()
                //#else
                //$$ entity.getLevel().getGameTime()
                //#endif
                    + duration
            );
        }

        /**
         * Gets the remaining cooldown in ticks for the entity.
         * 
         * @param entity    The entity to get the cooldown from
         * @param key       Key/identifier for the cooldown
         * @return          [long] remaining ticks, if not on cooldown, returns 0.
         */
        public static long getRemainingCooldown(LivingEntity entity, String key) {
            long r = NecNbtAPI.Entity.getLong(entity, key) - NecUtilsAPI.getWorldTime(entity);
            return r < 0 ? 0 : r;
        }

        /**
         * Checks if the entity is on cooldown.
         * 
         * @param entity    The entity to check the cooldown for
         * @param key       Key/identifier for the cooldown
         * @return          [boolean] true if on cooldown, false if not
         */
        public static boolean isOnCooldown(LivingEntity entity, String key) {
            return getRemainingCooldown(entity, key) > 0;
        }
    }

    /**
     * Entity NBT API. Unifies NBT methods for both Forge and Fabric.
     * 
     * @platform       Forge, Fabric
     * 
     * @since 1.0.8
     * @author ElocinDev
     */
    abstract class Entity {
        /**
         * Gets the NBT compound of the entity.
         * 
         * @param entity    The entity to get the NBt from
         * 
         * @return          [Fabric: NbtCompound, Forge: CompoundTag] The NBT compound of the entity
         * 
         * @platform        Forge, Fabric
         * 
         * @since 1.0.8
         * @author ElocinDev
         */
        //#if FABRIC==1
        public static NbtCompound 
        //#else
        //$$ public static CompoundTag
        //#endif
        getNbt(LivingEntity entity) {
            //#if FABRIC==1
            return entity.writeNbt(new NbtCompound());
            //#else
            //$$ return entity.saveWithoutId(new CompoundTag());
            //#endif
        }

        /**
         * Saves a NBT compound to the entity.
         * 
         * @param entity    The entity to save the NBT to
         * @param tag       [Fabric: NbtCompound, Forge: CompoundTag] The NBT compound to save to the entity
         * 
         * @platform        Forge, Fabric
         * 
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void saveNbt(LivingEntity entity, 
            //#if FABRIC==1
            NbtCompound tag
            //#else
            //$$ CompoundTag tag
            //#endif
        ) {
            //#if FABRIC==1
            entity.saveNbt(tag);
            //#else
            //$$ entity.save(tag);
            //#endif
        }

        // ENTITY UTILITIES

        public static void putLong(LivingEntity entity, String key, long value) {
            var tag = getNbt(entity);

            tag.putLong(key, value);

            //#if FABRIC==1
            entity.saveNbt(tag);
            //#else
            //$$ entity.save(tag);
            //#endif   
        }

        /**
         * Puts an integer value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putInt(LivingEntity entity, String key, int value) {
            var tag = getNbt(entity);

            tag.putInt(key, value);

            saveNbt(entity, tag); 
        }

        /**
         * Puts a boolean value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putBoolean(LivingEntity entity, String key, boolean value) {
            var tag = getNbt(entity);

            tag.putBoolean(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a String value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putString(LivingEntity entity, String key, String value) {
            var tag = getNbt(entity);

            tag.putString(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a float value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putFloat(LivingEntity entity, String key, float value) {
            var tag = getNbt(entity);

            tag.putFloat(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a double value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putDouble(LivingEntity entity, String key, double value) {
            var tag = getNbt(entity);

            tag.putDouble(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a byte value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putByte(LivingEntity entity, String key, byte value) {
            var tag = getNbt(entity);

            tag.putByte(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a short value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putShort(LivingEntity entity, String key, short value) {
            var tag = getNbt(entity);

            tag.putShort(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a UUID value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putUuid(LivingEntity entity, String key, java.util.UUID value) {
            var tag = getNbt(entity);

            tag.putUuid(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a byte[] value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putByteArray(LivingEntity entity, String key, byte[] value) {
            var tag = getNbt(entity);

            tag.putByteArray(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a int[] value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putIntArray(LivingEntity entity, String key, int[] value) {
            var tag = getNbt(entity);

            tag.putIntArray(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a long[] value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putLongArray(LivingEntity entity, String key, long[] value) {
            var tag = getNbt(entity);

            tag.putLongArray(key, value);

            saveNbt(entity, tag);   
        }

        /**
         * Puts a NbtCompound/CompoundTag value into the entity's NBT.
         * 
         * @param entity    The entity to put the value into
         * @param key       The key/identifier for the value
         * @param value     The value to put into the entity's NBT
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static void putCompound(LivingEntity entity, String key, 
            //#if FABRIC==1
            NbtCompound value
            //#else
            //$$ CompoundTag value
            //#endif
        ) {
            var tag = getNbt(entity);

            tag.put(key, value);

            saveNbt(entity, tag);   
        }

        // Getters

        /**
         * Gets a long value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The long value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static long getLong(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getLong(key);
        }

        /**
         * Gets a int value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The int value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static int getInt(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getInt(key);
        }

        /**
         * Gets a boolean value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The boolean value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static boolean getBoolean(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getBoolean(key);
        }

        /**
         * Gets a String value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The String value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static String getString(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getString(key);
        }

        /**
         * Gets a float value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The float value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static float getFloat(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getFloat(key);
        }

        /**
         * Gets a double value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The double value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static double getDouble(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getDouble(key);
        }

        /**
         * Gets a byte value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The byte value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static byte getByte(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getByte(key);
        }

        /**
         * Gets a short value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The short value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static short getShort(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getShort(key);
        }

        /**
         * Gets a UUID value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The UUID value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static UUID getUuid(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getUuid(key);
        } 

        /**
         * Gets a byte[] value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The byte[] value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static byte[] getByteArray(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getByteArray(key);
        }

        /**
         * Gets a int[] value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The int[] value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static int[] getIntArray(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getIntArray(key);
        }

        /**
         * Gets a long[] value from the entity's NBT.
         * 
         * @param entity    The entity to get the value from
         * @param key       The key/identifier for the value
         * 
         * @returns         The long[] value from provided key
         * 
         * @platform        Forge, Fabric
         * @since 1.0.8
         * @author ElocinDev
         */
        public static long[] getLongArray(LivingEntity entity, String key) {
            var tag = getNbt(entity);

            return tag.getLongArray(key);
        }
    }
}
