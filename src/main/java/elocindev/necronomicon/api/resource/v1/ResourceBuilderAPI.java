package elocindev.necronomicon.api.resource.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//#if FABRIC==0
//$$ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

//$$ import net.minecraft.network.chat.Component;
//$$ import net.minecraft.server.packs.PackResources;
//$$ import net.minecraft.server.packs.PackType;
//$$ import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
//$$ import net.minecraft.server.packs.repository.Pack;
//$$ import net.minecraft.server.packs.repository.PackSource;
//$$ import net.minecraft.server.packs.repository.Pack.ResourcesSupplier;
//$$ import net.minecraftforge.resource.PathPackResources;
//$$ import net.minecraft.world.flag.FeatureFlagSet;
//$$ import net.minecraftforge.event.AddPackFindersEvent;
//$$ import net.minecraftforge.fml.ModList;

//$$ import java.nio.file.Path;
//$$ import java.util.function.Supplier;
//$$ import org.jetbrains.annotations.Nullable;
//$$ import java.util.function.Consumer;

//#else
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
//#endif

/**
 * Utility class for building builtin resource packs and other misc features.
 * 
 * @platform    Forge
 * @side        Both
 * @minecraft   >= 1.20
 * 
 * @author ElocinDev
 * @since 1.0.4
 */
public class ResourceBuilderAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger("necronomicon");

    /**
     * The pack format for the current version of Minecraft.
     * 
     * @see <a href="https://minecraft.fandom.com/wiki/Pack_format">Pack format list from the Minecraft wiki</a>
     * 
     * @since 1.0.4
     */
    public static final int PACK_FORMAT = 15;

    /**
     * Registers a builtin resource pack. Should be called in the constructor of your mod.
     * Defaults to the current version's pack format.
     * 
     * @param modid             The modid of the mod registering the pack.
     * @param path              String path of your pack inside your mod's assets folder. (Ex. "resourcepacks/mypack")
     * @param title             The title of the pack.
     * @param enabledDefault    Whether the pack is enabled by default.
     * @param description       The description of the pack.
     * @param packType          The type of pack (Client or Server)
     * @param pos               The position of the pack in the pack list.
     * @param fixed             Whether the pack is fixed or not.
     * 
     * @platform                Forge
     * @since 1.0.4
     * 
     * @author ElocinDev
     */
    //#if FABRIC==1
    @Deprecated
    //#endif
    public static void registerBuiltinPack(
        //#if FABRIC==0
        //$$ String modid, Path path, Component title, boolean enabledDefault, Component description, PackType packType, Pack.Position pos, boolean fixed  
        //#endif
    ) {
        //#if FABRIC==0
        //$$ var pack = new PathPackResources(ModList.get().getModFileById(modid).getFile().getFileName() + ":" + path, true, path);

        //$$ registerResourcePack(packType, () ->
        //$$             Pack.create(
        //$$                     modid,
        //$$                     title,
        //$$                     enabledDefault,
        //$$                     (s) -> pack,
        //$$                     new Pack.Info(description, PACK_FORMAT, FeatureFlagSet.of()),
        //$$                     packType,
        //$$                     pos,
        //$$                     fixed,
        //$$                     PackSource.BUILT_IN));
        //#else

        throw new UnsupportedOperationException("Forge only method");
        //#endif
    }

    /**
     * Registers a builtin resource pack. Should be called in the constructor of your mod.
     * 
     * @param modid             : The modid of the mod registering the pack.
     * @param path              : String path of your pack inside your mod's assets folder. (Ex. "resourcepacks/mypack")
     * @param title             : The title of the pack.
     * @param enabledDefault    : Whether the pack is enabled by default.
     * @param description       : The description of the pack.
     * @param packType          : The type of pack (Client or Server)
     * @param pos               : The position of the pack in the pack list.
     * @param fixed             : Whether the pack is fixed or not.
     * @param packFormat        : The pack format of the pack. <a href="https://minecraft.fandom.com/wiki/Pack_format">Pack format list from the Minecraft wiki</a>
     * 
     * @platform                Forge
     * @since 1.0.4
     * 
     * @author ElocinDev
     */
    //#if FABRIC==1
    @Deprecated
    //#endif
    public static void registerBuiltinPack(String modid
        //#if FABRIC==0
        //$$, Path path, Component title, boolean enabledDefault, Component description, PackType packType, Pack.Position pos, boolean fixed, int packFormat
        //#endif
    ) {
        //#if FABRIC==0
        //$$ var pack = new PathPackResources(ModList.get().getModFileById(modid).getFile().getFileName() + ":" + path, true, path);

        //$$ registerResourcePack(packType, () ->
        //$$             Pack.create(
        //$$                     modid,
        //$$                     title,
        //$$                     enabledDefault,
        //$$                     (s) -> pack,
        //$$                     new Pack.Info(description, packFormat, FeatureFlagSet.of()),
        //$$                     packType,
        //$$                     pos,
        //$$                     fixed,
        //$$                     PackSource.BUILT_IN));
        //#else
        throw new UnsupportedOperationException("Forge only method");
        //#endif
    }

    //#if FABRIC==1
    /**
     *  Registers a builtin resource pack. Should be called in the initializer of your mod.
     *  The pack will need to be added to the resourcepacks folder in your resources directory.
     * 
     * @param instance          The FabricLoader instance.
     * @param modid             The modid of the mod registering the pack.
     * @param id                The id of the pack. (This must match the name of the folder inside resourcepacks)
     * @param description       A text adding a description of the pack, formatting works.
     * @param enabledDefault    Whether the pack is enabled by default.
     * @param fixed             Whether the pack is fixed or not.
     * 
     * @platform                Fabric
     * @since 1.0.6
     * 
     * @author ElocinDev
     */
    public static void registerBuiltinPack(FabricLoader instance, String modid, String id, Text description, boolean enabledDefault, boolean fixed) {
        if (instance == null) throw new IllegalArgumentException("Fabric loader instance is null, call this method after Fabric is loaded.");
    
        if (fixed) registerResourcePack(instance, modid, id, description, ResourcePackActivationType.ALWAYS_ENABLED);
        else if (enabledDefault) registerResourcePack(instance, modid, id, description, ResourcePackActivationType.DEFAULT_ENABLED);
        else registerResourcePack(instance, modid, id, description, ResourcePackActivationType.NORMAL);
    }
    //#endif
    
    @SuppressWarnings("unused")
    private static void registerResourcePack(
        //#if FABRIC==0
        //$$ PackType packType, @Nullable Supplier<Pack> packSupplier
        //#else
        FabricLoader instance, String modid, String id, Text description, ResourcePackActivationType type
        //#endif
    ) {
        //#if FABRIC==0
        //$$ if (packSupplier == null) return;
        
        //$$ var bus = FMLJavaModLoadingContext.get().getModEventBus();
        //$$ Consumer<AddPackFindersEvent> consumer = event -> {
        //$$     if (event.getPackType() == packType) {
        //$$         var pack = packSupplier.get();
        //$$         if (pack != null) {
        //$$             event.addRepositorySource(infoConsumer -> infoConsumer.accept(packSupplier.get()));
        //$$         }
        //$$     }
        //$$ };

        //$$ bus.addListener(consumer);
        //#else
        instance.getModContainer(modid)
                        .map(container -> ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(modid, id),
                                container, description, type))
                        .filter(success -> !success).ifPresent(success -> LOGGER.warn("Could not register built-in resource pack. "+modid, id));
        //#endif
    }
}
