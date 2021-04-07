package daylightnebula.mmorpgplugin;

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

        }
    }

    private static void addAttributesFromItem(HashMap playerMap, HashMap itemMap) {
        for (Object obj : itemMap.keySet()) {
            ItemAttribute attr = (ItemAttribute) obj;
            // finish
        }
    }
}
