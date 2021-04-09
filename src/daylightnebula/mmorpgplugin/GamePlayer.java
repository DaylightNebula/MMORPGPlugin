package daylightnebula.mmorpgplugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GamePlayer {

    // MAIN CLASS
    public LivingEntity le;
    public Champion champion;

    public AbilityContainer[] abilities;

    public GamePlayer(LivingEntity le, Champion champion) {
        this.le = le;
        this.champion = champion;

        abilities = new AbilityContainer[5];
    }

    public boolean isPlayer() {
        return le instanceof Player;
    }

    // ABILITY CONTAINER
    public static class AbilityContainer {
        public int cooldown;
        public Object storage;

        public AbilityContainer(int cooldown, Object o) {
            this.cooldown = cooldown;
            this.storage = o;
        }
    }

    // STATIC
    public static Map<LivingEntity, GamePlayer> gamePlayers = new HashMap<>();

    public static void addLivingEntity(LivingEntity le, Champion champ) {
        GamePlayer gp = new GamePlayer(le, champ);
        gamePlayers.put(le, gp);
    }

    public static GamePlayer getLivingEntity(Entity entity) {
        if (entity == null) return null;
        if (entity instanceof LivingEntity) return null;

        return gamePlayers.get(entity);
    }
}
