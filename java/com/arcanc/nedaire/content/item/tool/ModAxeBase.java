/**
 * @author ArcAnc
 * Created at: 2022-03-30
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.item.tool;

import com.arcanc.nedaire.Nedaire;
import com.arcanc.nedaire.content.material.tool.ModAbstractToolMaterial;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class ModAxeBase extends AxeItem 
{

	public ModAxeBase(ModAbstractToolMaterial toolMat, float attackDamage, float attackSpeed)  
	{
		super(toolMat, attackDamage, attackSpeed, new Item.Properties().tab(Nedaire.getInstance().TAB));
	}

	@Override
	public String getDescriptionId() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

}
