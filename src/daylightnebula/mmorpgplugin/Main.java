package daylightnebula.mmorpgplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    EventListener eventListener;

    @Override
    public void onEnable() {
        eventListener = new EventListener(this);
        Bukkit.getPluginManager().registerEvents(eventListener, this);
    }

    @Override
    public void onDisable() {

    }
}
