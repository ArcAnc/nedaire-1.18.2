/**
 * @author ArcAnc
 * Created at: 2022-03-30
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.util.database;

import com.arcanc.nedaire.util.helpers.StringHelper;

public class ModDatabase 
{
	public static final String MOD_ID = "nedaire";
	public static final String VERSION = "${version}";

	public static class ItemGroups
	{
		public static class Main
		{
			public static final String MAIN = "main";
		}
		public static final String BACKGROUND_IMAGE_PATH = "textures/gui/itemgroups/";
	}
	
	public static class Materials
	{
		public static final String CORIUM = "corium";
	}
	
	public static class Items
	{
		public static class Names
		{
			public static final String INGOT = "ingot";
			public static final String NUGGET = "nugget";
			public static final String DUST = "dust";
			public static final String RAW = "raw";
			public static final String DEEPSLATE = "deepslate";
			
			public static final String TOOL = "tool";
			public static final String WEAPON = "weapon";
			public static final String ARMOR = "armor";
			public static final String PLAYER_ARMOR = "player";
			
			public static final String HAMMER = "hammer";
			
			public static class Tools
			{
				public static final String AXE = "axe";
				
				public static final String PICKAXE = "pickaxe";
				public static final String SHOVEL = "shovel";
				public static final String HOE = "hoe";
				public static final String SHEARS = "shears";
				public static final String FISHING_ROD = "fishing_rod";
			}
			
			public static class Armor
			{
				public static final String ARMOR_HORSE = "horse";
				
				public static final String ARMOR_CHEST = "chest";
				public static final String ARMOR_LEGS = "legs";
				public static final String ARMOR_FEET = "feet";
				public static final String ARMOR_HEAD = "head";
			}
			
			public static class Weapon
			{
				public static final String SHIELD = "shield";
				public static final String BOW = "bow";
				public static final String CROSSBOW = "crossbow";
				public static final String SWORD = "sword";
			}
			
		}
	}

	public static class Blocks
	{
		public static class Names
		{
			public static final String STORAGE_BLOCK = "storage_block";
			public static final String ORE = "ore";
			public static final String DEEPSLATE = "deepslate";
			
			public static final String SKYSTONE = "skystone";
		}
		
		public static class BlockEntities
		{
			public static class Names
			{
				public static final String PEDESTAL = "pedestal";
				public static final String HOLDER = "holder";
			}
			
			public static class TagAddress
			{
				public static class Machines
				{
					public static class RedstoneSensitive
					{
						public static final String REDSTONE_MOD = "redstone_mod";
					}
				}
				
			}
			
		}
	}

	public static class Recipes
	{
		
		public static class VanillaTypes
		{
			public static final String SMELTING = "smelting";
			public static final String BLASTING = "blasting";
			public static final String CONVERSION = "conversion";
			public static final String TOOL = "tool";
			public static final String SHIELD_DECORATION = "shield_decoration";
		}
	}

	public static class Capabilities
	{
		public static class ItemHandler
		{
			public static final String SLOTS = "slots";
			public static final String TAG_LOCATION = StringHelper.getStrLocFStr("inventory");
		
			public static class ItemHolder
			{
				public static final String SLOT_LIMIT = "slot_limit";
			}
		}
		
		public static class Vim
		{
			public static final String TAG_LOCATION = StringHelper.getStrLocFStr("vim");
			
			public static final String ENERGY = "vim";
			public static final String MAX_ENERGY = "max_vim";
			public static final String INPUT = "input";
			public static final String EXTRACT = "extracting";
		}
	}

}