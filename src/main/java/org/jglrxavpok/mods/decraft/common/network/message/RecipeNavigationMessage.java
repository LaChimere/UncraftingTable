package org.jglrxavpok.mods.decraft.common.network.message;

import org.jglrxavpok.mods.decraft.inventory.ContainerUncraftingTable;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class RecipeNavigationMessage implements IMessage
{

	private int recipeIndex;


	public RecipeNavigationMessage()
	{
	}

	public RecipeNavigationMessage(int recipeIndex)
	{
		this.recipeIndex = recipeIndex;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.recipeIndex = ByteBufUtils.readVarShort(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarShort(buf, this.recipeIndex);
	}


	public static final class MessageHandler implements IMessageHandler<RecipeNavigationMessage, IMessage>
	{

		@Override
		public IMessage onMessage(RecipeNavigationMessage message, MessageContext ctx)
		{
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;

			Container container = player.openContainer;
			if (container instanceof ContainerUncraftingTable)
			{
				ContainerUncraftingTable uncraftingContainer = (ContainerUncraftingTable)container;
				uncraftingContainer.uncraftingResult.selectedCraftingGrid = message.recipeIndex;
				uncraftingContainer.switchRecipe();
			}

			return null;
		}
	}

}

