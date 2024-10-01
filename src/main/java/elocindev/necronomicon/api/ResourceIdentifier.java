package elocindev.necronomicon.api;

import net.minecraft.resources.ResourceLocation;

public class ResourceIdentifier {
    public static ResourceLocation get(String id) {
        //? if >=1.21 {
        /* return ResourceLocation.parse(id);
        *///? } else {
        return new ResourceLocation(id);
        //? }
    }

    public static ResourceLocation get(String namespace, String path) {
        //? if >=1.21 {
        /* return ResourceLocation.fromNamespaceAndPath(namespace, path);
        *///? } else {
        return new ResourceLocation(namespace, path);
        //? }
    }
}
