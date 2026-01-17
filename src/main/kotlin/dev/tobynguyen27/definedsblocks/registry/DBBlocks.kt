package dev.tobynguyen27.definedsblocks.registry

import com.tterrag.registrate.providers.RegistrateRecipeProvider.has
import com.tterrag.registrate.util.entry.BlockEntry
import dev.tobynguyen27.definedsblocks.DefinedsBlocks
import dev.tobynguyen27.definedsblocks.blocks.FuturaBlock
import dev.tobynguyen27.definedsblocks.blocks.PeacefulBlock
import dev.tobynguyen27.definedsblocks.blocks.PeacefulGlassBlock
import dev.tobynguyen27.definedsblocks.blocks.PeacefulSlabBlock
import dev.tobynguyen27.definedsblocks.blocks.PeacefulStairBlock
import dev.tobynguyen27.definedsblocks.blocks.plushie.PlushieBlockEntity
import dev.tobynguyen27.definedsblocks.blocks.plushie.miku.MikuBlock
import dev.tobynguyen27.definedsblocks.blocks.plushie.neru.NeruBlock
import dev.tobynguyen27.definedsblocks.blocks.plushie.teto.TetoBlock
import dev.tobynguyen27.definedsblocks.registry.helper.BlockRegistry
import dev.tobynguyen27.definedsblocks.util.Identifier
import dev.tobynguyen27.sense.util.FormattingUtils
import java.util.function.Supplier
import net.minecraft.client.renderer.RenderType
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.Material

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

        val pastels =
            hashMapOf(
                "black_concrete" to "black_pastel",
                "blue_concrete" to "blue_pastel",
                "brown_concrete" to "brown_pastel",
                "gray_concrete" to "gray_pastel",
                "green_concrete" to "green_pastel",
                "light_blue_concrete" to "light_blue_pastel",
                "light_gray_concrete" to "light_gray_pastel",
                "lime_concrete" to "light_green_pastel",
                "red_concrete" to "light_red_pastel",
                "magenta_concrete" to "magenta_pastel",
                "orange_concrete" to "orange_pastel",
                "purple_concrete" to "purple_pastel",
                "red_concrete" to "red_pastel",
                "white_concrete" to "white_pastel",
                "yellow_concrete" to "yellow_pastel",
            )
        pastels.forEach { (ingredient, name) ->
            with(BlockRegistry) {
                val concreteBlock =
                    registerPastelLikeBlock(name, ::PeacefulBlock, ingredient).register()
                registerSlabBlock("${name}_slab", ::PeacefulSlabBlock, concreteBlock).register()
                registerStairBlock(
                        "${name}_stairs",
                        { properties ->
                            PeacefulStairBlock(concreteBlock.defaultState, properties)
                        },
                        concreteBlock,
                    )
                    .register()
                registerWoolLikeBlock(
                        ingredient.replace("concrete", "pastel_wool"),
                        ::PeacefulBlock,
                        ingredient.replace("concrete", "wool"),
                    )
                    .register()
            }
        }
    }

    val MIKU: BlockEntry<MikuBlock> =
        BlockRegistry.registerPlushieBlock("hastune_miku_plushie", ::MikuBlock)
            .blockEntity { type, blockPos, blockState ->
                PlushieBlockEntity(type, blockPos, blockState)
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

    val TETO: BlockEntry<TetoBlock> =
        BlockRegistry.registerPlushieBlock("kasane_teto_plushie", ::TetoBlock)
            .blockEntity { type, blockPos, blockState ->
                PlushieBlockEntity(type, blockPos, blockState)
            }
            .build()
            .recipe { ctx, prov ->
                ShapedRecipeBuilder.shaped(ctx.entry)
                    .pattern(" A ")
                    .pattern("BCB")
                    .pattern(" B ")
                    .define("B".toCharArray()[0], Items.WHITE_WOOL)
                    .define("C".toCharArray()[0], Items.LIGHT_GRAY_WOOL)
                    .define("A".toCharArray()[0], Items.RED_WOOL)
                    .unlockedBy("has_wool", has(Items.WHITE_WOOL))
                    .save(prov)
            }
            .register()

    val NERU: BlockEntry<NeruBlock> =
        BlockRegistry.registerPlushieBlock("akita_neru_plushie", ::NeruBlock)
            .blockEntity { type, blockPos, blockState ->
                PlushieBlockEntity(type, blockPos, blockState)
            }
            .build()
            .recipe { ctx, prov ->
                ShapedRecipeBuilder.shaped(ctx.entry)
                    .pattern(" A ")
                    .pattern("BCB")
                    .pattern(" B ")
                    .define("B".toCharArray()[0], Items.WHITE_WOOL)
                    .define("C".toCharArray()[0], Items.LIGHT_GRAY_WOOL)
                    .define("A".toCharArray()[0], Items.YELLOW_WOOL)
                    .unlockedBy("has_wool", has(Items.WHITE_WOOL))
                    .save(prov)
            }
            .register()

    val FUTURA_BLOCK =
        DefinedsBlocks.REGISTRATE.block(FuturaBlock.ID, ::FuturaBlock)
            .properties { p -> p.requiresCorrectToolForDrops().strength(1.5f, 6.0f) }
            .initialProperties(Material.STONE)
            .lang(FormattingUtils.toEnglishName(FuturaBlock.ID))
            .blockstate { ctx, prov ->
                prov
                    .getVariantBuilder(ctx.get())
                    .partialState()
                    .with(FuturaBlock.FUTURA_BLOCK_TYPE, FuturaBlock.FuturaBlockType.block)
                    .modelForState()
                    .modelFile(
                        prov
                            .models()
                            .getExistingFile(Identifier("block/futura_block/futura_block_online"))
                    )
                    .addModel()
                    .partialState()
                    .with(FuturaBlock.FUTURA_BLOCK_TYPE, FuturaBlock.FuturaBlockType.column_x)
                    .modelForState()
                    .modelFile(
                        prov
                            .models()
                            .getExistingFile(
                                Identifier("block/futura_block/futura_block_column_online")
                            )
                    )
                    .rotationX(90)
                    .rotationY(90)
                    .addModel()
                    .partialState()
                    .with(FuturaBlock.FUTURA_BLOCK_TYPE, FuturaBlock.FuturaBlockType.column_y)
                    .modelForState()
                    .modelFile(
                        prov
                            .models()
                            .getExistingFile(
                                Identifier("block/futura_block/futura_block_column_online")
                            )
                    )
                    .addModel()
                    .partialState()
                    .with(FuturaBlock.FUTURA_BLOCK_TYPE, FuturaBlock.FuturaBlockType.column_z)
                    .modelForState()
                    .modelFile(
                        prov
                            .models()
                            .getExistingFile(
                                Identifier("block/futura_block/futura_block_column_online")
                            )
                    )
                    .rotationX(90)
                    .addModel()
            }
            .item()
            .model { ctx, prov ->
                prov.withExistingParent(
                    ctx.name,
                    Identifier("block/futura_block/futura_block_online"),
                )
            }
            .build()
            .recipe { ctx, prov ->
                ShapedRecipeBuilder.shaped(ctx.get(), 8)
                    .pattern("XXX")
                    .pattern("XYX")
                    .pattern("XXX")
                    .define("X".toCharArray()[0], Blocks.STONE)
                    .define("Y".toCharArray()[0], Items.REDSTONE)
                    .unlockedBy("has_redstone", has(Items.REDSTONE))
                    .save(prov)
            }
            .tag(BlockTags.NEEDS_STONE_TOOL, BlockTags.MINEABLE_WITH_PICKAXE)
            .addLayer { Supplier { RenderType.translucent() } }
            .register()

    fun register() {
        DefinedsBlocks.LOGGER.info("Registering blocks...")
    }
}
