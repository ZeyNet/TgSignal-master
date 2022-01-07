package Main.TgSignal.EventManager;

import Main.TgSignal.Main.TgSignal;
import Main.TgSignal.Telegram.BroadCast;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerMoveTripwire implements Listener {
    private TgSignal plugin;

    public PlayerMoveTripwire(TgSignal plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerStepSrting(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction() == Action.PHYSICAL){
            if(e.getClickedBlock().getType() == Material.TRIPWIRE) {
                String string = String.format(this.plugin.getConfig().getString("messages.signal").replaceAll("%player%", player.getDisplayName()).replaceAll("&", "§"));
                Thread broadcast = new Thread(new BroadCast(string, TgSignal.chat_ids, false));
                broadcast.start();
            }
        }
    }
}
