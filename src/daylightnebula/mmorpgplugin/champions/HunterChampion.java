package daylightnebula.mmorpgplugin.champions;

import daylightnebula.mmorpgplugin.Champion;
import daylightnebula.mmorpgplugin.GamePlayer;
import daylightnebula.mmorpgplugin.ItemGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HunterChampion extends Champion {
    public HunterChampion(int id) {
        super(id);
    }

    ItemStack champIcon;
    @Override
    public ItemStack getChampIcon(GamePlayer gp) {
        if (champIcon == null) {
            champIcon = ItemGenerator.getIcon("Hunter", 1);
        }

        return champIcon;
    }

    ItemStack weapon;
    @Override
    public ItemStack getWeapon(GamePlayer gp) {
        if (weapon == null)
            weapon = ItemGenerator.getCustomWeapon("Hunters Gun", 1, 1, 1);

        return weapon;
    }

    ItemStack[] abilities;
    @Override
    public ItemStack[] getIcons(GamePlayer gp) {
        if (abilities == null) {
            abilities = new ItemStack[4];
            abilities[0] = ItemGenerator.getIcon("Reload", 2);
            abilities[1] = ItemGenerator.getIcon("Smoke Grenade", 3);
            abilities[2] = ItemGenerator.getIcon("Bear Trap", 4);
            abilities[3] = ItemGenerator.getIcon("Snipe Rifle", 5);
        }

        return abilities;
    }

    ItemStack[] armor;
    @Override
    public ItemStack[] getArmor(GamePlayer gp) {
        if (armor == null) {
            armor = new ItemStack[4];
            armor[0] = new ItemStack(Material.LEATHER_HELMET);
            armor[1] = new ItemStack(Material.LEATHER_CHESTPLATE);
            armor[2] = new ItemStack(Material.LEATHER_LEGGINGS);
            armor[3] = new ItemStack(Material.LEATHER_BOOTS);
        }

        return armor;
    }

    @Override
    public GamePlayer.AbilityContainer[] defaultContainers(GamePlayer gp) {
        return new GamePlayer.AbilityContainer[]{
                new GamePlayer.AbilityContainer(0, 0),
                new GamePlayer.AbilityContainer(0, null),
                new GamePlayer.AbilityContainer(0, null),
                new GamePlayer.AbilityContainer(0, null)
        };
    }

    @Override
    public void onDealDamage(EntityDamageByEntityEvent event, GamePlayer gp) {
        Bukkit.broadcastMessage("Player Deal Damage");
    }

    @Override
    public void onTakeDamage(EntityDamageByEntityEvent event, GamePlayer gp) {
        Bukkit.broadcastMessage("Player Take Damage");
    }

    @Override
    public void onPlayerUseAbility(PlayerItemHeldEvent event, GamePlayer gp) {
        Bukkit.broadcastMessage("Use Ability: " + event.getNewSlot());
    }

    @Override
    public void onPlayerUseWeapon(PlayerInteractEvent event, GamePlayer gp) {
        if ((event.getAction() == Action.RIGHT_CLICK_AIR
                || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && gp.abilities[0].cooldown == 0) {
            gp.le.launchProjectile(Arrow.class);
            gp.abilities[0].cooldown = 5;
        }
    }

    @Override
    public void onProjectileLaunch(ProjectileLaunchEvent event, GamePlayer gp) {
        Bukkit.broadcastMessage("Projectile launched!");
    }

    @Override
    public void onProjectileHit(ProjectileHitEvent event, GamePlayer gp) {
        Bukkit.broadcastMessage("Projectile hit!");
    }

    @Override
    public void update(GamePlayer gp) {
        if (gp.abilities[0].cooldown > 0) gp.abilities[0].cooldown--;
    }
}
