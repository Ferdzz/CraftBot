package com.ferdz.craftbot;

import java.io.File;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

import com.ferdz.craftbot.command.CBCommand;
import com.ferdz.craftbot.keybind.KeyBindHandler;
import com.ferdz.craftbot.keybind.KeyBindings;
import com.ferdz.craftbot.player.PlayerHandler;
import com.ferdz.craftbot.render.TesselatorRenderer;
import com.ferdz.craftbot.util.Schematic;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = CraftBot.MODID, version = CraftBot.VERSION)
public class CraftBot {
	public static final String MODID = "CraftBot";
	public static final String VERSION = "0.1";

	public static File dir;
	
	public static boolean bot = false;
	
	public static Schematic currentSchematic;
	
	@Instance
	CraftBot instance;
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Started loading " + MODID + " version " + VERSION);
		
		dir = new File("mods/CraftBot");
		if(!dir.exists())
			dir.mkdir();
		
		MinecraftForge.EVENT_BUS.register(new PlayerHandler());
		MinecraftForge.EVENT_BUS.register(new TesselatorRenderer());
		ClientCommandHandler.instance.registerCommand(new CBCommand());
		KeyBindings.init();
		FMLCommonHandler.instance().bus().register(new KeyBindHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Loaded " + MODID + " version " + VERSION + " correctly");
	}
}
