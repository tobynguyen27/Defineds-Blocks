package dev.tobynguyen27.definedsblocks.client

import dev.tobynguyen27.definedsblocks.client.render.DBBlockEntityRenderers
import dev.tobynguyen27.definedsblocks.client.render.DBItemRenderers
import net.fabricmc.api.ClientModInitializer

object DBClient : ClientModInitializer {
    override fun onInitializeClient() {
        DBBlockEntityRenderers.register()
        DBItemRenderers.register()
    }
}
