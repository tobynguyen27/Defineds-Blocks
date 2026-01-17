package dev.tobynguyen27.definedsblocks.blocks.plushie.miku

import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlock
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlockEntity
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlockEntityTick
import dev.tobynguyen27.definedsblocks.registry.DBBlockEntities
import dev.tobynguyen27.definedsblocks.registry.DBSounds
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class MikuBlock(properties: Properties) : PlushieBlock(properties) {
    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult,
    ): InteractionResult {
        val be = level.getBlockEntity(pos) as PlushieBlockEntity

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
                PlushieBlockEntityTick::clientTick,
            )

        return createTickerHelper(
            blockEntityType,
            DBBlockEntities.MIKU.get(),
            PlushieBlockEntityTick::serverTick,
        )
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return DBBlockEntities.MIKU.create(pos, state)
    }
}
