package daylightnebula.mmorpgplugin;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemGenerator {

    public static Material IconBase = Material.GOLDEN_HOE;
    public static Material WeaponBase = Material.GOLDEN_PICKAXE;

    public static ItemStack getIcon(String name, int custommodelid) {
        ItemStack item = new ItemStack(IconBase);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setCustomModelData(custommodelid);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getCustomWeapon(String name, int custommodelid, int attackdamage, int attackspeed) {
        ItemStack item = new ItemStack(WeaponBase);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setCustomModelData(custommodelid);
        item.setItemMeta(meta);

        return item;
    }
}
