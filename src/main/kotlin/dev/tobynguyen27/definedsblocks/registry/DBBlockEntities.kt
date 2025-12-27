package dev.tobynguyen27.definedsblocks.registry

import com.tterrag.registrate.util.entry.BlockEntityEntry
import dev.tobynguyen27.definedsblocks.DefinedsBlocks
import dev.tobynguyen27.definedsblocks.blocks.miku.MikuBlockEntity
import net.minecraft.core.Registry

object DBBlockEntities {

    val MIKU: BlockEntityEntry<MikuBlockEntity> =
        BlockEntityEntry.cast(DBBlocks.MIKU.getSibling(Registry.BLOCK_ENTITY_TYPE))

    fun register() {
        DefinedsBlocks.LOGGER.info("Registering block entities...")
    }
}
