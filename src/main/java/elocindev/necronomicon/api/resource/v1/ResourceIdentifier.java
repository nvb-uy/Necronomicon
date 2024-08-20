package elocindev.necronomicon.api.resource.v1;

/**
 * Wrapper class that returns a ResourceLocation object based on the Minecraft version.
 * 
 * Thanks Mojang for completely changing the way ResourceLocations are created in 1.21!
 * 
 * Also named ResourceIdentifier because both Yarn and Mojmap are based.
 * 
 * @platform    Both
 * @side        Both
 * @minecraft   *
 * @since       1.5.0
 * 
 */
public class ResourceIdentifier {
    public static net.minecraft.resources.ResourceLocation of(String namespace, String path) {
        //? if <=1.20.4 {
        return new net.minecraft.resources.ResourceLocation(namespace, path);
        //?} else {
        /*return net.minecraft.resources.ResourceLocation.tryBuild(namespace, path);        *///?}
    }

    public static net.minecraft.resources.ResourceLocation of(String id) {
        //? if <=1.20.1 {
        return new net.minecraft.resources.ResourceLocation(id);
        //?} else {
        /*return net.minecraft.resources.ResourceLocation.tryParse(id);        *///?}
    }
}
