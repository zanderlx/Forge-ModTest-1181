package com.example.examplemod.block;

import com.example.examplemod.McCourseMod;
import com.example.examplemod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.rmi.registry.Registry;
import java.util.function.Supplier;

public class ModBlocks {

    // Registry of modded blocks specific to the MOD_ID
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, McCourseMod.MOD_ID);

    /***
     * Create the registry object for the custom block: COBALT_BLOCK
     * Type is Material.METAL (same level as IRON or GOLD)
     * Strength is 5.0 hardness that controls time to break block (same level as IRON or GOLD)
     * Will be placed in the creative misc tab
     */
    public static final RegistryObject<Block> COBALT_BLOCK = registerBlock("cobalt_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    /***
     * Create the registry object for the custom block: COBALT_ORE
     * Type is Material.STONE (same level as STONE)
     * Strength is f.0 hardness that controls time to break block (same level as STONE)
     * Will be placed in the creative misc tab
     */
    public static final RegistryObject<Block> COBALT_ORE = registerBlock("cobalt_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    /***
     * Create registry object of a generic block that is placeable in the world
     * @param name The name of the block
     * @param block Supplier with the properties of the block
     * @param tab The inventory tab it will be in
     * @param <T> A registry object whose type is a generic block T
     * @return A placeable generic block object that is item stackable
     */
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab); // Specify that this block object is also a stackable item (IMPORTANT!)
        return toReturn;
    }

    /***
     * Create registry object to specify a generic block will be a stackable item and can appear in an inventory
     * Without this, the block will not appear to have items in the game and will be unobtainable
     * @param name The name of the stackable block item (should be the same as name as the block)
     * @param block A block registry object
     * @param tab The inventory tab it will be in
     * @param <T> A registry object whose type is a generic block T
     * @return A generic stackable block item
     */
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
