package daylightnebula.mmorpgplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    EventListener eventListener;

    @Override
    public void onEnable() {
        eventListener = new EventListener(this);
        Bukkit.getPluginManager().registerEvents(eventListener, this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equals("getitems") && commandSender instanceof Player) {
            ((Player) commandSender).openInventory(Champion.championMenu(GamePlayer.gamePlayers.get((Player) commandSender)));
        }

        return true;
    }

    @Override
    public void onDisable() {

    }
}
