/**
 * @author ArcAnc
 * Created at: 2022-03-31
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.item;

import com.arcanc.nedaire.content.item.gem.GemEffect;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemInterfaces 
{
	public interface ICustomModelProperties
	{
		void registerModelProperties(Item item);
		
	}
	
	public interface IGemItem
	{
		GemEffect getEffect();
	}
	
	public interface GemAction 
	{
		void action (ItemStack stack, Level level, Player player); 
	}
}
