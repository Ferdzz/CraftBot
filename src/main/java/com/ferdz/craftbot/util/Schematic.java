package com.ferdz.craftbot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;

public class Schematic {
	public ArrayList<BlockPos> blockPos;
	public int offsetX;
	public int offsetY;
	public int offsetZ;
	
	public Schematic(File file) {		
		try {
			blockPos = new ArrayList<BlockPos>();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] info = line.split(",");
				blockPos.add(new BlockPos(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]), GameRegistry.findBlock(info[3], info[4]), Integer.parseInt(info[5])));
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
