/**
 * @author ArcAnc
 * Created at: 2022-03-30
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire;

import java.awt.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arcanc.nedaire.content.block.BlockInterfaces.IBlockRenderLayer;
import com.arcanc.nedaire.content.capabilities.vim.CapabilityVim;
import com.arcanc.nedaire.content.container.ModSlot;
import com.arcanc.nedaire.content.item.ItemInterfaces.ICustomModelProperties;
import com.arcanc.nedaire.content.item.gem.GemEffects;
import com.arcanc.nedaire.content.item.gem.GemItem;
import com.arcanc.nedaire.content.item.weapon.ModShieldBase;
import com.arcanc.nedaire.content.itemGroup.ModItemGroup;
import com.arcanc.nedaire.content.material.ModMaterial;
import com.arcanc.nedaire.content.registration.ModRegistration;
import com.arcanc.nedaire.content.renderer.blockEntity.HolderRenderer;
import com.arcanc.nedaire.content.renderer.blockEntity.PedestalRenderer;
import com.arcanc.nedaire.content.renderer.item.shieldRenderer.ShieldTileEntityRenderer;
import com.arcanc.nedaire.data.ModBlockLootProvider;
import com.arcanc.nedaire.data.ModBlockStatesProvider;
import com.arcanc.nedaire.data.ModBlockTagsProvider;
import com.arcanc.nedaire.data.ModItemModelProvider;
import com.arcanc.nedaire.data.ModItemTagsProvider;
import com.arcanc.nedaire.data.crafting.ModRecipeProvider;
import com.arcanc.nedaire.data.language.ModEnUsLangProvider;
import com.arcanc.nedaire.util.database.ModDatabase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod(ModDatabase.MOD_ID)
public class Nedaire 
{

    private static Nedaire instance;
	
	private static final Logger LOGGER = LogManager.getLogger(ModDatabase.MOD_ID);

	public final CreativeModeTab TAB;
	
	public Nedaire ()
	{
		instance = this;
		
		TAB = new ModItemGroup(ModDatabase.ItemGroups.Main.MAIN);
		
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModRegistration.RegisterBlocks.BLOCKS.register(modEventBus);
	    ModRegistration.RegisterItems.ITEMS.register(modEventBus);
	    ModRegistration.RegisterMaterials.init();
	    ModRegistration.RegisterBlockEntities.BLOCK_ENTITIES.register(modEventBus);
	    ModRegistration.RegisterRecipes.RECIPE_SERIALIZERS.register(modEventBus);
	    ModRegistration.RegisterWorldGen.FEATURES.register(modEventBus);
		
	    modEventBus.addListener(this :: serverSetup);
	    modEventBus.addListener(this :: clientSetup);
	    modEventBus.addListener(this :: clientTextureStitch);
	    modEventBus.addListener(this :: registerItemColors);
	    modEventBus.addListener(this :: registerReloadListeners);
	    modEventBus.addListener(this :: registerCapability);
	    
	    modEventBus.addListener(this :: gatherData);
	    
	    GemEffects.effectMap.putIfAbsent(GemEffects.HEALTH.getColor(), GemEffects.HEALTH);
	    GemEffects.effectMap.putIfAbsent(GemEffects.REGENERATION.getColor(), GemEffects.REGENERATION);
	}
	
	private void serverSetup(final FMLCommonSetupEvent event)
    {

    }

	private void registerCapability(final RegisterCapabilitiesEvent event )
	{
		CapabilityVim.register(event);
	}
	
	private void clientSetup (final FMLClientSetupEvent event)
	{
		event.enqueueWork(() ->
		{
			registerBlocksRenderers();
			
			registerModelsProperties();

			registerBlockEntityRenderers();
		});
	}
	
	private void registerBlockEntityRenderers() 
	{
		BlockEntityRenderers.register(ModRegistration.RegisterBlockEntities.BE_PEDESTAL.get(), PedestalRenderer :: new);	
		BlockEntityRenderers.register(ModRegistration.RegisterBlockEntities.BE_HOLDER.get(), HolderRenderer :: new);	
	}
	
	private void registerBlocksRenderers()
	{
		ModRegistration.RegisterBlocks.BLOCKS.getEntries().
		stream().
		map(RegistryObject :: get).
		forEach(block ->
		{
			if (block instanceof IBlockRenderLayer b)
			{
				ItemBlockRenderTypes.setRenderLayer(block, b.getRenderLayer());
			}
		});		
	}
	
	private void registerModelsProperties() 
	{
		ModRegistration.RegisterItems.ITEMS.getEntries().
		stream().
		map(RegistryObject :: get).
		forEach(item -> 
		{
			if (item instanceof ICustomModelProperties modProps)
			{
				modProps.registerModelProperties(item);
			}
		});
	}
	
	private void registerReloadListeners(final RegisterClientReloadListenersEvent event)
	{
		event.registerReloadListener(ModShieldBase.shieldRenderer = new ShieldTileEntityRenderer(
				Minecraft.getInstance().getBlockEntityRenderDispatcher(), 
				Minecraft.getInstance().getEntityModels()));
	}
	
	
	private void clientTextureStitch (final TextureStitchEvent.Pre event)
	{
		if (event.getAtlas().location().equals(InventoryMenu.BLOCK_ATLAS))
		{
			ModMaterial mat = ModRegistration.RegisterMaterials.CORIUM;
			event.addSprite(mat.getToolMat().getShieldBase().texture());
			event.addSprite(mat.getToolMat().getShieldNoPattern().texture());
		
			/**
			 * Slots
			 */
			
			event.addSprite(ModSlot.BACKGROUND_STANDART);
			event.addSprite(ModSlot.BACKGROUND_INPUT);
			event.addSprite(ModSlot.BACKGROUND_OUPUT);
			event.addSprite(ModSlot.BACKGROUND_BOTH);
		}
	}

	private void registerItemColors(final ColorHandlerEvent.Item event)
	{
		event.getItemColors().register((stack, overlay) -> 
		{
			if (overlay == 0 && stack.getItem() instanceof GemItem)
			{
				return overlay > 0 ? -1 : GemEffects.getColor(stack);
			}
			return -1;
		}, ModRegistration.RegisterItems.GEM.get());
	}
	
    public void gatherData(GatherDataEvent event)
    {
    	
    	ExistingFileHelper ext = event.getExistingFileHelper();
    	DataGenerator gen = event.getGenerator();
        
    	if (event.includeServer())
    	{
            gen.addProvider(new ModBlockLootProvider(gen));
        	ModBlockTagsProvider btp = new ModBlockTagsProvider(gen, ext);
    		
    		gen.addProvider(btp);
            gen.addProvider(new ModItemTagsProvider(gen, btp, ext));    	
            gen.addProvider(new ModRecipeProvider(gen));
/*            gen.addProvider(new NedaireMultiblockProvider(gen));
 */   	}
    	
    	
    	if (event.includeClient())
    	{
    		gen.addProvider(new ModEnUsLangProvider(gen));
            gen.addProvider(new ModItemModelProvider(gen, ext));
            gen.addProvider(new ModBlockStatesProvider(gen, ext));

/*            
            gen.addProvider(new NedaireSoundsProvider(gen, ext));
            gen.addProvider(new NedaireParticleProvider(gen));
*/    	}
    }
    
    
	public static Nedaire getInstance()
	{
		return instance;
	}
	
	/**
	 * @return the logger
	 */
	public static Logger getLogger() 
	{
		return LOGGER;
	}
}
