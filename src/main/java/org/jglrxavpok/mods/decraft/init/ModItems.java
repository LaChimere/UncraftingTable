package org.jglrxavpok.mods.decraft.init;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jglrxavpok.mods.decraft.ModUncrafting;
import org.jglrxavpok.mods.decraft.common.config.ModConfiguration;
import org.jglrxavpok.mods.decraft.item.ItemNugget;
import org.jglrxavpok.mods.decraft.item.ItemNugget.EnumNuggetType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@EventBusSubscriber(modid = ModUncrafting.MODID)
public class ModItems
{

	public static final Item DIAMOND_NUGGET = new ItemNugget(EnumNuggetType.DIAMOND);
	public static final Item EMERALD_NUGGET = new ItemNugget(EnumNuggetType.EMERALD);
	public static final Item LEATHER_STRIP = new ItemNugget(EnumNuggetType.LEATHER);

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		if (!ModConfiguration.registerNuggets) return;

		// register the items
		event.getRegistry().registerAll(DIAMOND_NUGGET, EMERALD_NUGGET, LEATHER_STRIP);
	}

	public static void registerOreDictionaryEntries()
	{
		if (!ModConfiguration.registerNuggets) return;

		// FIXME: use Tags
		// register the ore dictionary entries
		OreDictionary.registerOre("nuggetDiamond", new ItemStack(DIAMOND_NUGGET, 1));
		OreDictionary.registerOre("shardDiamond", new ItemStack(DIAMOND_NUGGET, 1));  // added for compatibility with Magic Bees
		OreDictionary.registerOre("nuggetEmerald", new ItemStack(EMERALD_NUGGET, 1));
		OreDictionary.registerOre("shardEmerald", new ItemStack(EMERALD_NUGGET, 1));  // added for compatibility with Magic Bees
		OreDictionary.registerOre("nuggetLeather", new ItemStack(LEATHER_STRIP, 1));
	}

}