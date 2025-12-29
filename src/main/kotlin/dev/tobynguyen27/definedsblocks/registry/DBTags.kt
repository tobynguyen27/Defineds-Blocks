package dev.tobynguyen27.definedsblocks.registry

import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object DBTags {

    object ItemTag {
        object Chipped {
            val STONE: TagKey<Item> = create("stone")
            val DEEPSLATE: TagKey<Item> = create("deepslate")
            val GLASS: TagKey<Item> = create("glass")


            private fun create(path: String): TagKey<Item> {
                return TagKey.create(
                    ResourceKey.createRegistryKey(ResourceLocation("chipped", path)),
                    ResourceLocation("chipped", path),
                )
            }
        }
    }

    object BlockTag {
        object Chipped {
            val STONE: TagKey<Block> = create("stone")
            val DEEPSLATE: TagKey<Block> = create("deepslate")
            val GLASS: TagKey<Block> = create("glass")


            private fun create(path: String): TagKey<Block> {
                return TagKey.create(
                    ResourceKey.createRegistryKey(ResourceLocation("chipped", path)),
                    ResourceLocation("chipped", path),
                )
            }
        }
    }

    fun register() {}
}
