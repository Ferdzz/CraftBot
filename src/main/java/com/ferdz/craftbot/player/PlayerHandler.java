package com.ferdz.craftbot.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.ferdz.craftbot.CraftBot;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerHandler {
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			if (event.entityLiving.equals(Minecraft.getMinecraft().thePlayer)) {
				if (CraftBot.bot) {
					
				}
			}
		}
	}
	
	public void placeBlockUnder(EntityPlayer player) {
		player.rotationPitch = 91;
		if (player.onGround)
			player.jump();
		ForcePlayer.forcePlaceBlock(player);
	}
}