package dev.tobynguyen27.definedsblocks.client.render

import dev.tobynguyen27.definedsblocks.blocks.plushie.miku.MikuBlockEntityRenderer
import dev.tobynguyen27.definedsblocks.blocks.plushie.neru.NeruBlockEntityRenderer
import dev.tobynguyen27.definedsblocks.blocks.plushie.teto.TetoBlockEntityRenderer
import dev.tobynguyen27.definedsblocks.registry.DBBlockEntities
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry

object DBBlockEntityRenderers {

    fun register() {
        BlockEntityRendererRegistry.register(DBBlockEntities.MIKU.get(), ::MikuBlockEntityRenderer)
        BlockEntityRendererRegistry.register(DBBlockEntities.TETO.get(), ::TetoBlockEntityRenderer)
        BlockEntityRendererRegistry.register(DBBlockEntities.NERU.get(), ::NeruBlockEntityRenderer)
    }
}
