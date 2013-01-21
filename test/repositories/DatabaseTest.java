package repositories;

import com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import repositories.mongo.ZorgluxMongoClient;

import static com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb.MongoServerRuleBuilder.newManagedMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static repositories.mongo.ZorgluxMongoClient.ZORGLUX_TEST_DB;

public class DatabaseTest {

   @ClassRule
   public static ManagedMongoDb managedMongoDb = newManagedMongoDbRule()
                                                    .mongodPath("/home/joe/prog/mongo/mongodb")
                                                    .appendSingleCommandLineArguments("--nojournal")
                                                    .build();
   @Rule
   public MongoDbRule remoteMongoDbRule = newMongoDbRule().defaultManagedMongoDb(ZORGLUX_TEST_DB);

   @BeforeClass
   public static void setupDbConnection() {
      ZorgluxMongoClient.testMode();
   }
}
