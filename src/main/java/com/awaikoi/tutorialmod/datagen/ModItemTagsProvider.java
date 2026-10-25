package com.awaikoi.tutorialmod.datagen;

import com.awaikoi.tutorialmod.TutorialMod;
import com.awaikoi.tutorialmod.block.ModBlocks;
import com.awaikoi.tutorialmod.item.ModItems;
import com.awaikoi.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TutorialMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(Items.IRON_INGOT)
                .add(Items.REDSTONE)
                .add(ModItems.AZURITE.get());

        tag(ModTags.Items.AZURITE_REPAIRABLE)
                .add(ModItems.AZURITE.get());

        tag(ItemTags.SWORDS).add(ModItems.AZURITE_SWORD.get());
        tag(ItemTags.SPEARS).add(ModItems.AZURITE_SPEAR.get());
        tag(ItemTags.PICKAXES).add(ModItems.AZURITE_PICKAXE.get());
        tag(ItemTags.AXES).add(ModItems.AZURITE_AXE.get());
        tag(ItemTags.HOES).add(ModItems.AZURITE_HOE.get());
        tag(ItemTags.SHOVELS).add(ModItems.AZURITE_SHOVEL.get());
    }
}
