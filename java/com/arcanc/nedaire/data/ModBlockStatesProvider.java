/**
 * @author ArcAnc
 * Created at: 2022-03-31
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.data;

import com.arcanc.nedaire.content.material.ModMaterial;
import com.arcanc.nedaire.content.registration.ModRegistration;
import com.arcanc.nedaire.util.database.ModDatabase;
import com.arcanc.nedaire.util.helpers.StringHelper;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider 
{

	public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) 
	{
		super(gen, ModDatabase.MOD_ID, exFileHelper);
	}

	
	@Override
	protected void registerStatesAndModels() 
	{
		ModMaterial mat = ModRegistration.RegisterMaterials.CORIUM;
		
		registerSimpleBlock (mat.getStorageBlock().get());
		
		if (mat.requiredOre())
		{
			registerOreBlock(mat.getOreBlock().get());	
			registerSimpleBlock (mat.getRawStorageBlock().get());
			registerDeepslateOreBlock(mat.getDeepSlateOre().get());
		}
		
		registerSimpleBlock(ModRegistration.RegisterBlocks.SKYSTONE.get());
		
		registerPedestal(ModRegistration.RegisterBlocks.PEDESTAL.get());
	}
	
	private void registerSimpleBlock (Block block)
	{
		ModelFile model = models().
				withExistingParent(blockPrefix(name(block)), mcLoc(blockPrefix("cube_all"))).
				texture("all", blockTexture(block)).
				texture("particle", blockTexture(block));
		
		getVariantBuilder(block).partialState().addModels(new ConfiguredModel(model));
		
		itemModels().getBuilder(itemPrefix(name(block))).
			parent(model);
	}

	private void registerOreBlock(Block block)
	{
		ModelFile model = models().
				withExistingParent(blockPrefix(name(block)), mcLoc(blockPrefix("block"))).
				texture("ore", blockTexture(block)).
				texture("back", mcLoc(blockPrefix("stone"))).
				texture("particle", mcLoc(blockPrefix("stone"))).
				element().
					cube("#back").
					end().
				element().
					cube("#ore").
					end();
		
		getVariantBuilder(block).partialState().addModels(new ConfiguredModel(model));

		itemModels().getBuilder(itemPrefix(name(block))).
			parent(model);
	}

	private void registerDeepslateOreBlock(Block block)
	{
		ModelFile model = models().
				withExistingParent(blockPrefix(name(block)), mcLoc(blockPrefix("block"))).
				texture("ore", blockTexture(block)).
				texture("back", mcLoc(blockPrefix("deepslate"))).
				texture("particle", mcLoc(blockPrefix("deepslate"))).
				element().
					cube("#back").
					end().
				element().
					cube("#ore").
					end();
		
		getVariantBuilder(block).partialState().addModels(new ConfiguredModel(model));

		itemModels().getBuilder(itemPrefix(name(block))).
			parent(model);
	}
	
	private void registerPedestal(Block block) 
	{
		ModelFile model = models().
				withExistingParent(blockPrefix(name(block)), mcLoc("block")).
				texture("side", StringHelper.getLocFStr(blockPrefix(ModDatabase.Blocks.BlockEntities.Names.PEDESTAL + "/" + ModDatabase.Blocks.BlockEntities.Names.PEDESTAL + "_side"))).
				texture("top", StringHelper.getLocFStr(blockPrefix(ModDatabase.Blocks.BlockEntities.Names.PEDESTAL + "/" + ModDatabase.Blocks.BlockEntities.Names.PEDESTAL + "_top"))).
				texture("particle", StringHelper.getLocFStr(blockPrefix(ModDatabase.Blocks.BlockEntities.Names.PEDESTAL + "/" + ModDatabase.Blocks.BlockEntities.Names.PEDESTAL + "_side"))).
				element().
					from(0, 0, 0).
					to(16, 4, 16).
						face(Direction.DOWN).
							texture("#top").
							cullface(Direction.DOWN).
							end().
						face(Direction.UP).
							texture("#top").
							end().
						face(Direction.NORTH).
							texture("#side").
							cullface(Direction.NORTH).
							end().
						face(Direction.SOUTH).
							texture("#side").
							cullface(Direction.SOUTH).
							end().
						face(Direction.WEST).
							texture("#side").
							cullface(Direction.WEST).
							end().
						face(Direction.EAST).
							texture("#side").
							cullface(Direction.EAST).
							end().
					end().
				element().
					from(2, 12, 2).
					to(14, 16, 14).
						face(Direction.DOWN).
							texture("#top").
							end().
						face(Direction.UP).
							texture("#top").
							end().
						face(Direction.NORTH).
							texture("#side").
							end().
						face(Direction.SOUTH).
							texture("#side").
							end().
						face(Direction.WEST).
							texture("#side").
							end().
						face(Direction.EAST).
							texture("#side").
							end().
					end().
				element().
					from(4, 4, 4).
					to(12, 12, 12).
						face(Direction.DOWN).
							texture("#top").
							end().
						face(Direction.UP).
							texture("#top").
							end().
						face(Direction.NORTH).
							texture("#side").
							end().
						face(Direction.SOUTH).
							texture("#side").
							end().
						face(Direction.WEST).
							texture("#side").
							end().
						face(Direction.EAST).
							texture("#side").
							end().
					end();
	
		getVariantBuilder(block).partialState().addModels(new ConfiguredModel(model));
		
		itemModels().getBuilder(itemPrefix(name(block))).
			parent(model);
	}

	
	private String itemPrefix(String str)
	{
		return ModelProvider.ITEM_FOLDER + "/" + str;
	}
	
	private String blockPrefix(String str)
	{
		return ModelProvider.BLOCK_FOLDER + "/" + str;
	}
	
    private String name(Block block) 
    {
        return block.getRegistryName().getPath();
    }
	
	@Override
	public String getName() 
	{
		return "Nedaire Block States";
	}


}
