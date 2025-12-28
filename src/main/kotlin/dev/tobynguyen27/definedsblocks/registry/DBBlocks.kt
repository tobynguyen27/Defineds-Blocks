package dev.tobynguyen27.definedsblocks.registry

import com.tterrag.registrate.providers.RegistrateRecipeProvider.has
import com.tterrag.registrate.util.entry.BlockEntry
import dev.tobynguyen27.definedsblocks.DefinedsBlocks
import dev.tobynguyen27.definedsblocks.blocks.PeacefulBlock
import dev.tobynguyen27.definedsblocks.blocks.PeacefulGlassBlock
import dev.tobynguyen27.definedsblocks.blocks.miku.MikuBlock
import dev.tobynguyen27.definedsblocks.blocks.miku.MikuBlockEntity
import dev.tobynguyen27.definedsblocks.registry.helper.BlockRegistry
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.item.Items

object DBBlocks {

    init {
        val dirts = setOf("mossy_coarse_dirt", "flowery_mossy_coarse_dirt")
        dirts.forEach { BlockRegistry.registerDirtLikeBlock(it, ::PeacefulBlock).register() }

        val stones = setOf("mossier_stone_bricks", "flowery_mossier_stone_bricks")
        stones.forEach { BlockRegistry.registerStoneLikeBlock(it, ::PeacefulBlock).register() }

        val deepslates =
            setOf(
                "mossy_deepslate_bricks",
                "mossy_deepslate_tiles",
                "flowery_mossy_deepslate_bricks",
                "flowery_mossy_deepslate_tiles",
            )
        deepslates.forEach {
            BlockRegistry.registerDeepslateLikeBlock(it, ::PeacefulBlock).register()
        }

        val glasses = setOf("elf_glass", "mana_glass")
        glasses.forEach {
            BlockRegistry.registerGlassLikeBlock(it, ::PeacefulGlassBlock).register()
        }
    }

    val MIKU: BlockEntry<MikuBlock> =
        BlockRegistry.registerPlushieBlock(MikuBlock.ID, ::MikuBlock)
            .blockEntity { type, blockPos, blockState ->
                MikuBlockEntity(type, blockPos, blockState)
            }
            .build()
            .recipe { ctx, prov ->
                ShapedRecipeBuilder.shaped(ctx.entry)
                    .pattern(" A ")
                    .pattern("BCB")
                    .pattern(" B ")
                    .define("B".toCharArray()[0], Items.WHITE_WOOL)
                    .define("C".toCharArray()[0], Items.LIGHT_GRAY_WOOL)
                    .define("A".toCharArray()[0], Items.LIGHT_BLUE_WOOL)
                    .unlockedBy("has_wool", has(Items.WHITE_WOOL))
                    .save(prov)
            }
            .register()

    fun register() {
        DefinedsBlocks.LOGGER.info("Registering blocks...")
    }
}
