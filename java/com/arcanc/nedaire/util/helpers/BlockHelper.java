/**
 * @author ArcAnc
 * Created at: 2022-04-09
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.util.helpers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BlockHelper 
{
	public static final BlockEntity getTileEntity(Level world, BlockPos pos)
	{
		if (world != null && world.hasChunkAt(pos))
		{
			return world.getBlockEntity(pos);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T castTileEntity(BlockEntity tile, Class<T> to)
	{
		if (tile != null && to.isAssignableFrom(tile.getClass()))
		{
			return (T) tile;
		}
		return null;
	}
	
	public static <T> T castTileEntity(Level world, BlockPos pos, Class<T> to)
	{
		if (world != null && pos != null)
		{
			BlockEntity tile = getTileEntity(world, pos);
			return castTileEntity(tile, to);
		}
		return null;
	}
}
