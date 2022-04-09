/**
 * @author ArcAnc
 * Created at: 2022-03-31
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.item;

import net.minecraft.world.item.Item;

public class ItemInterfaces 
{
	public interface ICustomModelProperties
	{
		void registerModelProperties(Item item);
		
	}
}
