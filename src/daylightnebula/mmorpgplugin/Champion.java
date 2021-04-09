package daylightnebula.mmorpgplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class Champion {

    public int id;

    public Champion(int id) {
        this.id = id;
    }

    // getters
    public abstract ItemStack getChampIcon(GamePlayer gp);
    public abstract ItemStack getWeapon(GamePlayer gp);
    public abstract ItemStack[] getIcons(GamePlayer gp);
    public abstract ItemStack[] getArmor(GamePlayer gp);
    public abstract GamePlayer.AbilityContainer[] defaultContainers(GamePlayer gp); // create new every time, do not want overlap

    // events
    public abstract void onDealDamage(EntityDamageByEntityEvent event, GamePlayer gp);
    public abstract void onTakeDamage(EntityDamageByEntityEvent event, GamePlayer gp);
    public abstract void onPlayerUseAbility(PlayerItemHeldEvent event, GamePlayer gp);
    public abstract void onPlayerUseWeapon(PlayerInteractEvent event, GamePlayer gp);
    public abstract void onProjectileLaunch(ProjectileLaunchEvent event, GamePlayer gp);
    public abstract void onProjectileHit(ProjectileHitEvent event, GamePlayer gp);
    public abstract void update(GamePlayer gp);

    // STATIC
    public static Champion[] champions = new Champion[]{};

    private static Inventory inv;
    public static Inventory championMenu(GamePlayer gp) {
        if (inv == null) {
            inv = Bukkit.createInventory(null, 27, "Select A Champion");

            for (Champion champion : champions)
                inv.addItem(champion.getChampIcon(gp));
        }

        return inv;
    }
}
