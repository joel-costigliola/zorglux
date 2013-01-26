package repositories;

import com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.SuiteClasses;
import static com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb.MongoServerRuleBuilder.newManagedMongoDbRule;
import static repositories.mongo.ZorgluxMongoClient.LOCAL_DB_TEST_PORT;

@RunWith(Suite.class)
@SuiteClasses(value = {AllNameCollectionsTest.class, AllTokenCollectionsTest.class})
public class RepositoriesTestSuite {

   @ClassRule
   public static ManagedMongoDb managedMongoDb = newManagedMongoDbRule()
                                                    .mongodPath("/home/joe/prog/mongo/mongodb")
                                                    .appendSingleCommandLineArguments("--nojournal")
                                                    .port(LOCAL_DB_TEST_PORT)
                                                    .build();

}
