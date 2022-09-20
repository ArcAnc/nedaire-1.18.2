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
import java.util.UUID;

import javax.annotation.Nonnull;

import com.arcanc.nedaire.util.helpers.StringHelper;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class GemEffect 
{
	private UUID id;
	private String descriptionId;
	private final Color color;
	private final String name;
	
	public GemEffect(@Nonnull Color color, String name) 
	{
		this.id = UUID.randomUUID();
		this.color = color;
		this.name = name;
	}

	public UUID getId() 
	{
		return id;
	}

	public Color getColor() 
	{
		return color;
	}
	
	public String getName() 
	{
		return name;
	}
	
	protected abstract boolean isTicker();
	protected abstract void tick (ItemStack stack, Level level, Player player);
	protected abstract void applyInstateousEffect(ItemStack stack, Level level, Player player);
	protected abstract void removeInstanteousEffect(ItemStack stack, Level level, Player player);
	
	protected String getOrCreateDescriptionId() 
	{
		if (this.descriptionId == null) 
		{
			this.descriptionId = StringHelper.getStrLocFStr("gem_effect." + name).replace(':', '.');
		}
		return this.descriptionId;
	}

	public String getDescriptionId() 
	{
		return this.getOrCreateDescriptionId();
	}
	
	public Component getDisplayName() 
	{
		return new TranslatableComponent(this.getDescriptionId());
	}
}
