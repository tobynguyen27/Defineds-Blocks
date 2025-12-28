package dev.tobynguyen27.definedsblocks

import com.tterrag.registrate.Registrate
import dev.tobynguyen27.definedsblocks.data.client.Texts
import dev.tobynguyen27.definedsblocks.registry.DBBlockEntities
import dev.tobynguyen27.definedsblocks.registry.DBBlocks
import dev.tobynguyen27.definedsblocks.registry.DBSounds
import dev.tobynguyen27.definedsblocks.util.Identifier
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.world.item.CreativeModeTab
import org.slf4j.LoggerFactory

object DefinedsBlocks : ModInitializer {

    const val MOD_ID = "definedsblocks"
    const val MOD_NAME = "Defined's Blocks"
    val LOGGER = LoggerFactory.getLogger(MOD_NAME)

    val REGISTRATE: Registrate = Registrate.create(MOD_ID)
    val ITEM_GROUP: CreativeModeTab =
        FabricItemGroupBuilder.build(Identifier("general")) {
            DBBlocks.MIKU.get().asItem().defaultInstance
        }

    override fun onInitialize() {
        REGISTRATE.creativeModeTab { ITEM_GROUP }
        REGISTRATE.addRawLang("itemGroup.$MOD_ID.general", MOD_NAME)

        Texts.register()
        DBSounds.register()
        DBBlocks.register()
        DBBlockEntities.register()
        REGISTRATE.register()
    }
}
