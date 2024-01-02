package dev.galiev.gofo.block;

import dev.galiev.gofo.registry.ItemsRegistry;
import dev.galiev.gofo.utils.GodsData;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ZeusStatue extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public ZeusStatue() {
        super(FabricBlockSettings.create().strength(-1).nonOpaque());
        stateManager.getDefaultState().with(FACING, Direction.NORTH);
    }

    private static final VoxelShape SHAPE = Block.createCuboidShape(0, -16, 0, 16, 32, 16);

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.FAIL;
        if (GodsData.getRepZeus(player) == 15) {
            var stack = player.getStackInHand(hand);
            if (stack.getItem() == Items.NETHERITE_SWORD) {
                EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, pos, SpawnReason.EVENT);
                var blocPos = new BlockPos.Mutable(pos.getX(), pos.getY() + 3, pos.getZ());
                world.breakBlock(blocPos, false);
                blocPos.setY(pos.getY() + 4);
                world.breakBlock(blocPos, false);
                world.createExplosion(null, blocPos.getX(), blocPos.getY(), blocPos.getZ(), 2.0F, World.ExplosionSourceType.BLOCK);
                player.setStackInHand(hand, ItemsRegistry.ZEUS_LIGHTNING.getDefaultStack());
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
