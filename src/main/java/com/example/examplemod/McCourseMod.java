package com.example.examplemod;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(McCourseMod.MOD_ID)
public class McCourseMod
{
    public static final String MOD_ID = "mccourse";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // McCourseMod default constructor
    public McCourseMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Use the above eventBus to register the mod items
        ModItems.register(eventBus);
        // Use the above eventBus to register the mod blocks
        ModBlocks.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::gatherDataEvent);

        // Register ourselves for server and other game events we are interested in
//        MinecraftForge.EVENT_BUS.register(this);
    }

    // APEX STUFF FOR GENERATING JSON FILES
    private void gatherDataEvent(GatherDataEvent event) {
        // Helper that forge adds that helps get file data from my mod
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Used to automatically generate the json files
        DataGenerator generator = event.getGenerator();

        if (event.includeClient()) {
            generator.addProvider(new BlockStateGenerator(generator, existingFileHelper));
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
