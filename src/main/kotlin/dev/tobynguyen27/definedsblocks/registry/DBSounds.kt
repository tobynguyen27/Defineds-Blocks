package dev.tobynguyen27.definedsblocks.registry

import dev.tobynguyen27.definedsblocks.DefinedsBlocks.MOD_ID
import dev.tobynguyen27.definedsblocks.DefinedsBlocks.REGISTRATE
import dev.tobynguyen27.definedsblocks.util.Identifier
import dev.tobynguyen27.sense.util.FormattingUtils
import net.minecraft.core.Registry
import net.minecraft.sounds.SoundEvent

object DBSounds {

    val MIKU = registerSound("miku")
    val TETO = registerSound("teto")
    val NERU = registerSound("neru")

    private fun registerSound(name: String): SoundEvent {
        val id = Identifier(name)

        REGISTRATE.addRawLang("subtitle.$MOD_ID.$name", FormattingUtils.toEnglishName(name))

        return Registry.register(Registry.SOUND_EVENT, id, SoundEvent(id))
    }

    fun register() {}
}
