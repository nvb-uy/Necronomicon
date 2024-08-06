package elocindev.necronomicon.datagen;

//? if fabric {
/*import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {  }

    @Override
    public void generateItemModels(ItemModelGenerators generator) { }

    // *------------------------*
    //         UTILITIES
    // *------------------------*

    public static void registerSlabStairSet(BlockModelGenerators generator, Block slab, Block stairs, Block parentBlock) {
        ModelGenerator.registerSlab(slab, parentBlock, generator.modelOutput, generator.blockStateOutput);
        ModelGenerator.registerStairs(stairs, parentBlock, generator.modelOutput, generator.blockStateOutput);
    }

    public static void registerWoodset(BlockModelGenerators generator, Block planks, Block door, Block trapdoor, Block slab, Block stairs, Block button, Block fence, Block fenceGate) {
        generator.createTrivialCube(planks);
        generator.createDoor(door);
        generator.createTrapdoor(trapdoor);
        ModelGenerator.registerSlab(slab, planks, generator.modelOutput, generator.blockStateOutput);
        ModelGenerator.registerStairs(stairs, planks, generator.modelOutput, generator.blockStateOutput);
        ModelGenerator.registerButton(button, planks, generator.modelOutput, generator.blockStateOutput);
        ModelGenerator.registerFence(fence, planks, generator.modelOutput, generator.blockStateOutput);
        ModelGenerator.registerFenceGate(fenceGate, planks, generator.modelOutput, generator.blockStateOutput);
    }

    public static void registerBars(Block barBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> blockStateCollector) {
        ResourceLocation identifier = ModelLocationUtils.getModelLocation(barBlock, "_post_ends");
        ResourceLocation identifier2 = ModelLocationUtils.getModelLocation(barBlock, "_post");
        ResourceLocation identifier3 = ModelLocationUtils.getModelLocation(barBlock, "_cap");
        ResourceLocation identifier4 = ModelLocationUtils.getModelLocation(barBlock, "_cap_alt");
        ResourceLocation identifier5 = ModelLocationUtils.getModelLocation(barBlock, "_side");
        ResourceLocation identifier6 = ModelLocationUtils.getModelLocation(barBlock, "_side_alt");
        blockStateCollector.accept(MultiPartGenerator.multiPart(barBlock).with(Variant.variant().with(VariantProperties.MODEL, identifier)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier2)).with(Condition.condition().term(BlockStateProperties.NORTH, true).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier3)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, true).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, true).term(BlockStateProperties.WEST, false), Variant.variant().with(VariantProperties.MODEL, identifier4)).with(Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, identifier4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, identifier5)).with(Condition.condition().term(BlockStateProperties.EAST, true), Variant.variant().with(VariantProperties.MODEL, identifier5).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, identifier6)).with(Condition.condition().term(BlockStateProperties.WEST, true), Variant.variant().with(VariantProperties.MODEL, identifier6).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)));
        ModelGenerator.registerItemModel(barBlock, modelCollector);
    }

    public static final void registerItemModel(Block block, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector) {
        Item item = block.asItem();
        
        if (item != Items.AIR) {
            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(block), modelCollector);
        }
    }

    public static void registerFence(Block fenceBlock, Block plankBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> blockStateCollector) {
        TextureMapping textureMap = TextureMapping.cube(plankBlock);
        ResourceLocation identifier = ModelTemplates.FENCE_POST.create(fenceBlock, textureMap, modelCollector);
        ResourceLocation identifier2 = ModelTemplates.FENCE_SIDE.create(fenceBlock, textureMap, modelCollector);
        blockStateCollector.accept(BlockModelGenerators.createFence(fenceBlock, identifier, identifier2));
        ResourceLocation identifier3 = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, textureMap, modelCollector);
        ModelGenerator.registerParentedItemModel(fenceBlock, identifier3, modelCollector);
    }

    public static void registerFenceGate(Block fenceGateBlock, Block plankBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> blockStateCollector) {
        TextureMapping textureMap = TextureMapping.cube(plankBlock);
        ResourceLocation identifier = ModelTemplates.FENCE_GATE_OPEN.create(fenceGateBlock, textureMap, modelCollector);
        ResourceLocation identifier2 = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGateBlock, textureMap,modelCollector);
        ResourceLocation identifier3 = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGateBlock, textureMap, modelCollector);
        ResourceLocation identifier4 = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGateBlock, textureMap, modelCollector);
        blockStateCollector.accept(BlockModelGenerators.createFenceGate(fenceGateBlock, identifier, identifier2, identifier3, identifier4, false));

        ResourceLocation identifier5 = ModelTemplates.FENCE_INVENTORY.create(fenceGateBlock, textureMap, modelCollector);
        ModelGenerator.registerParentedItemModel(fenceGateBlock, identifier5, modelCollector);
    }

    public static void registerSlab(Block slabBlock, Block plankBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> blockStateCollector) {
        TextureMapping textureMap = TextureMapping.cube(plankBlock);
        TextureMapping textureMap2 = TextureMapping.column(TextureMapping.getBlockTexture(plankBlock, ""), textureMap.get(TextureSlot.TOP));

        ResourceLocation identifier = ModelTemplates.SLAB_BOTTOM.create(slabBlock, textureMap2, modelCollector);
        ResourceLocation identifier2 = ModelTemplates.SLAB_TOP.create(slabBlock, textureMap2, modelCollector);
        ResourceLocation identifier3 = ModelTemplates.CUBE_COLUMN.createWithOverride(slabBlock, "_double", textureMap2, modelCollector);

        blockStateCollector.accept(createSlabBlockState(slabBlock, identifier, identifier2, identifier3));
    }

    public static void registerButton(Block buttonBlock, Block plankBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> blockStateCollector) {
        TextureMapping textureMap = TextureMapping.cube(plankBlock);

        ResourceLocation identifier = ModelTemplates.BUTTON.create(buttonBlock, textureMap, modelCollector);
        ResourceLocation identifier2 = ModelTemplates.BUTTON_PRESSED.create(buttonBlock, textureMap, modelCollector);
        ResourceLocation identifier3 = ModelTemplates.BUTTON_INVENTORY.create(buttonBlock, textureMap, modelCollector);

        blockStateCollector.accept(createButtonBlockState(buttonBlock, identifier, identifier2));
        ModelGenerator.registerParentedItemModel(buttonBlock, identifier3, modelCollector);
    }

    public static void registerStairs(Block stairBlock, Block plankBlock, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> blockStateCollector) {
        TextureMapping textureMap = TextureMapping.cube(plankBlock);
        TextureMapping textureMap2 = TextureMapping.column(TextureMapping.getBlockTexture(plankBlock, ""), textureMap.get(TextureSlot.TOP));

        ResourceLocation identifier = ModelTemplates.STAIRS_STRAIGHT.create(stairBlock, textureMap2, modelCollector);

        blockStateCollector.accept(createStairsBlockState(stairBlock, identifier, identifier, identifier));
    }

    public final void registerFronds(Block block, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector, Consumer<BlockStateGenerator> blockStateCollector) {
        TextureMapping textureMap = (new TextureMapping()).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(Blocks.END_STONE)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, ""));
        blockStateCollector.accept(createSingletonBlockState(block, ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block, textureMap, modelCollector)));
    }

    public static MultiVariantGenerator createSingletonBlockState(Block block, ResourceLocation modelId) {
        return MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, modelId));
    }

    public static BlockStateGenerator createSlabBlockState(Block slabBlock, ResourceLocation bottomModelId, ResourceLocation topModelId, ResourceLocation fullModelId) {
        return MultiVariantGenerator.multiVariant(slabBlock).with(PropertyDispatch.property(BlockStateProperties.SLAB_TYPE).select(SlabType.BOTTOM, Variant.variant().with(VariantProperties.MODEL, bottomModelId)).select(SlabType.TOP, Variant.variant().with(VariantProperties.MODEL, topModelId)).select(SlabType.DOUBLE, Variant.variant().with(VariantProperties.MODEL, fullModelId)));
    }

    public static BlockStateGenerator createStairsBlockState(Block stairsBlock, ResourceLocation innerModelId, ResourceLocation regularModelId, ResourceLocation outerModelId) {
        return MultiVariantGenerator.multiVariant(stairsBlock).with(PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE).select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)).select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)).select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)).select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)).select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)).select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, true)).select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, true)));
    }

    public static BlockStateGenerator createButtonBlockState(Block buttonBlock, ResourceLocation regularModelId, ResourceLocation pressedModelId) {
        return MultiVariantGenerator.multiVariant(buttonBlock).with(PropertyDispatch.property(BlockStateProperties.POWERED).select(false, Variant.variant().with(VariantProperties.MODEL, regularModelId)).select(true, Variant.variant().with(VariantProperties.MODEL, pressedModelId))).with(PropertyDispatch.properties(BlockStateProperties.ATTACH_FACE, BlockStateProperties.HORIZONTAL_FACING).select(AttachFace.FLOOR, Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(AttachFace.FLOOR, Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)).select(AttachFace.FLOOR, Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).select(AttachFace.FLOOR, Direction.NORTH, Variant.variant()).select(AttachFace.WALL, Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(AttachFace.WALL, Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(AttachFace.WALL, Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(AttachFace.WALL, Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, true)).select(AttachFace.CEILING, Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)).select(AttachFace.CEILING, Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)).select(AttachFace.CEILING, Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)).select(AttachFace.CEILING, Direction.NORTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)));
    }

    public static void registerParentedItemModel(Block block, ResourceLocation parentModelId, BiConsumer<ResourceLocation, Supplier<JsonElement>> modelCollector) {
        modelCollector.accept(ModelLocationUtils.getModelLocation(block.asItem()), new DelegatedModel(parentModelId));
    }
}
*///?}