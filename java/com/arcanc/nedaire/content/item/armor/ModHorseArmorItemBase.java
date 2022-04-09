/**
 * @author ArcAnc
 * Created at: 2022-03-31
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.item.armor;

import com.arcanc.nedaire.Nedaire;
import com.arcanc.nedaire.content.material.armor.horse.ModHorseArmorMaterial;

import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;

public class ModHorseArmorItemBase extends HorseArmorItem
{
	public ModHorseArmorItemBase(ModHorseArmorMaterial material) 
	{
		super(material.getDefense(), material.getTexturePath(), new Item.Properties().tab(Nedaire.getInstance().TAB));
	}

	@Override
	public String getDescriptionId() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

}
