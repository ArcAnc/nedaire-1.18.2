/**
 * @author ArcAnc
 * Created at: 2022-04-25
 * Copyright (c) 2022
 * 
 * This code is licensed under "Ancient's License of Common Sense"	
 * Details can be found in the license file in the root folder of this project
 */
package com.arcanc.nedaire.util.helpers;

import com.arcanc.nedaire.content.capabilities.vim.CapabilityVim;
import com.arcanc.nedaire.content.capabilities.vim.IVim;

import net.minecraftforge.common.capabilities.Capability;

public class VimHelper 
{
	public static Capability<IVim> vimHandler = CapabilityVim.VIM;

}
