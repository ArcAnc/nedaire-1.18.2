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

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;

public class ModShovelBase extends ShovelItem 
{
	public ModShovelBase(ModAbstractToolMaterial toolMat) 
	{
		super(toolMat, toolMat.getAttackDamageBonus(), toolMat.getAttackSpeed(), new Item.Properties().tab(Nedaire.getInstance().TAB));
	}
	
	@Override
	public String getDescriptionId() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

}
