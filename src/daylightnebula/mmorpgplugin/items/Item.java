package daylightnebula.mmorpgplugin.items;

import daylightnebula.mmorpgplugin.GamePlayer;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Item {

    ItemStack defaultIcon;
    ItemSlot requiredSlot;
    HashMap attributeMap;

    public Item(ItemStack defaultIcon, ItemSlot requiredSlot, HashMap attributeMap) {
        this.defaultIcon = defaultIcon;
        this.requiredSlot = requiredSlot;
        this.attributeMap = attributeMap;
    }

    // getters
    public abstract ItemStack getCurrentIcon();
    public ItemSlot getRequiredSlot() { return requiredSlot; }
    public HashMap getAttributeMap() { return attributeMap; }

    // events
    public abstract void onEquip(GamePlayer gp);
    public abstract void onUnequip(GamePlayer gp);
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
}
