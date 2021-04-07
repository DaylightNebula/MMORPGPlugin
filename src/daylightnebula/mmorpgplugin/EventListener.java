package daylightnebula.mmorpgplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {

    public EventListener(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                Update();
            }
        }, 0L, 1L);
    }

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {

    }

    @EventHandler
    public void ProjectileLaunch(ProjectileLaunchEvent event) {

    }

    @EventHandler
    public void PlayerDropItem(PlayerDropItemEvent event) {

    }

    public void Update() {

    }
}
