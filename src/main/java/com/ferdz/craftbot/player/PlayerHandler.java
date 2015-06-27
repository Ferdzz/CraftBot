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
					//This is not used, but it could be usefull. This method is called on each living tick of the player, so if you want to handle the bot, it would be here.
				}
			}
		}
	}
	
	//Forces the player to look under and place a block
	public void placeBlockUnder(EntityPlayer player) {
		player.rotationPitch = 91; // Looking down
		if (player.onGround)
			player.jump(); //Jumping
		ForcePlayer.forcePlaceBlock(player); //Placing
	}
}