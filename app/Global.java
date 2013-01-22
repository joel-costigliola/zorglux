import play.Application;
import play.GlobalSettings;
import play.Logger;
import repositories.mongo.ZorgluxMongoClient;

public class Global extends GlobalSettings {

   @Override
   public void onStart(Application app) {
      Logger.info("Application has started");
      ZorgluxMongoClient.applicationMode();
   }

   @Override
   public void onStop(Application app) {
      Logger.info("Application shutdown...");
   }
}