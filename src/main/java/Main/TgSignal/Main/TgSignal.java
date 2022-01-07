package Main.TgSignal.Main;

import Main.TgSignal.Commands.CommandReload;
import Main.TgSignal.EventManager.PlayerMoveTripwire;
import Main.TgSignal.Telegram.getMe;
import Main.TgSignal.Telegram.getUpdates;
import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TgSignal extends JavaPlugin {
   public static String token = null;
   public static String[] chat_ids;
   public static File dataFolder;
   public static TgSignal plugin;

   public void onEnable() {
      this.saveDefaultConfig();
      if (!this.getConfig().getBoolean("enable")) {
         this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_BLUE + "[TgSignal] " + ChatColor.RED + "Вставьте свойтокен в bot_token: \"сюда ваш токен\" и установите enable: true. Перезапустите сервер!");
         this.getPluginLoader().disablePlugin(this);
      } else {
         token = this.getConfig().getString("bot_token").trim();
         getMe getme = new getMe(this);
         getme.run();
         if (!getMe.field_0) {
            this.getPluginLoader().disablePlugin(this);
         } else {
            dataFolder = this.getDataFolder();
            plugin = this;
            this.loadChatids();
            this.getCommand("tgsreload").setExecutor(new CommandReload(this));
            this.getServer().getPluginManager().registerEvents(new PlayerMoveTripwire(this), this);
            getUpdates getUpds = new getUpdates(token);
            new BukkitRunnable() {
               @Override
               public void run() {
                  getUpds.fetch();
               }
           }.runTaskTimerAsynchronously(this, 10L, 10L);
         }
      }
   }

   public void onDisable() {
   }

   private void loadChatids() {
      int size = this.getConfig().getList("ChatIds").size();
      if (size != 0) {
         chat_ids = new String[size];

         for(int i = 0; i < size; ++i) {
            chat_ids[i] = this.getConfig().getList("ChatIds").get(i).toString();
         }

      }
   }
}
