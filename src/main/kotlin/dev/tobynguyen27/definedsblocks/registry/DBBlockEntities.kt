package dev.tobynguyen27.definedsblocks.registry

import com.tterrag.registrate.util.entry.BlockEntityEntry
import dev.tobynguyen27.definedsblocks.DefinedsBlocks
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlockEntity
import net.minecraft.core.Registry

object DBBlockEntities {

    val MIKU: BlockEntityEntry<PlushieBlockEntity> =
        BlockEntityEntry.cast(DBBlocks.MIKU.getSibling(Registry.BLOCK_ENTITY_TYPE))

    val TETO: BlockEntityEntry<PlushieBlockEntity> =
        BlockEntityEntry.cast(DBBlocks.TETO.getSibling(Registry.BLOCK_ENTITY_TYPE))

    val NERU: BlockEntityEntry<PlushieBlockEntity> =
        BlockEntityEntry.cast(DBBlocks.NERU.getSibling(Registry.BLOCK_ENTITY_TYPE))

    fun register() {
        DefinedsBlocks.LOGGER.info("Registering block entities...")
    }
}
