package dev.tobynguyen27.definedsblocks.blocks.miku

import dev.tobynguyen27.sense.sync.annotation.Synced
import dev.tobynguyen27.sense.sync.blockentity.AutoSyncBlockEntity
import dev.tobynguyen27.sense.sync.container.ManagedFieldContainer
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class MikuBlockEntity(
    type: BlockEntityType<MikuBlockEntity>,
    blockPos: BlockPos,
    blockState: BlockState,
) : BlockEntity(type, blockPos, blockState), AutoSyncBlockEntity {

    private val managedFieldContainer by lazy { ManagedFieldContainer(this) }

    @Synced var isClicked: Boolean = false
    @Synced var cooldown: Int = 0
    @Synced var squishTicks: Int = 0
    @Synced var prevSquishTicks: Int = 0

    override fun getSelf(): BlockEntity = this

    override fun getFieldContainer(): ManagedFieldContainer = managedFieldContainer
}
