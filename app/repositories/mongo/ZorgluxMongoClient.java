package repositories.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;

import java.net.UnknownHostException;

public class ZorgluxMongoClient {

   private static final String ZORGLUX = "zorglux";
   private static MongoClient mongoClient = init();

   private static MongoClient init()
   {
      try {
         // TODO inject config
         return new MongoClient();
      } catch (UnknownHostException e) {
         // TODO log exception
         throw new RuntimeException(e);
      }
   }


   public static MongoClient zorgluxMongoClient() {
      return mongoClient;
   }

   public static Jongo zorgluxJongoDB() {
      return new Jongo(zorgluxMongoClient().getDB(ZORGLUX));
   }
}
