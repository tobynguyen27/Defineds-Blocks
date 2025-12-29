package dev.tobynguyen27.definedsblocks.registry.helper

import com.tterrag.registrate.Registrate
import com.tterrag.registrate.builders.BlockBuilder
import com.tterrag.registrate.util.DataIngredient
import dev.tobynguyen27.definedsblocks.DefinedsBlocks.REGISTRATE
import dev.tobynguyen27.definedsblocks.registry.DBTags
import dev.tobynguyen27.sense.util.FormattingUtils
import java.util.function.Supplier
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.client.renderer.RenderType
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material

object BlockRegistry {
    fun <T : Block> registerGlassLikeBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.BUILDABLE_GLASS)
                    .sound(SoundType.GLASS)
                    .strength(0.3f)
                    .explosionResistance(0.3f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
                    .noOcclusion()
            }
            .tag(BlockTags.IMPERMEABLE, DBTags.BlockTag.Chipped.GLASS)
            .item()
            .tag(DBTags.ItemTag.Chipped.GLASS)
            .build()
            .addLayer { Supplier { RenderType.translucent() } }
    }

    fun <T : Block> registerDeepslateLikeBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.STONE)
                    .sound(SoundType.DEEPSLATE)
                    .strength(3f)
                    .explosionResistance(6f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .tag(
                BlockTags.MINEABLE_WITH_SHOVEL,
                BlockTags.NEEDS_STONE_TOOL,
                DBTags.BlockTag.Chipped.DEEPSLATE,
            )
            .item()
            .tag(DBTags.ItemTag.Chipped.DEEPSLATE)
            .build()
    }

    fun <T : Block> registerDirtLikeBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.DIRT)
                    .sound(SoundType.GRAVEL)
                    .strength(0.5f)
                    .explosionResistance(0.5f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .tag(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.NEEDS_STONE_TOOL)
            .simpleItem()
            .recipe { ctx, prov ->
                prov.stonecutting(DataIngredient.stacks(Blocks.DIRT.asItem().defaultInstance)) {
                    ctx.get()
                }
            }
    }

    fun <T : Block> registerStoneLikeBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.STONE)
                    .sound(SoundType.STONE)
                    .strength(5f)
                    .explosionResistance(6.0f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL, DBTags.BlockTag.Chipped.STONE)
            .item()
            .tag(DBTags.ItemTag.Chipped.STONE)
            .build()
    }

    fun <T : Block> registerPlushieBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.WOOL)
                    .sound(SoundType.WOOL)
                    .strength(0.8f)
                    .explosionResistance(0.8f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .tag(BlockTags.WOOL)
            .item()
            .defaultModel()
            .build()
    }
}
