package repositories.mongo;

import com.mongodb.*;
import org.jongo.Jongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

import static com.google.common.base.Objects.firstNonNull;

public class ZorgluxMongoClient {

   private static final String ZORGLUX = "zorglux";
   private static final String MONGOHQ_URL = "MONGOHQ_URL";
   private static final String ZORGLUX_LOCALHOST_MONGODB = "mongodb://localhost";
   private static Logger logger;
   private static MongoClient mongoClient = init();

   private static MongoClient init() {
      logger = LoggerFactory.getLogger(ZorgluxMongoClient.class);
      try {
         String mongoUrl = firstNonNull(System.getenv(MONGOHQ_URL), ZORGLUX_LOCALHOST_MONGODB);
         logger.info("Connecting to Mongo : {}", mongoUrl);
         return new MongoClient(new MongoClientURI(mongoUrl));
      } catch (UnknownHostException e) {
         logger.error("relaunching following exception as a RuntimeException", e);
         throw new RuntimeException(e);
      }
   }

   public static MongoClient zorgluxMongoClient() {
      return mongoClient;
   }

   public static Jongo zorgluxDB() {
      return new Jongo(zorgluxMongoClient().getDB(ZORGLUX));
   }

}
