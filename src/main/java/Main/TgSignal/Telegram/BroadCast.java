package Main.TgSignal.Telegram;

import Main.TgSignal.Main.TgSignal;
import java.net.URL;

public class BroadCast implements Runnable {
   private String message = null;
   private String[] chat_ids;
   private Boolean notify;

   public BroadCast(String message, String[] chat_ids, Boolean notify) {
      this.message = message;
      this.chat_ids = chat_ids;
      this.notify = notify;
   }

   public void run() {
      if (this.message != null) {
         SendMessage sm = new SendMessage();
         sm.setText(this.message);
         sm.setHTML(true);
         sm.setNotify(this.notify);
         String[] var2 = this.chat_ids;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String chat_id = var2[var4];
            sm.setChatId(chat_id);
            String api = sm.getSendApiString();
            api = api.replace("{token}", TgSignal.token);

            try {
               (new URL(api)).openStream().close();
            } catch (Exception var8) {

            }
         }

      }
   }
}
