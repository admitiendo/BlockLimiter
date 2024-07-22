package dev.admitiendo.blocklimiter;

import dev.admitiendo.blocklimiter.commands.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public final class BlockLimiter extends JavaPlugin {
    public static Plugin plugin;
    public static ArrayList<UUID> tempLimiter = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        CommandFramework framework = new CommandFramework(this);
        framework.registerCommands(new BlockLimiterCommands());
        Bukkit.getConsoleSender().sendMessage(CC.translate("&e" + CC.normalLine() + CC.normalLine()));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&ePlugin de limitador de bloques (Solo cobwebs y lana) activado exitosamente."));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&eBy: admitiendo"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&e" + CC.normalLine() + CC.normalLine()));
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(CC.translate("&c" + CC.normalLine() + CC.normalLine()));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&cPlugin de limitador de bloques (Solo cobwebs y lana) desactivado exitosamente."));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&cBy: admitiendo"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&c" + CC.normalLine() + CC.normalLine()));
    }
}
