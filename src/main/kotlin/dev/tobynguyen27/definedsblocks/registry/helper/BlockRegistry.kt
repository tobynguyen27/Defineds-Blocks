package dev.tobynguyen27.definedsblocks.registry.helper

import com.tterrag.registrate.Registrate
import com.tterrag.registrate.builders.BlockBuilder
import dev.tobynguyen27.definedsblocks.DefinedsBlocks.REGISTRATE
import dev.tobynguyen27.sense.util.FormattingUtils
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material

object BlockRegistry {

    fun <T : Block> registerDirtLikeBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.DIRT)
                    .sound(SoundType.GRASS)
                    .strength(0.5f)
                    .explosionResistance(0.5f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .tag(BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.NEEDS_STONE_TOOL)
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
            .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)
    }

    fun <T : Block> registerWoolLikeBlock(
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
    }
}
