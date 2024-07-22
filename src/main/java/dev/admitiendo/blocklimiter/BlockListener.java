package dev.admitiendo.blocklimiter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block b = event.getBlock();
        Player player = event.getPlayer();
        if (!b.getType().equals(Material.COBWEB)
                || !b.getType().toString().endsWith("WOOL")) {
            if (player.hasPermission("blocklimiter.bypass")) {
                return;
            }
            event.setCancelled(true);
        }
    }
}
