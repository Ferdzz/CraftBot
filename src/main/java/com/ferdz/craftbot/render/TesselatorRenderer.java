package com.ferdz.craftbot.render;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.lwjgl.opengl.GL11;

import com.ferdz.craftbot.CraftBot;
import com.ferdz.craftbot.util.BlockPos;
import com.ferdz.craftbot.util.Pos;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TesselatorRenderer {
	
	private static final double MAX = 1.05;
	private static final double MIN = -0.05;
	
	public void render(double x, double y, double z, Pos... markerPos) {
		
		GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_SRC_COLOR);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		OpenGlHelper.glBlendFunc(774, 768, 1, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		GL11.glPushMatrix();
		GL11.glPolygonOffset(-3.0F, -3.0F);
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		final boolean hadBlend = GL11.glIsEnabled(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_BLEND);
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setTranslation(-x, -y, -z);
		
		GL11.glTranslated(0, -1, 0); //to 
		for (final Pos m : markerPos) {
			tessellator.setColorRGBA_F(0.4f, 0, 0, 0.5f);
			if (m != null) {
				renderMarker(m, (int) x, (int) y, (int) z);
			}
		}
		
		tessellator.draw();
		tessellator.setTranslation(0.0D, 0.0D, 0.0D);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glPolygonOffset(0.0F, 0.0F);
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if (!hadBlend) {
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}
	
	private void renderMarker(Pos m, int x, int y, int z) {
		final Tessellator tessellator = Tessellator.instance;
		tessellator.addVertex(m.x + x + MIN, m.y + y + MAX, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MIN, m.y + y + MAX, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MAX, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MAX, m.z + z + MIN);
		
		tessellator.addVertex(m.x + x + MIN, m.y + y + MIN, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MIN, m.y + y + MIN, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MIN, m.y + y + MAX, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MIN, m.y + y + MAX, m.z + z + MIN);
		
		tessellator.addVertex(m.x + x + MAX, m.y + y + MAX, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MAX, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MIN, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MIN, m.z + z + MIN);
		
		tessellator.addVertex(m.x + x + MIN, m.y + y + MIN, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MIN, m.y + y + MAX, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MAX, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MIN, m.z + z + MIN);
		
		tessellator.addVertex(m.x + x + MIN, m.y + y + MAX, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MIN, m.y + y + MIN, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MIN, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MAX, m.z + z + MAX);
		
		tessellator.addVertex(m.x + x + MIN, m.y + y + MIN, m.z + z + MAX);
		tessellator.addVertex(m.x + x + MIN, m.y + y + MIN, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MIN, m.z + z + MIN);
		tessellator.addVertex(m.x + x + MAX, m.y + y + MIN, m.z + z + MAX);
	}
	
	/**
	 * Draws the position markers.
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public void drawMarkers(RenderWorldLastEvent event) {
		if (CraftBot.bot && CraftBot.currentSchematic != null && CraftBot.currentSchematic.blockPos != null) {

			final EntityLivingBase player = Minecraft.getMinecraft().renderViewEntity;
			final double x = player.lastTickPosX;
			final double y = player.lastTickPosY;
			final double z = player.lastTickPosZ;
			
			this.render(x, y, z, toArray(CraftBot.currentSchematic.blockPos));
		}
	}
	
	public BlockPos[] toArray(ArrayList<BlockPos> list) {
		BlockPos[] pos = new BlockPos[list.size()];
		for (int i = 0; i < pos.length; i++) {
			if(!list.get(i).block.equals(Blocks.air))
			pos[i] = list.get(i);
		}
		return pos;
	}
}
