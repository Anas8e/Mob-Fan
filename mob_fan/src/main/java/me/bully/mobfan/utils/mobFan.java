package me.bully.mobfan.utils;

import me.bully.mobfan.MobFan;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class mobFan implements Listener {
    //Fan detection and placement if the item in hand is a legit fan
    @EventHandler
    public void onFanPlacement(BlockPlaceEvent e) {
        Player placer = e.getPlayer();
        Block fan = e.getBlock();
        if (e.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Mob fan")) {
            if (e.getItemInHand().getItemMeta().getLore().get(0).equals("Mob Fan:")) {
                if (!(e.getBlock().getFace(e.getBlockAgainst()).equals(BlockFace.UP)) && !(e.getBlock().getFace(e.getBlockAgainst()).equals(BlockFace.DOWN)) && !(e.getBlock().getFace(e.getBlockAgainst()).equals(BlockFace.SELF))) {
                    BlockFace face = e.getBlockAgainst().getFace(e.getBlock());
                    BlockState blockState = fan.getState();
                    TileState tileState = (TileState) blockState;
                    PersistentDataContainer container = tileState.getPersistentDataContainer();
                    String setFan;
                    switch (face) {
                        case NORTH:
                            setFan = "NORTH";
                            container.set(new NamespacedKey(MobFan.getPlugin(), "fanFace"), PersistentDataType.STRING, setFan);
                            tileState.update();
                            break;
                        case SOUTH:
                            setFan = "SOUTH";
                            container.set(new NamespacedKey(MobFan.getPlugin(), "fanFace"), PersistentDataType.STRING, setFan);
                            tileState.update();
                            break;
                        case WEST:
                            setFan = "WEST";
                            container.set(new NamespacedKey(MobFan.getPlugin(), "fanFace"), PersistentDataType.STRING, setFan);
                            tileState.update();
                            break;
                        case EAST:
                            setFan = "EAST";
                            container.set(new NamespacedKey(MobFan.getPlugin(), "fanFace"), PersistentDataType.STRING, setFan);
                            tileState.update();
                            break;
                        default:
                            break;
                    }
                    String display = container.get(new NamespacedKey(MobFan.getPlugin(), "fanFace"), PersistentDataType.STRING);
                    placer.sendMessage("Fan placed successfully facing " + display);
                } else {
                    e.setCancelled(true);
                    placer.sendMessage("Please place the fan on a wall");
                }
            }
        }
    }
}
