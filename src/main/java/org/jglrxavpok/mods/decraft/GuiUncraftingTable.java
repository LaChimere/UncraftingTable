package org.jglrxavpok.mods.decraft;

import java.awt.*;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.resources.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import org.jglrxavpok.mods.decraft.ContainerUncraftingTable.UncraftingStatus;
import org.jglrxavpok.mods.decraft.common.config.ModConfiguration;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

public class GuiUncraftingTable extends GuiContainer
{

    private static final ResourceLocation UNCRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation(ModUncrafting.MODID + ":textures/gui/container/uncrafting_gui.png");

    public ContainerUncraftingTable container;
    private String blockName;
    private World worldObj;
    private EntityPlayer player;

    public GuiUncraftingTable(InventoryPlayer playerInventory, World world, String blockName)
    {
    	super(new ContainerUncraftingTable(playerInventory, world));
    	
        container = (ContainerUncraftingTable)inventorySlots;
        this.blockName = blockName;
        this.worldObj = world;
        this.player = playerInventory.player;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        int xSize = this.xSize;
        int ySize = this.ySize;
        
    	// fontRendererObj.drawString:
    	// Args: string, x, y, color, dropShadow
    	
    	
    	// render the block name at the top of the gui
        fontRendererObj.drawString(blockName, xSize / 2 - fontRendererObj.getStringWidth(blockName) / 2 + 1, 5, 4210752);
        
        // write "inventory" above the player inventory
        fontRendererObj.drawString(I18n.format("container.inventory"), 6, ySize - 96 + 2, 4210752);

        // write "compute:" above the input slots
        String compute = I18n.format("uncrafting.compute") + ":";
        fontRendererObj.drawString(EnumChatFormatting.DARK_GRAY + compute + EnumChatFormatting.RESET, 24 - fontRendererObj.getStringWidth(compute) / 2 + 1, 22, 0);
        fontRendererObj.drawString(EnumChatFormatting.GRAY + compute + EnumChatFormatting.RESET, 24 - fontRendererObj.getStringWidth(compute) / 2, 21, 0);
        
        // write the xp cost above the arrow
        Color darkGreen = new Color(75, 245, 75);
        fontRendererObj.drawString(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.UNDERLINE + "" + (ModConfiguration.standardLevel + container.uncraftingCost) + " levels" + EnumChatFormatting.RESET, xSize / 2 - fontRendererObj.getStringWidth((ModConfiguration.standardLevel + container.uncraftingCost) + " levels") / 2 + 1, ySize - 126 - 10, 0);
        fontRendererObj.drawString(EnumChatFormatting.UNDERLINE + "" + (ModConfiguration.standardLevel + container.uncraftingCost) + " levels" + EnumChatFormatting.RESET, xSize / 2 - fontRendererObj.getStringWidth((ModConfiguration.standardLevel + container.uncraftingCost) + " levels") / 2, ySize - 127 - 10, darkGreen.getRGB());

        String string = container.uncraftingStatusText;
        if (string != null)
        {
            UncraftingStatus msgType = container.uncraftingStatus;
            EnumChatFormatting format = EnumChatFormatting.GREEN;
            EnumChatFormatting shadowFormat = EnumChatFormatting.DARK_GRAY;
            if (msgType == ContainerUncraftingTable.UncraftingStatus.ERROR)
            {
                format = EnumChatFormatting.WHITE;
                shadowFormat = EnumChatFormatting.DARK_RED;
            }
            fontRendererObj.drawString(shadowFormat + string + EnumChatFormatting.RESET, 6 + 1, ySize - 95 + 2 - fontRendererObj.FONT_HEIGHT, 0);
            fontRendererObj.drawString(format + string + EnumChatFormatting.RESET, 6, ySize - 96 + 2 - fontRendererObj.FONT_HEIGHT, 0);
        }
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.renderEngine.bindTexture(UNCRAFTING_TABLE_GUI_TEXTURES);

        int k = this.width / 2 - this.xSize / 2;
        int l = this.height / 2 - this.ySize / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        GL11.glPopMatrix();
    }

}
