package com.xorinc.portalbreaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class PortalUtil {
	
	private static final Set<BlockFace> PLANE_FACES = Collections.unmodifiableSet(EnumSet.of(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST));
	
	
	public static List<Block> getPortals(Location loc){		
		Block startingBlock = loc.getWorld().getBlockAt(loc);
		
		if(startingBlock.getType() != Material.ENDER_PORTAL){
			return null;
		}
		
		List<Block> blocks = new ArrayList<Block>();
		
		for(Block block : getSurroundingBlocks(startingBlock, 2)){
			if(block.getType() == Material.ENDER_PORTAL)
				blocks.add(block);
		}
		
		return blocks;
	}
	
	public static List<Block> getPortalsFromFrame(Location loc){
		Block startingBlock = loc.getWorld().getBlockAt(loc);
		
		for(BlockFace facing : PLANE_FACES){
			List<Block> portals = getPortals(startingBlock.getRelative(facing).getLocation());
			if(portals != null)
				return portals;
		}
		
		return null;

	}
	
	public static Block[] getSurroundingBlocks(Block block, int radius){
		Block[] blocks = new Block[(radius * 2 + 1)*(radius * 2 + 1)];
		Location loc = block.getLocation();
		int[] locs = new int[radius * 2 + 1];
		for(int i = 0; i < locs.length; i++){
			locs[i] = radius - i;
		}
		int c = 0;
		for(int i : locs)
			for(int j : locs){
				blocks[c] =  block.getWorld().getBlockAt(loc.getBlockX() + i, loc.getBlockY(), loc.getBlockZ() + j);
				c++;
			}
		
		return blocks;
	}

	public static boolean isNextToPortal(Location loc){
		
		Block block = loc.getWorld().getBlockAt(loc);
		
		for(BlockFace facing : PLANE_FACES){
			if(block.getRelative(facing).getType() == Material.ENDER_PORTAL)
				return true;
		}
		
		return false;
	}
	
}
