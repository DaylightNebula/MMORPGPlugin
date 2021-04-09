package daylightnebula.mmorpgplugin;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePlayer {

    public LivingEntity le;
    public Champion champion;

    public GamePlayer(LivingEntity le, Champion champion) {
        this.le = le;
        this.champion = champion;
    }

    public boolean isPlayer() {
        return le instanceof Player;
    }

    // STATIC
    public static Map<LivingEntity, GamePlayer> gamePlayers = new HashMap<>();

    public static void addLivingEntity(LivingEntity le, Champion champ) {
        GamePlayer gp = new GamePlayer(le, champ);
        gamePlayers.put(le, gp);
    }
}
