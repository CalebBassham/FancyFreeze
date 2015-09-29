package gg.uhc.fancyfreeze.commands;

import gg.uhc.fancyfreeze.api.Freezer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class FreezeCommand implements CommandExecutor {

    protected static final String USAGE = ChatColor.RED + "Usage: /ff playerName [on|off]";
    protected static final String FROZEN = ChatColor.AQUA + "Player %s is now %s";
    protected static final String PLAYER_FROZEN = ChatColor.AQUA + "You have been %s";

    protected final Freezer freezer;

    public FreezeCommand(Freezer freezer) {
        this.freezer = freezer;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(USAGE);
            return true;
        }

        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        UUID uuid = player.getUniqueId();

        String type = "toggle";

        if (args.length > 1) {
            type = args[1].toLowerCase();
        }

        boolean frozen;
        switch (type) {
            case "toggle":
                frozen = freezer.toggleAlwaysFreeze(uuid);
                break;
            case "on":
                frozen = true;
                freezer.alwaysFreeze(uuid);
                break;
            case "off":
                frozen = false;
                freezer.removeAlwaysFreeze(uuid);
                break;
            default:
                sender.sendMessage(USAGE);
                return true;
        }

        sender.sendMessage(String.format(FROZEN, args[0], frozen ? "frozen" : "unfrozen"));

        if (player.isOnline()) {
            player.getPlayer().sendMessage(String.format(PLAYER_FROZEN, frozen ? "frozen" : "unfrozen"));
        }

        return true;
    }
}