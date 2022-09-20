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
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class GemEffects 
{
	public static final Map<Color, GemEffect> effectMap = Maps.newHashMap();
	
	public static final GemEffectRegen REGENERATION = new GemEffectRegen(Color.GREEN);
	public static final GemEffectHealth HEALTH = new GemEffectHealth(Color.RED, 3);
	
	public static ItemStack makeGem (ItemStack stack, GemEffect effect)
	{
		stack.getOrCreateTag().putInt("effect", effect.getColor().getRGB());
		
		return stack;
	}
	
	public static int getColor (ItemStack stack)
	{
		CompoundTag compoundtag = stack.getTag();
	    if (compoundtag != null && compoundtag.contains("effect", 99)) 
	    {
	    	return compoundtag.getInt("effect");
	    }
	    else
	    {
	    	return 16253176;
	    }
	}
}
