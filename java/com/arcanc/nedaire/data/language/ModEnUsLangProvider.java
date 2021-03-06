/**
 * @author ArcAnc
 * Created at: 2022-03-31
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.data.language;

import com.arcanc.nedaire.Nedaire;
import com.arcanc.nedaire.content.material.ModMaterial;
import com.arcanc.nedaire.content.registration.ModRegistration;
import com.arcanc.nedaire.util.database.ModDatabase;
import com.arcanc.nedaire.util.helpers.StringHelper;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider 
{

	public ModEnUsLangProvider(DataGenerator gen) 
	{
		super(gen, ModDatabase.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() 
	{
		add(Nedaire.getInstance().TAB.getDisplayName().getString(), "Nedaire");
		
		ModMaterial mat = ModRegistration.RegisterMaterials.CORIUM;
		
		add(mat.getIngot().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.INGOT));
		add(mat.getNugget().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.NUGGET));
		add(mat.getDust().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.DUST));
		
		add(mat.getPickaxe().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Tools.PICKAXE));
		add(mat.getAxe().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Tools.AXE));
		add(mat.getShovel().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Tools.SHOVEL));
		add(mat.getHoe().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Tools.HOE));
		add(mat.getShears().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Tools.SHEARS));
		add(mat.getFishingRod().get(), StringHelper.capitalize(mat.getName()) + " " + "Fishhing Rod");

		add(mat.getShield().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Weapon.SHIELD));
		add(mat.getSword().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Weapon.SWORD));
		add(mat.getBow().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Weapon.BOW));
		add(mat.getCrossbow().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Weapon.CROSSBOW));

		add(mat.getArmorHorse().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Armor.ARMOR_HORSE) + " " + StringHelper.capitalize(ModDatabase.Items.Names.ARMOR));
		add(mat.getPlayerArmorHead().get(), StringHelper.capitalize(mat.getName()) + " " + "Helmet");
		add(mat.getPlayerArmorChest().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Items.Names.Armor.ARMOR_CHEST + "plate"));
		add(mat.getPlayerArmorLegs().get(), StringHelper.capitalize(mat.getName()) + " " + "Leggings");
		add(mat.getPlayerArmorFeet().get(), StringHelper.capitalize(mat.getName()) + " " + "Boots");
		
		add(mat.getStorageBlock().get(), "Block" + " of " +  StringHelper.capitalize(mat.getName()));
	
		if (mat.requiredOre())
		{
			add(mat.getRaw().get(), StringHelper.capitalize(ModDatabase.Items.Names.RAW) + " " + StringHelper.capitalize(mat.getName())); 
			
			add(mat.getRawStorageBlock().get(), "Block" + " of " +  StringHelper.capitalize(ModDatabase.Items.Names.RAW) + " " + StringHelper.capitalize(mat.getName()));
			
			add(mat.getOreBlock().get(), StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Blocks.Names.ORE));

			add(mat.getDeepSlateOre().get(), StringHelper.capitalize(ModDatabase.Items.Names.DEEPSLATE) + " " + StringHelper.capitalize(mat.getName()) + " " + StringHelper.capitalize(ModDatabase.Blocks.Names.ORE));
		}
		
		add(ModRegistration.RegisterItems.HAMMER.get(), StringHelper.capitalize(ModDatabase.Items.Names.HAMMER));

		add(ModRegistration.RegisterBlocks.SKYSTONE.get().asItem(), StringHelper.capitalize(ModDatabase.Blocks.Names.SKYSTONE));
		
		add(ModRegistration.RegisterBlocks.PEDESTAL.get().asItem(), StringHelper.capitalize(ModDatabase.Blocks.BlockEntities.Names.PEDESTAL));
		add(ModRegistration.RegisterBlocks.HOLDER.get().asItem(), StringHelper.capitalize(ModDatabase.Blocks.BlockEntities.Names.HOLDER));
	}
	
	@Override
	public String getName() 
	{
		return "Nedaire EnUs Provider";
	}

}
