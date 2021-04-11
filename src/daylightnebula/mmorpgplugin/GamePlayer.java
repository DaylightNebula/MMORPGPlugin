package daylightnebula.mmorpgplugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
        gp.abilities = champ.defaultContainers(gp);
        gamePlayers.put(le, gp);
    }

    public static void setupInventory(GamePlayer gp) {
        if (!(gp.le instanceof Player)) return;

        Player player = (Player) gp.le;

        ItemStack[] icons = gp.champion.getIcons(gp);
        ItemStack[] freshArmor = gp.champion.getArmor(gp);
        player.getInventory().setItem(0, icons[0]);
        player.getInventory().setItem(1, icons[1]);
        player.getInventory().setItem(2, icons[2]);
        player.getInventory().setItem(3, icons[3]);
        player.getEquipment().setHelmet(freshArmor[0]);
        player.getEquipment().setChestplate(freshArmor[1]);
        player.getEquipment().setLeggings(freshArmor[2]);
        player.getEquipment().setBoots(freshArmor[3]);
        player.getInventory().setItem(4, gp.champion.getWeapon(gp));
        player.getInventory().setHeldItemSlot(4);
    }

    public static GamePlayer getLivingEntity(Entity entity) {
        if (entity == null) return null;
        if (!(entity instanceof LivingEntity)) return null;

        return gamePlayers.get(entity);
    }
}
