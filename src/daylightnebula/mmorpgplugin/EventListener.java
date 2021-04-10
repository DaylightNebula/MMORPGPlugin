package daylightnebula.mmorpgplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {

    public EventListener(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Update();
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
        GamePlayer hit = GamePlayer.getLivingEntity(event.getEntity());
        GamePlayer damager = GamePlayer.getLivingEntity(event.getDamager());

        if (hit != null) {
            hit.champion.onTakeDamage(event, hit);
        }
        if (damager != null) {
            damager.champion.onDealDamage(event, hit);
        }
    }

    @EventHandler
    public void ProjectileLaunch(ProjectileLaunchEvent event) {
        GamePlayer launcher = GamePlayer.getLivingEntity((Entity) event.getEntity().getShooter());

        if (launcher != null)
            launcher.champion.onProjectileLaunch(event, launcher);
    }

    @EventHandler
    public void ProjectileHit(ProjectileHitEvent event) {
        GamePlayer launcher = GamePlayer.getLivingEntity((Entity) event.getEntity().getShooter());

        if (launcher != null)
            launcher.champion.onProjectileHit(event, launcher);
    }

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        GamePlayer player = GamePlayer.getLivingEntity(event.getPlayer());

        if (player != null)
            player.champion.onPlayerUseWeapon(event, player);
    }

    @EventHandler
    public void PlayerItemHeld(PlayerItemHeldEvent event) {
        GamePlayer player = GamePlayer.getLivingEntity(event.getPlayer());

        if (player != null)
            player.champion.onPlayerUseAbility(event, player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Select A Champion")) {
            GamePlayer.addLivingEntity(event.getWhoClicked(), Champion.champions[event.getSlot()]);
            GamePlayer.setupInventory(GamePlayer.getLivingEntity(event.getWhoClicked()));
            event.setCancelled(true);
            event.getWhoClicked().closeInventory();
        }
    }

    public void Update() {
        for (LivingEntity le : Bukkit.getWorlds().get(0).getLivingEntities()) {
            GamePlayer gp = GamePlayer.getLivingEntity(le);

            if (gp != null)
                gp.champion.update(gp);
        }
    }
}
