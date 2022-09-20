/**
 * @author ArcAnc
 * Created at: 2022-06-14
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.item.gem;

import com.arcanc.nedaire.content.item.ItemInterfaces.IGemItem;
import com.arcanc.nedaire.content.item.ModBaseItem;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class GemItem extends ModBaseItem implements IGemItem
{
//	private GemEffect effect;
	
	public GemItem(Properties props) 
	{
		super(props);
//		this.effect = effect;
	}

	@Override
	public GemEffect getEffect() 
	{
		return null;
	}
	
	@Override
	public ItemStack getDefaultInstance() 
	{
		ItemStack stack = new ItemStack(this);
		stack.getOrCreateTag().putInt("effect", GemEffects.HEALTH.getColor().getRGB());
		return stack;
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) 
	{
		if (this.allowdedIn(p_41391_)) 
		{
			for (GemEffect c : GemEffects.effectMap.values())
			{
				p_41392_.add(GemEffects.makeGem(new ItemStack(this), c));
			}
	    }
	}

}
