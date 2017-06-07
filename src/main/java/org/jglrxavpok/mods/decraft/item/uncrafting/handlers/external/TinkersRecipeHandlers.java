package org.jglrxavpok.mods.decraft.item.uncrafting.handlers.external;

import org.apache.commons.lang3.ArrayUtils;
import org.jglrxavpok.mods.decraft.common.config.ModJsonConfiguration;
import org.jglrxavpok.mods.decraft.common.config.ModJsonConfiguration.ItemMapping;
import org.jglrxavpok.mods.decraft.item.uncrafting.handlers.NBTSensitiveRecipeHandlers.INBTSensitiveRecipeHandler;
import org.jglrxavpok.mods.decraft.item.uncrafting.handlers.RecipeHandlers.ShapedOreRecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;

public class TinkersRecipeHandlers
{

	public static class TableRecipeHandler extends ShapedOreRecipeHandler implements INBTSensitiveRecipeHandler
	{

		private ItemStack inputStack;

		public static Class<? extends IRecipe> recipeClass;

		static
		{
			try
			{
				recipeClass = Class.forName("slimeknights.tconstruct.tools.common.TableRecipe").asSubclass(IRecipe.class);
			}
			catch(ClassNotFoundException ex) { }
		}


		@Override
		public NonNullList<ItemStack> getCraftingGrid(IRecipe r)
		{
			NonNullList<ItemStack> result = super.getCraftingGrid(r);

			ItemMapping mapping = ModJsonConfiguration.ITEM_MAPPINGS.get(inputStack);
			if (mapping != null)
			{
				if (mapping.replaceSlots != null)
				{
					ItemStack textureBlock = new ItemStack(inputStack.getTagCompound().getCompoundTag("textureBlock"));
					for ( int i = 0 ; i < result.size() ; i++ )
					{
						if (ArrayUtils.indexOf(mapping.replaceSlots, i) >= 0)
						{
							result.set(i, textureBlock.copy());
						}
					}
				}
			}

			return result;
		}


		@Override
		public void setInputStack(ItemStack stack) { this.inputStack = stack; }

		@Override
		public ItemStack getInputStack() { return this.inputStack; }

	}

}
