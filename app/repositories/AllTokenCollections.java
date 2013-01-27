package repositories;

import com.mongodb.DBObject;
import models.TokenCollection;
import org.jongo.MongoCollection;
import org.jongo.ResultHandler;

import static repositories.mongo.ZorgluxMongoClient.*;

public class AllTokenCollections {

   public static Iterable<TokenCollection> findAll() {
      return tokenCollectionDBCollection().find().as(TokenCollection.class);
   }

   public static TokenCollection findByName(String name) {
      return tokenCollectionDBCollection().findOne("{name:#}", name).as(TokenCollection.class);
   }

   public static long count() {
      return tokenCollectionDBCollection().count();
   }

   public static void save(TokenCollection tokenCollection) {
      tokenCollectionDBCollection().save(tokenCollection);
   }

   public static void remove(String tokenCollectionName) {
      tokenCollectionDBCollection().remove("{name:#}", tokenCollectionName);
   }

   private static MongoCollection tokenCollectionDBCollection() {
      return zorgluxDB().getCollection(TokenCollection.class.getSimpleName());
   }

   public static Iterable<String> findAllTokenCollectionsName() {
      return tokenCollectionDBCollection().find().fields("{name:1, _id:0}").sort("{name:1}")
                .map(new ResultHandler<String>() {
                   @Override
                   public String map(DBObject result) { return (String) result.get("name"); }
                });
   }
}