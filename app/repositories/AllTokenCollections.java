package repositories;

import models.TokenCollection;
import org.jongo.MongoCollection;

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

   private static MongoCollection tokenCollectionDBCollection() {
      return zorgluxDB().getCollection(TokenCollection.class.getSimpleName());
   }
}