package com.ferdz.craftbot.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;

import com.ferdz.craftbot.keybind.InteractAlways;

//This class was meant to be a collection of way to overtake the player's movements
public class ForcePlayer {
	
	private static Minecraft mc = Minecraft.getMinecraft();
	
	private static KeyBinding resetUseItemKey;
	private static MovementInput resetMovementInput;
	private static boolean useItemKeyJustPressed;
	
	//Resets the mouse and movement inputs, if not called, the player will not be able to move
	public static void resetInputs() {
		if (resetMovementInput != null) {
			mc.thePlayer.movementInput = resetMovementInput;
			resetMovementInput = null;
		}
		useItemKeyJustPressed = resetUseItemKey != null;
		if (resetUseItemKey != null) {
			mc.gameSettings.keyBindUseItem = resetUseItemKey;
			resetUseItemKey = null;
		}
	}
	
	//Forces a right click
	public static void forcePlaceBlock(EntityPlayer player) {
		mc.gameSettings.keyBindUseItem = new InteractAlways(mc.gameSettings.keyBindUseItem.getKeyDescription(), mc.gameSettings.keyBindUseItem.getKeyCode(), mc.gameSettings.keyBindUseItem.getKeyCategory(), !useItemKeyJustPressed);
	}
	
	//Forces a MovementInput
	public static void overrideMovement(MovementInput i) {
		if (resetMovementInput == null) {
			resetMovementInput = mc.thePlayer.movementInput;
		}
		mc.thePlayer.movementInput = i;
	}
}
