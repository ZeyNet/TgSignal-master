package Main.TgSignal.Commands;

import Main.TgSignal.Main.TgSignal;
import Main.TgSignal.Telegram.getMe;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload implements CommandExecutor {
   private TgSignal plugin;

   public CommandReload(TgSignal plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
      if (commandSender instanceof Player) {
         Player p = (Player)commandSender;
         if (p.hasPermission("tgs.reload")) {
            try {
               this.reloadAction();
               p.sendMessage(this.plugin.getConfig().getString("messages.reload").replaceAll("&", "§"));
            } catch (Exception var7) {
               p.sendMessage(this.plugin.getConfig().getString("messages.no-reload").replaceAll("&", "§"));
               var7.printStackTrace();
            }

            return true;
         } else {
            p.sendMessage(this.plugin.getConfig().getString("messages.no-permission").replaceAll("&", "§"));
            return true;
         }
      } else {
         try {
            this.reloadAction();
            this.plugin.getServer().getConsoleSender().sendMessage(this.plugin.getConfig().getString("messages.reload").replaceAll("&", "§"));
         } catch (Exception var8) {
            this.plugin.getServer().getConsoleSender().sendMessage(this.plugin.getConfig().getString("messages.reload").replaceAll("&", "§"));
            var8.printStackTrace();
         }

         return true;
      }
   }

   private void reloadAction() {
      this.plugin.reloadConfig();
      this.loadToken();
      this.loadChatids();
   }

   private void loadToken() {
      TgSignal.token = this.plugin.getConfig().getString("bot_token").trim();
      getMe getme = new getMe(this.plugin);
      getme.run();
      if (!getMe.field_0) {
         this.plugin.getPluginLoader().disablePlugin(this.plugin);
      }
   }

   private void loadChatids() {
      int size = this.plugin.getConfig().getList("ChatIds").size();
      if (size != 0) {
         TgSignal.chat_ids = new String[size];

         for(int i = 0; i < size; ++i) {
            TgSignal.chat_ids[i] = this.plugin.getConfig().getList("ChatIds").get(i).toString();
         }

      }
   }
}
