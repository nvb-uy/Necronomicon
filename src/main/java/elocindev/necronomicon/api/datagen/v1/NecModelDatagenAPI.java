package elocindev.necronomicon.api.datagen.v1;

//#if FABRIC==1

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.gson.JsonElement;

import elocindev.necronomicon.datagen.ModelGenerator;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.When;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

//#else
//$$ import net.minecraft.resources.ResourceLocation;
//$$ import net.minecraft.world.item.Item;
//$$ import net.minecraftforge.client.model.generators.ItemModelBuilder;
//$$ import net.minecraftforge.registries.RegistryObject;
//#endif

/**
 * A class containing methods to make model generation easier using Fabric's Data Generator.
 * 
 * @platform Fabric
 * @side     Client
 * 
 * @see     NecRecipeDatagenAPI
 * @see     NecLootDatagenAPI
 * 
 * @author  ElocinDev
 * @since   1.0.0
 */
public class NecModelDatagenAPI {
    //#if FABRIC==1

    /**
     *  Registers a basic generated item model.
     * 
     * @param item              The item to register the model for.
     * @param modelCollector    The model collector to register the model to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItem(Item item, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        if (item != Items.AIR) {
            Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(item), modelCollector);
        }
    }

    /**
     *  Registers a block's BlockItem model.
     * 
     * @param block             The block to register the BlockItem model for.
     * @param modelCollector    The model collector to register the model to.
     * 
     * @see NecModelDatagenAPI#makeItem
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItem(Block block, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        makeItem(block.asItem(), modelCollector);
    }

    /**
     *  Registers a basic item model for an array of items.
     * 
     * @param items                 An array that holds all items to be registered.
     * @param modelCollector        The model collector to register the model to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItems(Item[] items, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        for (Item item : items) {
            makeItem(item, modelCollector);
        }
    }

    /**
     *  Registers a basic blockitem model for an array of blocks.
     * 
     * @param blocks                An array that holds all blocks to register the item models.
     * @param modelCollector        The model collector to register the model to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.3
     */
    public static void makeItems(Block[] blocks, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector) {
        for (Block block : blocks) {
            makeItem(block, modelCollector);
        }
    }

