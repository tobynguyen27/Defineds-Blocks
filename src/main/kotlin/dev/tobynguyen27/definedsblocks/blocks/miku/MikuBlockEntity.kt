package dev.tobynguyen27.definedsblocks.blocks.miku

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class MikuBlockEntity(
    type: BlockEntityType<MikuBlockEntity>,
    blockPos: BlockPos,
    blockState: BlockState,
) : BlockEntity(type, blockPos, blockState) {

    var isClicked: Boolean = false
    var cooldown: Int = 0

    var squishTicks: Int = 0
    var prevSquishTicks: Int = 0

    override fun getUpdateTag(): CompoundTag {
        return saveWithoutMetadata()
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener> {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    override fun saveAdditional(tag: CompoundTag) {
        tag.putBoolean("isClicked", isClicked)
        tag.putInt("cooldown", cooldown)
        tag.putInt("squishTicks", squishTicks)
        tag.putInt("prevSquishTicks", prevSquishTicks)

        super.saveAdditional(tag)
    }

    override fun load(tag: CompoundTag) {
        isClicked = tag.getBoolean("isClicked")
        cooldown = tag.getInt("cooldown")
        squishTicks = tag.getInt("squishTicks")
        prevSquishTicks = tag.getInt("prevSquishTicks")

        super.load(tag)
    }
}
