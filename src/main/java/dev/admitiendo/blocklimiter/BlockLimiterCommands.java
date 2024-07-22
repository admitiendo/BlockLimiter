package dev.admitiendo.blocklimiter;

import dev.admitiendo.blocklimiter.commands.Command;
import dev.admitiendo.blocklimiter.commands.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockLimiterCommands {

    @Command(name = "blocklimiter", permission = "blocklimiter.manage", inGameOnly = true, description = "Comando principal")
    public void mainCommand(CommandArgs args) {
        args.getPlayer().sendMessage(CC.translate("&8" + CC.normalLine()));
        args.getPlayer().sendMessage(CC.translate("&cComandos: "));
        args.getPlayer().sendMessage(CC.translate("&c&cUso: /blocklimiter remove <jugador>"));
        args.getPlayer().sendMessage(CC.translate("&c&cUso: /blocklimiter add <jugador>"));
        args.getPlayer().sendMessage(CC.translate("&8" + CC.normalLine()));
    }

    @Command(name = "blocklimiter.remove", permission = "blocklimiter.manage", inGameOnly = true, description = "Revocar el bypass de un jugador.")
    public void removebypass(CommandArgs args) {
        Player sender = (Player) args.getSender();
        if (args.getArgs().length != 1) {
            sender.sendMessage(CC.translate("&cUso: /blocklimiter remove <jugador>"));
            return;
        }

        Player player = null;

        try {
            player = Bukkit.getPlayer(args.getArgs(0));
        } catch (Exception ignored) {
        }

        if (player == null) {
            sender.sendMessage(CC.translate("&cNo se ha encontrado al jugador especificado."));
            return;
        }

        if (BlockLimiter.tempLimiter.contains(player.getUniqueId())) {
            sender.sendMessage(CC.translate("&bEl jugador &f" + player.getName() + " &bno esta en la lista de permisos temporales."));
            return;
        }

        BlockLimiter.tempLimiter.remove(player.getUniqueId());
        sender.sendMessage(CC.translate("&bLos permisos de BlockLimiter de el jugador &f" + player.getName() + " &bfueron &crevocados."));
    }

    @Command(name = "blocklimiter.add", permission = "blocklimiter.manage", inGameOnly = true, description = "Agregar bypass a un jugador.")
    public void addbypass(CommandArgs args) {
        Player sender = (Player) args.getSender();
        if (args.getArgs().length != 1) {
            sender.sendMessage(CC.translate("&cUso: /blocklimiter add <jugador>"));
            return;
        }

        Player player = null;

        try {
            player = Bukkit.getPlayer(args.getArgs(0));
        } catch (Exception ignored) {
        }

        if (player == null) {
            sender.sendMessage(CC.translate("&cComo no se pudo encontrar al jugador especificado, se ha agregado tu nick a la lista de permisos."));
            BlockLimiter.tempLimiter.add(sender.getUniqueId());
            return;
        }

        if (BlockLimiter.tempLimiter.contains(player.getUniqueId())) {
            sender.sendMessage(CC.translate("&bEl jugador &f" + player.getName() + " &bya esta en la lista de permisos temporales."));
            return;
        }

        BlockLimiter.tempLimiter.add(player.getUniqueId());
        sender.sendMessage(CC.translate("&bLos permisos de BlockLimiter de el jugador &f" + player.getName() + " &bfueron &econcedidos."));
    }
}
