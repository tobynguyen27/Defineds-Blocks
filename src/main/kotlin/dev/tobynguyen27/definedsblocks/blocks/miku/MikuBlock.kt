package dev.tobynguyen27.definedsblocks.blocks.miku

import dev.tobynguyen27.codebebelib.raytracer.VoxelShapeCache
import dev.tobynguyen27.codebebelib.vec.Cuboid6
import dev.tobynguyen27.definedsblocks.data.client.Texts
import dev.tobynguyen27.definedsblocks.registry.DBBlockEntities
import dev.tobynguyen27.definedsblocks.registry.DBSounds
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class MikuBlock(properties: Properties) : BaseEntityBlock(properties) {
    companion object {
        const val ID = "hatsune_miku_plushie"

        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING

        private val SHAPE = Cuboid6(0.25, 0.0, 0.25, 0.75, 0.8, 0.75)
    }

    init {
        registerDefaultState(with(defaultBlockState()) { setValue(FACING, Direction.NORTH) })
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun setPlacedBy(
        level: Level,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        stack: ItemStack,
    ) {
        if (placer !is Player) return

        level.setBlock(pos, level.getBlockState(pos).setValue(FACING, placer.direction.opposite), 3)
    }

    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult,
    ): InteractionResult {
        val be = level.getBlockEntity(pos) as MikuBlockEntity

        be.squishTicks = 5

        if (!level.isClientSide) {
            be.isClicked = true
            be.cooldown = 5
            be.setChanged()
            level.sendBlockUpdated(pos, state, state, 3)

            level.playSound(
                null,
                pos,
                DBSounds.MIKU,
                net.minecraft.sounds.SoundSource.BLOCKS,
                1.0f,
                1.0f,
            )
        }

        return InteractionResult.sidedSuccess(level.isClientSide)
    }

    override fun <T : BlockEntity> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>,
    ): BlockEntityTicker<T>? {
        if (level.isClientSide)
            return createTickerHelper(
                blockEntityType,
                DBBlockEntities.MIKU.get(),
                MikuBlockEntityTick::clientTick,
            )

        return createTickerHelper(
            blockEntityType,
            DBBlockEntities.MIKU.get(),
            MikuBlockEntityTick::serverTick,
        )
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return DBBlockEntities.MIKU.create(pos, state)
    }

    override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return VoxelShapeCache.getShape(SHAPE)
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.INVISIBLE
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: BlockGetter?,
        tooltip: MutableList<Component>,
        flag: TooltipFlag,
    ) {
        tooltip.add(TranslatableComponent(Texts.PEACEFUL_BLOCK).withStyle(ChatFormatting.GRAY))
    }
}
