package daylightnebula.mmorpgplugin;

import daylightnebula.mmorpgplugin.items.Item;
import daylightnebula.mmorpgplugin.items.ItemAttribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GamePlayer {

    public LivingEntity le;
    public HashMap<ItemAttribute, Integer> currentAttributes;

    public GamePlayer(LivingEntity le, HashMap<ItemAttribute, Integer> currentAttributes) {
        this.le = le;
        this.currentAttributes = currentAttributes;
    }

    public boolean isPlayer() {
        return le instanceof Player;
    }

    // STATIC
    public static ArrayList<GamePlayer> gamePlayers = new ArrayList<GamePlayer>();

    public static void addLivingEntity(LivingEntity le) {
        GamePlayer gp = new GamePlayer(le, new HashMap<>());
        gamePlayers.add(gp);
        updateAttributes(gp);
    }

    public static void updateAttributes(GamePlayer player) {
        // clear old attributes
        player.currentAttributes.clear();

        // check if player
        if (player.isPlayer()) {
            // get player
            Player p = (Player) player.le;

            // for all equipment slots, add attributes
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getHelmet().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getChestplate().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getLeggings().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getBoots().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getItem(9).getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getItem(10).getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getItem(11).getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getItem(12).getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(p.getInventory().getItem(13).getItemMeta().getDisplayName()).getAttributeMap());
        } else {
            // is a living entity
            LivingEntity le = player.le;

            // for all the equipment slots, add attributes
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(le.getEquipment().getItemInMainHand().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(le.getEquipment().getChestplate().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(le.getEquipment().getHelmet().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(le.getEquipment().getLeggings().getItemMeta().getDisplayName()).getAttributeMap());
            addAttributesFromItem(player.currentAttributes, Item.getItemByName(le.getEquipment().getBoots().getItemMeta().getDisplayName()).getAttributeMap());
        }
    }

    private static void addAttributesFromItem(HashMap playerMap, HashMap itemMap) {
        // for each attribute in item
        for (Object obj : itemMap.keySet()) {
            ItemAttribute attr = (ItemAttribute) obj;

            // check if it already exists on the player map
            if (playerMap.containsKey(attr)) {
                Integer newValue = (Integer) playerMap.get(attr) + (Integer) itemMap.get(attr);
                playerMap.remove(attr);
                playerMap.put(attr, newValue);
            } else {
                playerMap.put(attr, itemMap.get(attr));
            }
        }
    }
}
