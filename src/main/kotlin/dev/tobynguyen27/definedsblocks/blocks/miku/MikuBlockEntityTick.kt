package dev.tobynguyen27.definedsblocks.blocks.miku

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState

object MikuBlockEntityTick {

    fun clientTick(
        level: Level,
        blockPos: BlockPos,
        blockState: BlockState,
        blockEntity: MikuBlockEntity,
    ) {
        blockEntity.prevSquishTicks = blockEntity.squishTicks
        if (blockEntity.squishTicks > 0) {
            blockEntity.squishTicks--
        }
        blockEntity.setChanged()
    }

    fun serverTick(
        level: Level,
        blockPos: BlockPos,
        blockState: BlockState,
        blockEntity: MikuBlockEntity,
    ) {
        if (blockEntity.isClicked && blockEntity.cooldown > 0) {
            blockEntity.cooldown--
            blockEntity.setChanged()

            if (blockEntity.cooldown == 0) {
                blockEntity.isClicked = false
                blockEntity.setChanged()
                level.sendBlockUpdated(blockPos, blockState, blockState, 3)
            }
        }

        blockEntity.syncTick()
    }
}
