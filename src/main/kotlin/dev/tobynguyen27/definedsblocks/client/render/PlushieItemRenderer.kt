package dev.tobynguyen27.definedsblocks.client.render

import com.mojang.blaze3d.vertex.PoseStack
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieRendererUtils
import dev.tobynguyen27.definedsblocks.registry.DBBlocks
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.block.model.ItemTransforms
import net.minecraft.world.item.ItemStack

object PlushieItemRenderer : BuiltinItemRendererRegistry.DynamicItemRenderer {
    override fun render(
        stack: ItemStack,
        mode: ItemTransforms.TransformType,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int,
    ) {
        val type =
            when {
                stack.`is`(DBBlocks.MIKU.get().asItem()) -> PlushieRendererUtils.Plushie.MIKU
                stack.`is`(DBBlocks.TETO.get().asItem()) -> PlushieRendererUtils.Plushie.TETO
                else -> PlushieRendererUtils.Plushie.NERU
            }

        PlushieRendererUtils.renderPlushieItem(type, matrices, vertexConsumers, light, overlay)
    }
}
