package com.awaikoi.tutorialmod.item.custom;

import com.awaikoi.tutorialmod.tags.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.function.Consumer;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if(!world.isClientSide()) {
            // We are on the server
            boolean foundBlock = false;

            for(int i = 0; i <= pos.getY() + 64; i++) {
                BlockState blockState = world.getBlockState(pos.below(i));

                if(isValuableBlock(blockState)) {
                    outputValuableCoordinates(pos.below(i), player, blockState.getBlock());
                    foundBlock = true;

                    // damage the item
                    context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                    // play sound
                    world.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME,
                            SoundSource.BLOCKS, 1.5f, 1f);
                    // spawn some particles
                    spawnFoundParticles(world, pos, blockState);

                    break;

                }
            }

            if(!foundBlock) {
                outputNoValuablesFound(player);
            }


        }


        return InteractionResult.PASS;
    }

    private void spawnFoundParticles(Level world, BlockPos pos, BlockState blockState) {
        for(int i = 0; i < 20; i++) {
            ServerLevel serverLevel = (ServerLevel) world;

            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                    pos.getX() + 0.5d, pos.getY() + 1, pos.getZ() + 0.5d, 1,
                    Math.cos(i * 18) * 0.15d, 0.15d, Math.sin(i * 18) * 0.15d, 0.1);
        }
    }

    private void outputNoValuablesFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.tutorialmod.metal_detector.no_valuables"));
    }

    private void outputValuableCoordinates(BlockPos pos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Valuables Found at: ")
                .append(block.getName()).append(Component.literal(" at (" + pos.getX() +
                        ", " + pos.getY() + ", " + pos.getZ() + ")")));
    }

    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(ModTags.Blocks.METAL_DETECTABLES);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.tutorialmod.metal_detector.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.tutorialmod.metal_detector"));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