    /**
     *  Registers a series of cube_all models for blocks inside an array.
     * 
     * @param generator     The model generator to register the blocks to.
     * @param blocks        An array of blocks to register cube_all models for.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeCubeAllBlocks(BlockStateModelGenerator generator, Block[] blocks) {
        for (Block block : blocks) {
            generator.registerSimpleCubeAll(block);
        }
    }

    /**
     * Registers a whole woodset's model generation.
     * 
     * @param generator     The model generator to register the woodset to.
     * @param planks        The planks block of the woodset.
     * @param door          The door block of the woodset.
     * @param trapdoor      The trapdoor block of the woodset.
     * @param slab          The slab block of the woodset.
     * @param stairs        The stairs block of the woodset.
     * @param button        The button block of the woodset.
     * @param fence         The fence block of the woodset.
     * @param fenceGate     The fence gate block of the woodset.
     * 
     * @see NecModelDatagenAPI#makeSlab
     * @see NecModelDatagenAPI#makeStairs
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeWoodset(BlockStateModelGenerator generator, 
        Block planks, Block door, Block trapdoor, Block slab, Block stairs, Block button, Block fence, Block fenceGate) {

        ModelGenerator.registerWoodset(generator, planks, door, trapdoor, slab, stairs, button, fence, fenceGate);
    }

    /**
     * Registers a slab block model generation.
     * 
     * @param slabBlock         The slab block to register.
     * @param parentBlock       The parent block of the slab. (Full block)
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The block state collector to register the block state to.
     * 
     * @see NecModelDatagenAPI#makeSlabStair
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeSlab(Block slabBlock, Block parentBlock, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> stateCollector) {
        ModelGenerator.registerSlab(slabBlock, parentBlock, modelCollector, stateCollector);
    }

    /**
     * Registers a stairs block model generation.
     * 
     * @param stairBlock        The stairs block to register.
     * @param parentBlock       The parent block of the stairs. (Full block)
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The block state collector to register the block state to.
     * 
     * @see NecModelDatagenAPI#makeSlabStair
     *
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeStair(Block stairBlock, Block parentBlock, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> stateCollector) {
        ModelGenerator.registerStairs(stairBlock, parentBlock, modelCollector, stateCollector);
    }

    /**
     * Makes models for a slab and stair block from a parent block.
     * 
     * @param generator     The model generator to register the blocks to.
     * @param slab          The slab block to register.
     * @param stairs        The stairs block to register.
     * @param parentBlock   The parent block of the slab and stairs. (Full block)
     * 
     * @see NecModelDatagenAPI#makeSlab
     * @see NecModelDatagenAPI#makeStair
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeSlabStair(BlockStateModelGenerator generator, Block slab, Block stairs, Block parentBlock) {
        ModelGenerator.registerSlab(slab, parentBlock, generator.modelCollector, generator.blockStateCollector);
        ModelGenerator.registerStairs(stairs, parentBlock, generator.modelCollector, generator.blockStateCollector);
    }


    /**
     * Makes models and blockstates for a bar block.
     * 
     * @param barBlock          The bar block to register.
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the block state to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeBarBlock(Block barBlock, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> stateCollector) {
        Identifier identifier = ModelIds.getBlockSubModelId(barBlock, "_post_ends");
        Identifier identifier2 = ModelIds.getBlockSubModelId(barBlock, "_post");
        Identifier identifier3 = ModelIds.getBlockSubModelId(barBlock, "_cap");
        Identifier identifier4 = ModelIds.getBlockSubModelId(barBlock, "_cap_alt");
        Identifier identifier5 = ModelIds.getBlockSubModelId(barBlock, "_side");
        Identifier identifier6 = ModelIds.getBlockSubModelId(barBlock, "_side_alt");
        stateCollector.accept(MultipartBlockStateSupplier.create(barBlock).with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)).with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3)).with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4)).with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5)).with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier6)).with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier6).put(VariantSettings.Y, VariantSettings.Rotation.R90)));
        ModelGenerator.registerItemModel(barBlock, modelCollector);
    }

    /**
     * Makes models and blockstates for a fence block.
     * 
     * @param fenceBlock        The fence block to register.
     * @param parentBlock       The parent block of the fence (Full block).
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the block state to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeFenceBlock(Block fenceBlock, Block parentBlock, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> stateCollector) {
        TextureMap textureMap = TextureMap.all(parentBlock);
        Identifier identifier = Models.FENCE_POST.upload(fenceBlock, textureMap, modelCollector);
        Identifier identifier2 = Models.FENCE_SIDE.upload(fenceBlock, textureMap, modelCollector);
        stateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, identifier, identifier2));
        Identifier identifier3 = Models.FENCE_INVENTORY.upload(fenceBlock, textureMap, modelCollector);
        ModelGenerator.registerParentedItemModel(fenceBlock, identifier3, modelCollector);
    }

    /**
     * Makes models and blockstates for a fence block.
     * 
     * @param fenceGateBlock    The fence gate block to register.
     * @param parentBlock       The parent block of the fence (Full block).
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the block state to.
     * 
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeFenceGateBlock(Block fenceGateBlock, Block parentBlock, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> stateCollector) {
        TextureMap textureMap = TextureMap.all(parentBlock);
        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, textureMap, modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, textureMap,modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, textureMap, modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, textureMap, modelCollector);
        stateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, identifier, identifier2, identifier3, identifier4, false));

        Identifier identifier5 = Models.FENCE_INVENTORY.upload(fenceGateBlock, textureMap, modelCollector);
        ModelGenerator.registerParentedItemModel(fenceGateBlock, identifier5, modelCollector);
    }

    /**
     * Makes models and blockstates for a button block.
     * 
     * @param buttonBlock       The button block to register.
     * @param parentBlock       The parent block of the button (Full block).
     * @param modelCollector    The model collector to register the model to.
     * @param stateCollector    The blockState collector to register the bloc
     *
     * @platform Fabric
     * 
     * @author ElocinDev
     * @since 1.0.0
     */
    public static void makeButtonBlock(Block buttonBlock, Block parentBlock, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<BlockStateSupplier> stateCollector) {
        TextureMap textureMap = TextureMap.all(parentBlock);

        Identifier identifier = Models.BUTTON.upload(buttonBlock, textureMap, modelCollector);
        Identifier identifier2 = Models.BUTTON_PRESSED.upload(buttonBlock, textureMap, modelCollector);
        Identifier identifier3 = Models.BUTTON_INVENTORY.upload(buttonBlock, textureMap, modelCollector);

        stateCollector.accept(ModelGenerator.createButtonBlockState(buttonBlock, identifier, identifier2));
        ModelGenerator.registerParentedItemModel(buttonBlock, identifier3, modelCollector);
    }

    //#else
    
    /**
     *  Registers a basic generated item model.
     * 
     * @param item             The item to register the model for.
     * @param modid            The modid of the mod parent of the item.
     * 
     * @platform Forge
     * 
     * @author ElocinDev
     * @since 1.0.3
    */
    //$$ public static void ItemModelBuilder makeItem(RegistryObject<Item> item, String modid) {
    //$$     return withExistingParent(item.getId().getPath(),
    //$$             new ResourceLocation("item/generated")).texture("layer0",
    //$$             new ResourceLocation(modid,"item/" + item.getId().getPath()));
    //$$ }
    //#endif
}
