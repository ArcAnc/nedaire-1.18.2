/**
 * @author ArcAnc
 * Created at: 2022-04-09
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.content.block;

import java.util.function.BiFunction;

import com.arcanc.nedaire.content.block.entities.ModBEPedestal;
import com.arcanc.nedaire.util.helpers.BlockHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModBlockPedestal extends ModTileProviderBlock<ModBEPedestal> 
{
	private final VoxelShape shape = Shapes.or(
			box(0, 0, 0, 16, 4, 16), 
			box(2, 12, 2, 14, 16, 14), 
			box(4, 4, 4, 12, 12, 12));

	public ModBlockPedestal(Properties properties, BiFunction<BlockPos, BlockState, ModBEPedestal> tile) 
	{
		super(properties, tile);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTrace) 
	{
		if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND)
		{
			ItemStack stack = player.getItemInHand(hand);
			ModBEPedestal tile = BlockHelper.castTileEntity(world, pos, ModBEPedestal.class);
			if (tile != null)
			{
				tile.usePedestal(player, stack);
				
				return InteractionResult.CONSUME;
			}
		}
		return InteractionResult.SUCCESS;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) 
	{
		return shape;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) 
	{
		return shape;
	}
	
	@Override
	public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) 
	{
		return shape;
	}
}