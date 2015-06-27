package com.ferdz.craftbot.keybind;

import com.ferdz.craftbot.CraftBot;
import com.ferdz.craftbot.player.ForcePlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyBindHandler {
	
	//Toggle the bot status
	@SubscribeEvent
	public void onKeyEvent(KeyInputEvent event) {
		
		if (KeyBindings.bot.isPressed()) {
			CraftBot.bot = !CraftBot.bot;
			
			if (!CraftBot.bot) {
				ForcePlayer.resetInputs();
			}
		}
	}
}
