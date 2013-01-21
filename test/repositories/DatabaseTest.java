package repositories;

import org.junit.BeforeClass;
import repositories.mongo.ZorgluxMongoClient;

public class DatabaseTest {

   @BeforeClass
   public static void setupDbConnection() {
      ZorgluxMongoClient.testMode();
   }
}
