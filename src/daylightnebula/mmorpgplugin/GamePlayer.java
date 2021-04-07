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
        gamePlayers.add(new GamePlayer(le, new HashMap<>()));
    }

    public static void updateAttributes(GamePlayer player) {
        // clear old attributes
        player.currentAttributes.clear();

        // check if player
        if (player.isPlayer()) {
            // for each active item slot TODO: Get list of active item slots
            // use addAttributesFromItem to update the attributes
        } else {
            // is a living entity
            LivingEntity le = player.le;

            // for all the equipment slots
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
