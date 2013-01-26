package repositories.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;
import org.jongo.Jongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ObjectsUtil;

import java.net.UnknownHostException;

import static util.ObjectsUtil.objects;

public class ZorgluxMongoClient {

   public static final int LOCAL_DB_TEST_PORT = 37017;
   private static Logger logger = LoggerFactory.getLogger(ZorgluxMongoClient.class);
   private static final String MONGODB_LOCALHOST = "localhost";
   public static final String ZORGLUX_TEST_DB = "zorglux-test";
   private static final String ZORGLUX_DB = "zorglux";
   private static Jongo zorgluxDb;

   public static void testMode() {
      if (zorgluxDb != null && ZORGLUX_TEST_DB.equals(zorgluxDb.getDatabase().getName())) {
         // we are already connected to test database
         return;
      }
      zorgluxDb = new Jongo(getLocalMongoClient(LOCAL_DB_TEST_PORT, ZORGLUX_TEST_DB));
   }

   public static void applicationMode() {
      zorgluxDb = new Jongo(getDb());
   }

   /**
    * Return zorglux Jongo database encapslation.
    * <p>Before calling this method you must set the mode : testMode or applicationMode to connect to zorglux mongo DB.</p>
    *
    * @return zorglux Jongo database encapslation.
    */
   public static Jongo zorgluxDB() {
      return zorgluxDb;
   }

   private static DB getDb() {
      // try using MongoHQ - "mongodb://heroku:88ca26f82e9d6897e9555b483c6fee3b@linus.mongohq.com:10084/app7420065"
      String mongoHqUrl = System.getenv("MONGOHQ_URL");
      if (mongoHqUrl != null) {
         try {
            return getMongoHqDb(mongoHqUrl);
         } catch (UnknownHostException e) {
            logger.error("Fail to connect to Mongo HQ database with URL '{}'", mongoHqUrl);
            throw new RuntimeException(e);
         }
      }
      // use local database
      return getLocalMongoClient(27017, ZORGLUX_DB);
   }

   private static DB getLocalMongoClient(int port, String zorgluxDb) {
      logger.info("Connecting to Mongo '{}:{}' using '{}' database", objects(MONGODB_LOCALHOST, port, zorgluxDb));
      try {
         return new MongoClient(MONGODB_LOCALHOST, port).getDB(zorgluxDb);
         // return new MongoClient(new MongoClientURI(MONGODB_LOCALHOST)).getDB(zorgluxDb);
      } catch (UnknownHostException e) {
         logger.error("Fail to connect to Mongo '{}' using '{}' database", MONGODB_LOCALHOST, ZorgluxMongoClient.zorgluxDb);
         throw new RuntimeException(e);
      }
   }

   private static DB getMongoHqDb(String mongoHqUrl) throws UnknownHostException {
      logger.info("Connecting to MongoHQ with URL : '{}'", mongoHqUrl);
      MongoURI mongoURI = new MongoURI(mongoHqUrl);
      DB db = mongoURI.connectDB();
      db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
      logger.info("Connected to MongoHQ.");
      return db;
   }

   public static void main(String[] args) {
      System.out.println("MONGO_HOME = " + System.getProperty("MONGO_HOME"));
      System.out.println("MONGO_HOME = " + System.getenv("MONGO_HOME"));
      System.out.println("JAVA_HOME = " + System.getProperty("JAVA_HOME"));
      System.out.println("JAVA_HOME = " + System.getenv("JAVA_HOME"));
      System.out.println("PATH = " + System.getProperty("PATH"));
      System.out.println("PATH = " + System.getenv("PATH"));
   }

}
