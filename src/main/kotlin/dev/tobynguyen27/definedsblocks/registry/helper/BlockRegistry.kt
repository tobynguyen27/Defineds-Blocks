package dev.tobynguyen27.definedsblocks.registry.helper

import com.tterrag.registrate.Registrate
import com.tterrag.registrate.builders.BlockBuilder
import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.BlockEntry
import dev.tobynguyen27.definedsblocks.DefinedsBlocks.REGISTRATE
import dev.tobynguyen27.definedsblocks.registry.DBTags
import dev.tobynguyen27.sense.util.FormattingUtils
import java.util.function.Supplier
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material

object BlockRegistry {

    fun <T : StairBlock> registerStairBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
        baseBlock: BlockEntry<out Block>,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.STONE)
                    .sound(SoundType.STONE)
                    .strength(1.8f)
                    .explosionResistance(1.8f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .blockstate { ctx, prov ->
                val texture = prov.modLoc("block/${baseBlock.id.path}")
                prov.stairsBlock(ctx.entry, texture)
            }
            .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)
            .simpleItem()
            .recipe { ctx, prov ->
                prov.stairs(
                    DataIngredient.stacks(baseBlock.get().asItem().defaultInstance),
                    { ctx.get() },
                    null,
                    true,
                )
            }
    }

    fun <T : SlabBlock> registerSlabBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
        baseBlock: BlockEntry<out Block>,
    ): BlockBuilder<T, Registrate> {
        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.STONE)
                    .sound(SoundType.STONE)
                    .strength(1.8f)
                    .explosionResistance(1.8f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .blockstate { ctx, prov ->
                val id = baseBlock.id.path
                val texture = prov.modLoc("block/$id")
                prov.slabBlock(ctx.entry, texture, texture)
            }
            .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)
            .simpleItem()
            .recipe { ctx, prov ->
                prov.slab(
                    DataIngredient.stacks(baseBlock.get().asItem().defaultInstance),
                    { ctx.get() },
                    null,
                    true,
                )
            }
    }

    fun <T : Block> registerWoolLikeBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
        ingredientBlock: String,
    ): BlockBuilder<T, Registrate> {
        val id = ResourceLocation("chipped", ingredientBlock)

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
            .tag(BlockTags.WOOL, TagKey.create(ResourceKey.createRegistryKey(id), id))
            .item()
            .tag(TagKey.create(ResourceKey.createRegistryKey(id), id))
            .build()
    }

    fun <T : Block> registerPastelLikeBlock(
        name: String,
        factory: (BlockBehaviour.Properties) -> T,
        ingredientBlock: String,
    ): BlockBuilder<T, Registrate> {
        val id = ResourceLocation("chipped", ingredientBlock)

        return REGISTRATE.block(name, factory)
            .lang(FormattingUtils.toEnglishName(name))
            .properties {
                FabricBlockSettings.of(Material.STONE)
                    .sound(SoundType.STONE)
                    .strength(1.8f)
                    .explosionResistance(1.8f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn { _, _, _, _ -> false }
            }
            .tag(
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.NEEDS_STONE_TOOL,
                TagKey.create(ResourceKey.createRegistryKey(id), id),
            )
            .item()
            .tag(TagKey.create(ResourceKey.createRegistryKey(id), id))
            .build()
    }

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
            .tag(
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.NEEDS_STONE_TOOL,
                DBTags.BlockTag.Chipped.STONE,
            )
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
            .model { ctx, prov -> prov.withExistingParent(ctx.name, prov.modLoc("item/plushie")) }
            .build()
            .blockstate { ctx, provider -> {} }
    }
}
