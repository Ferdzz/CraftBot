package com.ferdz.craftbot.util;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;

public class PathThread extends Thread {
	ICommandSender command;
	
	public PathThread(ICommandSender command) {
		this.command = command;
	}
	
	@Override
	public void run() {
		PathFinder pathFinder = new PathFinder(command.getEntityWorld(), false, true, true, true);
		PathEntity path = pathFinder.createEntityPathTo(Minecraft.getMinecraft().thePlayer, (int) Minecraft.getMinecraft().thePlayer.posX + 2, (int) Minecraft.getMinecraft().thePlayer.posY, (int) Minecraft.getMinecraft().thePlayer.posZ, 100f);
		while (!path.isFinished()) {
			PathPoint point = path.getPathPointFromIndex(path.getCurrentPathIndex());
			Minecraft.getMinecraft().thePlayer.moveEntity(point.xCoord - Minecraft.getMinecraft().thePlayer.posX, point.yCoord - Minecraft.getMinecraft().thePlayer.posY, point.zCoord - Minecraft.getMinecraft().thePlayer.posZ + 0.5);
			path.incrementPathIndex();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
