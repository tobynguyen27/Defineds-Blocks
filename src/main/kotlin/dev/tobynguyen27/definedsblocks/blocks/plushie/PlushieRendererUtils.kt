package dev.tobynguyen27.definedsblocks.blocks.plushie

import com.mojang.blaze3d.vertex.PoseStack
import dev.tobynguyen27.codebebelib.render.CCModel
import dev.tobynguyen27.codebebelib.render.CCRenderState
import dev.tobynguyen27.codebebelib.render.model.OBJParser
import dev.tobynguyen27.codebebelib.vec.Matrix4
import dev.tobynguyen27.codebebelib.vec.Vector3
import dev.tobynguyen27.definedsblocks.util.Identifier
import kotlin.math.sin
import net.minecraft.client.renderer.LevelRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.properties.BlockStateProperties

object PlushieRendererUtils {
    private fun getPlushieMaterial(name: Plushie): PlushieRenderingMaterial {
        val name = name.toString()

        val obj = OBJParser(Identifier("models/obj/$name.obj")).quads().ignoreMtl().parse()
        val model = CCModel.combine(obj.values).backfacedCopy()

        val texture = Identifier("textures/obj/$name.png")
        val squishedTexture = Identifier("textures/obj/${name}_squished.png")

        return PlushieRenderingMaterial(texture, squishedTexture, model)
    }

    fun renderPlushieItem(
        type: Plushie,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        light: Int,
        overlay: Int,
    ) {
        val (texture, _, model) = getPlushieMaterial(type)

        val ccrs = CCRenderState.instance()
        ccrs.reset()
        ccrs.overlay = overlay
        ccrs.brightness = light

        val mat = Matrix4(poseStack)
        mat.translate(0.5, 0.0, 0.5)

        ccrs.bind(RenderType.entityCutout(texture), bufferSource, mat)
        model.render(ccrs)
    }

    fun renderPlushieBlock(
        type: Plushie,
        blockEntity: PlushieBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
    ) {
        val (texture, squishedTexture, model) = getPlushieMaterial(type)

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
        mat.scale(0.5, 0.5 * yScale, 0.5)

        mat.rotate(Math.toRadians(270 - facing.toYRot().toDouble()), Vector3.Y_POS)

        ccrs.bind(
            if (blockEntity.isClicked) RenderType.entityCutout(squishedTexture)
            else RenderType.entityCutout(texture),
            bufferSource,
            mat,
        )
        model.render(ccrs)
    }

    enum class Plushie {
        MIKU,
        TETO,
        NERU;

        override fun toString(): String {
            return super.toString().lowercase()
        }
    }

    data class PlushieRenderingMaterial(
        val texture: ResourceLocation,
        val squishedTexture: ResourceLocation,
        val model: CCModel,
    ) {}
}
