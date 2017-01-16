package org.jglrxavpok.mods.decraft.proxy;

import org.jglrxavpok.mods.decraft.ModUncrafting;
import org.jglrxavpok.mods.decraft.common.config.ModConfiguration;
import org.jglrxavpok.mods.decraft.common.network.message.RecipeNavigationMessage;
import org.jglrxavpok.mods.decraft.item.ModItems;
import org.jglrxavpok.mods.decraft.item.uncrafting.UncraftingManager;
import org.jglrxavpok.mods.decraft.stats.ModAchievements;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

public class CommonProxy {
	
	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
	 */
	public void preInit(){
		
		// initialize the configuration
	    ModConfiguration.preInit();
	    
        // register the block
        GameRegistry.registerBlock(ModUncrafting.uncraftingTable, ItemBlock.class, "uncrafting_table");
        
        // register the items
        ModItems.preInit();

        // register the network messages
        ModUncrafting.instance.getNetwork().registerMessage(RecipeNavigationMessage.MessageHandler.class, RecipeNavigationMessage.class, 0, Side.SERVER);
        
	}
	
	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes,
	 * send FMLInterModComms messages to other mods.
	 */
	public void init(){
		
        // create block crafting recipe
        GameRegistry.addShapedRecipe(new ItemStack(ModUncrafting.uncraftingTable), new Object[]
        {
            "SSS", "SXS", "SSS", 'X', Blocks.crafting_table, 'S', Blocks.cobblestone
        });

        // create the item crafting recipes
        ModItems.init();
        
        // initialize the achievements
		ModAchievements.init();
	}
	
	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit(){
		UncraftingManager.postInit();
	}

}
