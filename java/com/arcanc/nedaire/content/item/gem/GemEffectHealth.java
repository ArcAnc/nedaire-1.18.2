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

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GemEffectHealth extends GemEffect 
{

	private int healthAmount;
	
	public GemEffectHealth(Color color, int healthAmount) 
	{
		super(color, "health");
		
		this.healthAmount = healthAmount;
	}

	@Override
	protected boolean isTicker() 
	{
		return false;
	}

	@Override
	protected void tick(ItemStack stack, Level level, Player player) 
	{
		
	}

	@Override
	protected void applyInstateousEffect(ItemStack stack, Level level, Player player) 
	{
		if (player != null)
		{
			AttributeInstance instance = player.getAttribute(Attributes.MAX_HEALTH);
			if (instance != null)
			{
				AttributeModifier modifier = new AttributeModifier(getId(), getDescriptionId(), healthAmount, AttributeModifier.Operation.ADDITION);
				instance.addPermanentModifier(modifier);
			}
		}
	}

	@Override
	protected void removeInstanteousEffect(ItemStack stack, Level level, Player player) 
	{
		if (player != null)
		{
			AttributeInstance instance = player.getAttribute(Attributes.MAX_HEALTH);
			if (instance != null)
			{
				instance.removePermanentModifier(getId());
			}
		}
	}

}
