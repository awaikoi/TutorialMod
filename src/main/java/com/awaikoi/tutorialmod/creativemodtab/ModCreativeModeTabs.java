package com.awaikoi.tutorialmod.creativemodtab;

import com.awaikoi.tutorialmod.TutorialMod;
import com.awaikoi.tutorialmod.block.ModBlocks;
import com.awaikoi.tutorialmod.item.ModItems;
import com.mojang.authlib.yggdrasil.TextureUrlChecker;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final Supplier<CreativeModeTab> AZURITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("azurite_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.AZURITE.get()))
                    .title(Component.translatable("creativetab.tutorialmod.azurite_items"))
                    .withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    .withTabsAfter(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "azurite_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AZURITE);
                        output.accept(ModItems.RAW_AZURITE);
                        output.accept(ModItems.METAL_DECETOR);
                        output.accept(ModItems.ONION);
                        output.accept(ModItems.END_FIRE_STARTER);


                    }).build());

    public static final Supplier<CreativeModeTab> AZURITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("azurite_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.AZURITE_BLOCK.get()))
                    .title(Component.translatable("creativetab.tutorialmod.azurite_block"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.AZURITE_BLOCK);
                        output.accept(ModBlocks.RAW_AZURITE_BLOCK);
                        output.accept(ModBlocks.AZURITE_ORE);
                        output.accept(ModBlocks.AZURITE_DEEPSLATE_ORE);

                        output.accept(ModBlocks.MAGIC_BLOCK);


                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
