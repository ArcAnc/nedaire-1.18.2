/**
 * @author ArcAnc
 * Created at: 2022-06-14
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.item.gem;

import java.awt.Color;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GemEffectRegen extends GemEffect 
{
	
	private static final String LAST_TICK = "last_tick";
	
	/**
	 * In ticks
	 */
	private int period;
	private int healPower;
	
	
	public GemEffectRegen(Color color, int period, int power) 
	{
		super(color, "regeneration");
		
		this.period = period;
		this.healPower = power;
	}
	
	public GemEffectRegen(Color color) 
	{
		this(color, 200, 1);
	}

	@Override
	protected boolean isTicker() 
	{
		return true;
	}

	@Override
	protected void tick(ItemStack stack, Level level, Player player) 
	{
		if (!stack.isEmpty() && level != null && player != null && player.isAlive())
		{
			CompoundTag tag = stack.getOrCreateTag();
			long lastTick = tag.getLong(LAST_TICK);
			long curTime = level.getGameTime();
			if (lastTick + period <= curTime)
			{
				if (player.getHealth() < player.getMaxHealth())
				{
					player.heal(healPower);
				}
			
			tag.putLong(LAST_TICK, curTime);
			}
		}
	}

	@Override
	protected void applyInstateousEffect(ItemStack stack, Level level, Player player) 
	{
	}

	@Override
	protected void removeInstanteousEffect(ItemStack stack, Level level, Player player) 
	{
		
	}

}
