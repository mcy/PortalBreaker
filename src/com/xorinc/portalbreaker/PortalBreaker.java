package com.xorinc.portalbreaker;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PortalBreaker extends JavaPlugin implements Listener {
	
	public void onEnable(){
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void onBlockBreak(BlockBreakEvent event){
		
		Block block = event.getBlock();
		
		if(block.getWorld().getEnvironment() == Environment.THE_END)
			return;
		
		if(!PortalUtil.isNextToPortal(block.getLocation()))
			return;
		
		List<Block> portals = PortalUtil.getPortalsFromFrame(block.getLocation());
		
		if(portals == null)
			return;
		
		for(Block portal : portals){
			portal.setType(Material.AIR);
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void onBlockPlace(BlockPlaceEvent event){
		
		Block block = event.getBlock();
		
		if(block.getWorld().getEnvironment() == Environment.THE_END)
			return;
		
		if(!PortalUtil.isNextToPortal(block.getLocation()))
			return;
		
		List<Block> portals = PortalUtil.getPortalsFromFrame(block.getLocation());
		
		if(portals == null)
			return;
		
		for(Block portal : portals){
			portal.setType(Material.AIR);
		}
	}
	
}
