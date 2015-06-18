package com.ferdz.craftbot.util;

import net.minecraft.block.Block;

public class BlockPos extends Pos {

	public Block block;
	public int metadata;
	
	public BlockPos(int x, int y, int z, Block block, int meta) {
		super(x, y, z);
		this.block = block;
		this.metadata = meta;
	}
	
}
