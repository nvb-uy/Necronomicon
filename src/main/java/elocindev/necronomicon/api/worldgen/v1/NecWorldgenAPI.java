package elocindev.necronomicon.api.worldgen.v1;

//#if FABRIC==1
import elocindev.necronomicon.worldgen.util.TreeFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
//#endif

/**
 * A class containing methods to generate stuff in features/structures.
 * Used mostly for Eldritch End.
 * 
 * @apiNote SIDE: Fabric
 * 
 * @author  ElocinDev
 * @since   1.0.0
 */
public class NecWorldgenAPI {
    //#if FABRIC==1

    /**
     * Generates an eldritch medium tree with a random variant.
     * 
     * @param world         : World accessor from structure/feature
     * @param pos           : Position to generate the tree
     * @param block         : BlockState of the log to be used
     * @param leaves        : BlockState of the leaves to be used
     * @param withLeaves    : Whether or not to generate leaves
     * 
     * @author ElocinDev
     */
    public static void genMediumTree(StructureWorldAccess world, BlockPos pos, BlockState block, BlockState leaves, boolean withLeaves) {
        TreeFactory.addRandomMedium(world, pos, block, leaves, withLeaves);
    }

    /**
     * Generates a specific eldritch medium tree variant.
     * 
     * @param world             : World accessor from structure/feature
     * @param pos               : Position to generate the tree
     * @param block             : BlockState of the log to be used
     * @param leaves            : BlockState of the leaves to be used
     * @param withLeaves        : Whether or not to generate leaves
     * @param variant           {0, 1, 2} : The variant of the tree to generate
     * 
     * @see                     NecWorldgenAPI#genMediumTree(StructureWorldAccess, BlockPos, BlockState, BlockState, boolean)
     * 
     * @author ElocinDev
     */
    public static void genMediumTree(StructureWorldAccess world, BlockPos pos, BlockState block, BlockState leaves, boolean withLeaves, int variant) {
        switch(variant) {
            case 0: TreeFactory.placeMediumDeadTree1(world, pos, block, leaves, withLeaves); break;
            case 1: TreeFactory.placeMediumDeadTree2(world, pos, block, leaves, withLeaves); break;
            case 2: TreeFactory.placeMediumDeadTree3(world, pos, block, leaves, withLeaves); break;
            default: {
                TreeFactory.placeMediumDeadTree1(world, pos, block, leaves, withLeaves); break;
            }
        }
    }

    //#else
    //$$ public void genMediumTree() { throw new UnsupportedOperationException("This method is fabric-only"); }
    //#endif
}
