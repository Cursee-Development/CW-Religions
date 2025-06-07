package com.cursee.cw_religions.core.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class ModItems {

    public static void register(BiConsumer<Item, ResourceLocation> consumer) {
        consumer.accept(new BlockItem(ModBlocks.ALTAR, new Item.Properties()), BuiltInRegistries.BLOCK.getKey(ModBlocks.ALTAR));
    }
}
