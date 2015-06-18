package com.ferdz.craftbot.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovementInput;

import org.lwjgl.util.vector.Vector3f;

import com.ferdz.craftbot.CraftBot;
import com.ferdz.craftbot.player.ForcePlayer;
import com.ferdz.craftbot.util.PathThread;
import com.ferdz.craftbot.util.Schematic;

import cpw.mods.fml.common.registry.GameRegistry;

public class CBCommand implements ICommand {
	
	@Override
	public void processCommand(ICommandSender command, String[] arg) {
		if (!(command instanceof EntityPlayer)) {
			command.addChatMessage(new ChatComponentText("Need to be a player to use this command"));
			return;
		} else if (arg.length == 0) {
			command.addChatMessage(new ChatComponentText("Invalid arguments"));
			return;
		}
		
		// Actual command
		EntityPlayer player = (EntityPlayer) command;
		if (arg[0].equalsIgnoreCase("pos1")) {
			player.getEntityData().setInteger("CBpos1X", (int) player.posX);
			player.getEntityData().setInteger("CBpos1Y", (int) (player.posY - player.getYOffset()));
			player.getEntityData().setInteger("CBpos1Z", (int) player.posZ);
			player.getEntityData().setBoolean("CBhaspos1", true);
		} else if (arg[0].equalsIgnoreCase("pos2")) {
			player.getEntityData().setInteger("CBpos2X", (int) player.posX);
			player.getEntityData().setInteger("CBpos2Y", (int) (player.posY - player.getYOffset()));
			player.getEntityData().setInteger("CBpos2Z", (int) player.posZ);
			player.getEntityData().setBoolean("CBhaspos2", true);
		} else if (arg[0].equalsIgnoreCase("save")) {
			if (!player.getEntityData().getBoolean("CBhaspos1") || !player.getEntityData().getBoolean("CBhaspos2")) {
				command.addChatMessage(new ChatComponentText("Please set pos1 and pos2 first"));
				return;
			}
			if (arg.length == 2)
				save(new Vector3f(player.getEntityData().getInteger("CBpos1X"), player.getEntityData().getInteger("CBpos1Y"), player.getEntityData().getInteger("CBpos1Z")), new Vector3f(player.getEntityData().getInteger("CBpos2X"), player.getEntityData().getInteger("CBpos2Y"), player.getEntityData().getInteger("CBpos2Z")), arg[1], command);
			else
				command.addChatMessage(new ChatComponentText("Usage: /craftbot save <file>"));
		} else if (arg[0].equalsIgnoreCase("place")) {
			if (arg.length == 2)
				place(new Vector3f((int) player.posX, (int) (player.posY - player.getYOffset()), (int) player.posZ), arg[1], command);
			else
				command.addChatMessage(new ChatComponentText("Usage: /craftbot place <file>"));
		} else if (arg[0].equalsIgnoreCase("togglebot")) {
			CraftBot.bot = !CraftBot.bot;
		} else if(arg[0].equalsIgnoreCase("path")) {
			new PathThread(command).start();
		} else {
			command.addChatMessage(new ChatComponentText("Command not recognized"));
		}
	}
	
	private void place(Vector3f pos, String name, ICommandSender command) {
		CraftBot.currentSchematic = new Schematic(new File("mods/CraftBot/" + name + ".txt"));
	}
	
	private void save(Vector3f pos1, Vector3f pos2, String name, ICommandSender command) {
		Vector3f min = new Vector3f(Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y), Math.min(pos1.z, pos2.z));
		Vector3f max = new Vector3f(Math.max(pos1.x, pos2.x), Math.max(pos1.y, pos2.y), Math.max(pos1.z, pos2.z));
		
		File file = new File(CraftBot.dir.getAbsolutePath() + "/" + name + ".txt");
		if (file.exists()) {
			command.addChatMessage(new ChatComponentText("File already exists, canceling"));
			return;
		}
		
		try {
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			for (float x = min.x; x <= max.x; x++) {
				for (float y = min.y; y <= max.y; y++) {
					for (float z = min.z; z <= max.z; z++) {
						Block block = command.getEntityWorld().getBlock((int) x, (int) y, (int) z);
						writer.write((int) (x - min.x) + "," + (int) (y - min.y) + "," + (int) (z - min.z) + "," + GameRegistry.findUniqueIdentifierFor(block).modId + "," + GameRegistry.findUniqueIdentifierFor(block).name + "," + command.getEntityWorld().getBlockMetadata((int) x, (int) y, (int) z));
						writer.newLine();
					}
				}
			}
			writer.close();
			command.addChatMessage(new ChatComponentText("File " + file.getName() + " has been created without errors"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public String getCommandName() {
		return "CraftBot";
	}
	
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return null;
	}
	
	@Override
	public List<String> getCommandAliases() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("cb");
		list.add("craftbot");
		return list;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
	
	@Override
	public List<String> addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return null;
	}
	
	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}
	
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
}
