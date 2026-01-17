package dev.tobynguyen27.definedsblocks.blocks.plushie.miku

import com.mojang.blaze3d.vertex.PoseStack
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlockEntity
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieRendererUtils
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

class MikuBlockEntityRenderer(ctx: BlockEntityRendererProvider.Context) :
    BlockEntityRenderer<PlushieBlockEntity> {

    override fun render(
        blockEntity: PlushieBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int,
    ) {
        PlushieRendererUtils.renderPlushieBlock(
            PlushieRendererUtils.Plushie.MIKU,
            blockEntity,
            partialTick,
            poseStack,
            bufferSource,
        )
    }
}
