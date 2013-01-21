package repositories;

import com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.SuiteClasses;
import static com.lordofthejars.nosqlunit.mongodb.ManagedMongoDb.MongoServerRuleBuilder.newManagedMongoDbRule;

@RunWith(Suite.class)
@SuiteClasses(value = {AllNameCollectionsTest.class, AllTokenCollectionsTest.class})
public class RepositoriesTestSuite {

   @ClassRule
   public static ManagedMongoDb managedMongoDb = newManagedMongoDbRule()
                                                    .mongodPath("/home/joe/prog/mongo/mongodb")
                                                    .appendSingleCommandLineArguments("--nojournal")
                                                    .build();

}
