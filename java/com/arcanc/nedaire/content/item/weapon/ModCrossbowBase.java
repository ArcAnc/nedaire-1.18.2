/**
 * @author ArcAnc
 * Created at: 2022-03-31
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.item.weapon;

import com.arcanc.nedaire.Nedaire;
import com.arcanc.nedaire.content.item.ItemInterfaces.ICustomModelProperties;
import com.arcanc.nedaire.content.material.tool.ModAbstractToolMaterial;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCrossbowBase extends CrossbowItem implements ICustomModelProperties
{
	protected final ModAbstractToolMaterial material;
	
	public ModCrossbowBase(ModAbstractToolMaterial toolMat) 
	{
		super(new Item.Properties().tab(Nedaire.getInstance().TAB).defaultDurability(toolMat.getUses()));
		
		this.material = toolMat;
	}
	
	@Override
	public boolean useOnRelease(ItemStack stack) 
	{
		return stack.getItem() instanceof CrossbowItem;
	}
	
	@Override
	public String getDescriptionId() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

	@Override
	public void registerModelProperties(Item item) 
	{
		ItemProperties.register(item, 
				   new ResourceLocation("pull"), (stack, world, living, integer) -> 
		   {
			   return living != null && 
					  !CrossbowItem.isCharged(stack) ? (living.getTicksUsingItem()) / (float)CrossbowItem.getChargeDuration(stack) : 0.0f;
		   });
			ItemProperties.register(item, 
				   new ResourceLocation("pulling"), (stack, world, living, integer) -> 
		   {
			   return living != null && 
					  living.isUsingItem() && 
					  living.getUseItem() == stack && 
					  !CrossbowItem.isCharged(stack) ? 1.0f : 0.0f;
		   });

			ItemProperties.register(item, 
				   new ResourceLocation("charged"), (stack, world, living, integer) ->
		   {
			   return living != null && 
					  CrossbowItem.isCharged(stack) ? 1.0f : 0.0f;
		   });
		   
			ItemProperties.register(item, 
				   new ResourceLocation("firework"), (stack, world, living, integer) -> 
		   {
			   return living != null && 
					  CrossbowItem.isCharged(stack) && 
					  CrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0f : 0.0f ;
		   });
		
	}
}
