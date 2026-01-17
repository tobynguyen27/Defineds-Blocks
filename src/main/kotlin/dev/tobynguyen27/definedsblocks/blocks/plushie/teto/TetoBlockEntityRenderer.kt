package dev.tobynguyen27.definedsblocks.blocks.plushie.teto

import com.mojang.blaze3d.vertex.PoseStack
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlockEntity
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieRendererUtils
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

class TetoBlockEntityRenderer(ctx: BlockEntityRendererProvider.Context) :
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
            PlushieRendererUtils.Plushie.TETO,
            blockEntity,
            partialTick,
            poseStack,
            bufferSource,
        )
    }
}
