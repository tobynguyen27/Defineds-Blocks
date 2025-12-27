package dev.tobynguyen27.definedsblocks.registry

import com.tterrag.registrate.providers.RegistrateRecipeProvider.has
import com.tterrag.registrate.util.entry.BlockEntry
import dev.tobynguyen27.definedsblocks.DefinedsBlocks
import dev.tobynguyen27.definedsblocks.blocks.miku.MikuBlock
import dev.tobynguyen27.definedsblocks.blocks.miku.MikuBlockEntity
import dev.tobynguyen27.definedsblocks.registry.helper.BlockRegistry
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.item.Items

object DBBlocks {

    val MIKU: BlockEntry<MikuBlock> =
        BlockRegistry.registerWoolLikeBlock(MikuBlock.ID, ::MikuBlock)
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
            .item()
            .defaultModel()
            .build()
            .register()

    fun register() {
        DefinedsBlocks.LOGGER.info("Registering blocks...")
    }
}
