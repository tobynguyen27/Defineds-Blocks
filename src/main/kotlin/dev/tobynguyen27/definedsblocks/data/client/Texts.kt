package dev.tobynguyen27.definedsblocks.data.client

import dev.tobynguyen27.definedsblocks.DefinedsBlocks

object Texts {
    const val PEACEFUL_BLOCK = "text.peaceful_block"

    fun register() {
        val texts = hashMapOf(PEACEFUL_BLOCK to "Mobs cannot spawn on this block")

        texts.forEach { (k, v) -> DefinedsBlocks.REGISTRATE.addRawLang(k, v) }
    }
}
