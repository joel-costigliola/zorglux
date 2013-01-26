package repositories;

import com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import repositories.mongo.ZorgluxMongoClient;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static repositories.mongo.ZorgluxMongoClient.*;

public class DatabaseTest {

   @ClassRule
   public static ManagedMongoDb managedMongoDb = RepositoriesTestSuite.managedMongoDb;

   @Rule
   public MongoDbRule remoteMongoDbRule = newMongoDbRule().configure(mongoDb().port(LOCAL_DB_TEST_PORT).databaseName(ZORGLUX_TEST_DB).build()).build();

   @BeforeClass
   public static void setupDbConnection() {
      ZorgluxMongoClient.testMode();
   }
}
