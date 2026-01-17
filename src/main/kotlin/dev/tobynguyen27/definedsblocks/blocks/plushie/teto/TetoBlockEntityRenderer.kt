package dev.tobynguyen27.definedsblocks.blocks.plushie.teto

import com.mojang.blaze3d.vertex.PoseStack
import dev.tobynguyen27.codebebelib.render.CCModel
import dev.tobynguyen27.codebebelib.render.CCRenderState
import dev.tobynguyen27.codebebelib.render.model.OBJParser
import dev.tobynguyen27.codebebelib.vec.Matrix4
import dev.tobynguyen27.codebebelib.vec.Vector3
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlockEntity
import dev.tobynguyen27.definedsblocks.util.Identifier
import kotlin.math.sin
import net.minecraft.client.renderer.LevelRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.level.block.state.properties.BlockStateProperties

class TetoBlockEntityRenderer(ctx: BlockEntityRendererProvider.Context) :
    BlockEntityRenderer<PlushieBlockEntity> {

    companion object {
        private val TEXTURE = Identifier("textures/obj/teto.png")
        private val SQUISHED_TEXTURE = Identifier("textures/obj/teto_squished.png")
    }

    private val obj = OBJParser(Identifier("models/obj/teto.obj")).quads().ignoreMtl().parse()
    private val model = CCModel.combine(obj.values).backfacedCopy()

    override fun render(
        blockEntity: PlushieBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int,
    ) {
        val facing = blockEntity.blockState.getValue(BlockStateProperties.HORIZONTAL_FACING)
        val worldLight =
            LevelRenderer.getLightColor(
                blockEntity.getLevel(),
                blockEntity.blockState,
                blockEntity.blockPos,
            )

        val ccrs = CCRenderState.instance()
        ccrs.reset()
        ccrs.brightness = worldLight
        ccrs.overlay = OverlayTexture.NO_OVERLAY

        val mat = Matrix4(poseStack)
        mat.translate(0.5, 0.0, 0.5)

        var yScale = 1.0
        if (blockEntity.squishTicks > 0 || blockEntity.prevSquishTicks > 0) {
            val interpolatedTicks =
                blockEntity.prevSquishTicks +
                    (blockEntity.squishTicks - blockEntity.prevSquishTicks) * partialTick
            yScale = 1.0 + (-(sin(interpolatedTicks.toDouble())) * interpolatedTicks) * 0.025
        }
        mat.scale(0.75, 0.75 * yScale, 0.75)

        mat.rotate(Math.toRadians(270 - facing.toYRot().toDouble()), Vector3.Y_POS)

        ccrs.bind(
            if (blockEntity.isClicked) RenderType.entityCutout(SQUISHED_TEXTURE)
            else RenderType.entityCutout(TEXTURE),
            bufferSource,
            mat,
        )
        model.render(ccrs)
    }
}
