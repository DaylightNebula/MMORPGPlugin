package daylightnebula.mmorpgplugin.items;

import daylightnebula.mmorpgplugin.Essentials;
import daylightnebula.mmorpgplugin.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public abstract class Item {

    public String name; // display name, this cannot change as it is used for id
    ItemStack defaultIcon;
    ItemSlot requiredSlot;
    HashMap attributeMap;

    public Item(String name) {
        this.name = name;
        this.defaultIcon = getCurrentIcon();
        this.requiredSlot = getRequiredSlot();
        this.attributeMap = getAttributeMap();
    }

    // getters
    public abstract ItemStack getCurrentIcon();
    public abstract ItemSlot getRequiredSlot();
    public abstract HashMap getAttributeMap();

    // required data
    public abstract byte[] getDefaultData();

    // events
    public abstract void onEquip(GamePlayer gp);
    public abstract void onUnequip(GamePlayer gp);
    public abstract void onItemInteract(PlayerInteractEvent event, GamePlayer player);
    public abstract void onTakeDamage(EntityDamageByEntityEvent event, GamePlayer gp);
    public abstract void onDealDamage(EntityDamageByEntityEvent event, GamePlayer gp, boolean wasMe);
    public abstract void onProjectileLaunch(ProjectileLaunchEvent event, GamePlayer gp, boolean wasMe);
    public abstract void onProjectileHit(ProjectileHitEvent event, GamePlayer gp, boolean wasMe);
    public abstract void onAbilityCall(GamePlayer gp); // may be called through drop, or through ability press (bound to keys 1,2,3,4,7,8,9)
    public abstract void update(GamePlayer gp);

    public enum ItemSlot {
        PRIMARY,
        SECONDARY,
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        KNICKKNACK // rings, amulets, necklaces, etc
    }

    // STATIC
    public static Item[] items = new Item[]{};

    public static Item getItemByName(String name) {
        for (Item item : items)
            if (item.name.equals(name))
                return item;
        return null;
    }

    public static ItemStack getOutputItemWithData(Item item) {
        ItemStack itemStack = item.defaultIcon.clone();
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(
                Essentials.key,
                PersistentDataType.BYTE_ARRAY,
                item.getDefaultData());
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static Inventory adminInventory;
    public static Inventory getAdminInventory() {
        if (adminInventory == null) {
            adminInventory = Bukkit.createInventory(null, 27, "Admin Only");

            for (Item item : items) {
                adminInventory.addItem(Item.getOutputItemWithData(item));
            }
        }

        return adminInventory;
    }
}
