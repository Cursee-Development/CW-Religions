package com.cursee.cw_religions.core.registry;

import com.cursee.cw_religions.CWReligions;
import com.cursee.cw_religions.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class ModItems {

    public static final Item CW_RELIGIONS = new Item(new Item.Properties().stacksTo(1));

    public static void register(BiConsumer<Item, ResourceLocation> consumer) {
        consumer.accept(CW_RELIGIONS, CWReligions.identifier(Constants.MOD_ID));
        consumer.accept(new BlockItem(ModBlocks.ALTAR, new Item.Properties()), BuiltInRegistries.BLOCK.getKey(ModBlocks.ALTAR));
    }
}
