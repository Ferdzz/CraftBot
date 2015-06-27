package com.ferdz.craftbot.keybind;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;

public class KeyBindings {
    public static KeyBinding bot;

    //Registers the bot keybind
    public static void init() {
        bot = new KeyBinding("key.togglebot", Keyboard.KEY_G, "key.categories.movement");
        ClientRegistry.registerKeyBinding(bot);
    }
}
