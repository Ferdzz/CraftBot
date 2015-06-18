package com.ferdz.craftbot.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PosEntity extends Entity {

	public PosEntity(World p_i1582_1_, double x, double y, double z) {
		super(p_i1582_1_);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub
		
	}
	
}
