package daylightnebula.mmorpgplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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

    // events
    public abstract void onDealDamage(EntityDamageByEntityEvent event, GamePlayer gp);
    public abstract void onTakeDamage(EntityDamageByEntityEvent event, GamePlayer gp);
    public abstract void onPlayerUseAbility(GamePlayer gp, int slot);
    public abstract void onPlayerUseWeapon(PlayerInteractEvent event, GamePlayer gp);

    // STATIC
    public static Champion[] champions = new Champion[]{};

    private static Inventory inv;
    public static Inventory getAdminInventory(GamePlayer gp) {
        if (inv == null) {
            inv = Bukkit.createInventory(null, 27, "Admin Inventory");

            for (Champion champion : champions)
                inv.addItem(champion.getChampIcon(gp));
        }

        return inv;
    }
}
