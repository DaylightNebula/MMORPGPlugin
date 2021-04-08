package daylightnebula.mmorpgplugin.items;

import daylightnebula.mmorpgplugin.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class GreatswordItem extends Item {

    public GreatswordItem() {
        super(ChatColor.GRAY + "Great Sword");
    }

    ItemStack item;
    @Override
    public ItemStack getCurrentIcon() {
        if (item == null) {
            item = new ItemStack(Material.IRON_SWORD);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }

        return item;
    }

    @Override
    public ItemSlot getRequiredSlot() {
        return ItemSlot.PRIMARY;
    }

    HashMap<ItemAttribute, Integer> attributeMap;
    @Override
    public HashMap getAttributeMap() {
        if (attributeMap == null) {
            attributeMap = new HashMap<ItemAttribute, Integer>();

            attributeMap.put(ItemAttribute.STRENGTH, 3);
        }

        return attributeMap;
    }

    @Override
    public byte[] getDefaultData() {
        return new byte[0];
    }

    @Override
    public void onEquip(GamePlayer gp) {

    }

    @Override
    public void onUnequip(GamePlayer gp) {

    }

    @Override
    public void onItemInteract(PlayerInteractEvent event, GamePlayer player) {

    }

    @Override
    public void onTakeDamage(EntityDamageByEntityEvent event, GamePlayer gp) {

    }

    @Override
    public void onDealDamage(EntityDamageByEntityEvent event, GamePlayer gp, boolean wasMe) {

    }

    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event, GamePlayer gp, boolean wasMe) {

    }

    @Override
    public void onProjectileHit(ProjectileHitEvent event, GamePlayer gp, boolean wasMe) {

    }

    @Override
    public void onAbilityCall(GamePlayer gp) {

    }

    @Override
    public void update(GamePlayer gp) {

    }
}
