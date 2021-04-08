package daylightnebula.mmorpgplugin;

import daylightnebula.mmorpgplugin.items.Item;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    EventListener eventListener;

    @Override
    public void onEnable() {
        Essentials.plugin = this;
        eventListener = new EventListener(this);
        Bukkit.getPluginManager().registerEvents(eventListener, this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equals("getitems") && commandSender instanceof Player) {
            ((Player) commandSender).openInventory(Item.getAdminInventory());
        }

        return true;
    }

    @Override
    public void onDisable() {

    }
}
