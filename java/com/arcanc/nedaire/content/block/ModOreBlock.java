/**
 * @author ArcAnc
 * Created at: 2022-03-31
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.block;

import com.arcanc.nedaire.content.block.BlockInterfaces.IBlockRenderLayer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.ticks.ScheduledTick;

public class ModOreBlock extends OreBlock implements SimpleWaterloggedBlock, IBlockRenderLayer
{
	private RenderType renderLayer = null;
	
	public ModOreBlock(Properties properties) 
	{
		this(properties, UniformInt.of(0, 0));
	}
	
	public ModOreBlock(Properties properties, UniformInt range) 
	{
		super(properties, range);
	
		setRenderLayer(RenderType.cutout());
	}
	
	@Override
	public void fillItemCategory (CreativeModeTab group, NonNullList<ItemStack> list) 
	{
		list.add(new ItemStack(this, 1));	
	}
	
	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventId, int eventParam) 
	{
		if (world.isClientSide() && eventId == 255)
		{
			world.sendBlockUpdated(pos, state, state, 3);
			return true;
		}
		return super.triggerEvent(state, world, pos, eventId, eventParam);
	}
	
	@Override
	public String getDescriptionId() 
	{
		return getRegistryName().toString().replace(':', '.').replace(':', '.').replace('/', '.');
	}
	
	@Override
	public RenderType getRenderLayer() 
	{
		return renderLayer;
	}
	
	@Override
	public void setRenderLayer(RenderType renderLayer) 
	{
		this.renderLayer = renderLayer;	
	}
	
	/**
	 * WATERLOGGING
	 */
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) 
	{
		BlockState state = this.defaultBlockState();
		if (state.hasProperty(BlockStateProperties.WATERLOGGED))
		{
			return state.setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
		}
		return state;
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction dir, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) 
	{
		if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED))
		{
			world.getFluidTicks().schedule(new ScheduledTick<>(Fluids.WATER, currentPos, Fluids.WATER.getTickDelay(world), 0));
		}
		return super.updateShape(state, dir, facingState, world, currentPos, facingPos);
	}
	
	@Override
	public FluidState getFluidState(BlockState state) 
	{
		if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED))
		{
			return Fluids.WATER.getSource(false);
		}
		return super.getFluidState(state);
	}
	
	@Override
	public boolean canPlaceLiquid(BlockGetter world, BlockPos pos, BlockState state, Fluid liquid) 
	{
		return state.hasProperty(BlockStateProperties.WATERLOGGED) && SimpleWaterloggedBlock.super.canPlaceLiquid(world, pos, state, liquid);
	}
	
	@Override
	public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState liquid) 
	{
		return state.hasProperty(BlockStateProperties.WATERLOGGED) && SimpleWaterloggedBlock.super.placeLiquid(world, pos, state, liquid);
	}

	@Override
	public ItemStack pickupBlock(LevelAccessor world, BlockPos pos, BlockState state) 
	{
		if (state.hasProperty(BlockStateProperties.WATERLOGGED))
		{
			return SimpleWaterloggedBlock.super.pickupBlock(world, pos, state);
		}
		return ItemStack.EMPTY;
	}
}
