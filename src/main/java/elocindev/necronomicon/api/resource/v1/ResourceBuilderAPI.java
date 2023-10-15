package elocindev.necronomicon.api.resource.v1;



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

//#endif

/**
 * Utility class for building builtin resource packs and other misc features.
 * 
 * @platform    Forge
 * @side        Both
 * 
 * @author ElocinDev
 * @since 1.0.4
 */
public class ResourceBuilderAPI {

    public static final int PACK_FORMAT = 15;

    /**
     * Registers a builtin resource pack. Should be called in the constructor of your mod.
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
    public static void registerBuiltinPack(
        //#if FABRIC==0
        //$$ String modid, Path path, Component title, boolean enabledDefault, Component description, PackType packType, Pack.Position pos, boolean fixed
        //#endif
    ) {
        //#if FABRIC==0
        //$$ var pack = new PathPackResources(ModList.get().getModFileById(modid).getFile().getFileName() + ":" + path, true, path);

        //$$ registerResourcePack(PackType.CLIENT_RESOURCES, () ->
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
        throw new UnsupportedOperationException("Not implemented in fabric yet!");
        //#endif
    }
    
    @SuppressWarnings("unused")
    private static void registerResourcePack(
        //#if FABRIC==0
        //$$ PackType packType, @Nullable Supplier<Pack> packSupplier
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
        //#endif
    }
}
