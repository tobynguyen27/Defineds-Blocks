package dev.tobynguyen27.definedsblocks.client.render

import dev.tobynguyen27.definedsblocks.registry.DBBlocks
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry

object DBItemRenderers {

    fun register() {
        BuiltinItemRendererRegistry.INSTANCE.register(DBBlocks.MIKU.get(), PlushieItemRenderer)
        BuiltinItemRendererRegistry.INSTANCE.register(DBBlocks.TETO.get(), PlushieItemRenderer)
        BuiltinItemRendererRegistry.INSTANCE.register(DBBlocks.NERU.get(), PlushieItemRenderer)
    }
}
