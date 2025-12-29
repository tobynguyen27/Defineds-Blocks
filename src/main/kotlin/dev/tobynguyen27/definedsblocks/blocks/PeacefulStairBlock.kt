package dev.tobynguyen27.definedsblocks.blocks

import dev.tobynguyen27.definedsblocks.data.client.Texts
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.state.BlockState

class PeacefulStairBlock(blockState: BlockState, properties: Properties) :
    StairBlock(blockState, properties) {

    override fun appendHoverText(
        stack: ItemStack,
        level: BlockGetter?,
        tooltip: MutableList<Component>,
        flag: TooltipFlag,
    ) {
        tooltip.add(TranslatableComponent(Texts.PEACEFUL_BLOCK).withStyle(ChatFormatting.GRAY))
    }
}
