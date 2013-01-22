package repositories;

import com.mongodb.DBObject;
import models.NameCollection;
import org.jongo.MongoCollection;
import org.jongo.ResultHandler;

import static repositories.mongo.ZorgluxMongoClient.*;

public class AllNameCollections {

   public static Iterable<NameCollection> findAll() {
      return nameCollectionDBCollection().find().as(NameCollection.class);
   }

   public static Iterable<String> findAllNameCollectionsName() {
      return nameCollectionDBCollection().find().fields("{name:1, _id:0}").sort("{name:1}")
                .map(new ResultHandler<String>() {
                   @Override
                   public String map(DBObject result) { return (String) result.get("name"); }
                });
   }

   public static NameCollection findByName(String name) {
      return nameCollectionDBCollection().findOne("{name:#}", name).as(NameCollection.class);
   }

   public static long count() {
      return nameCollectionDBCollection().count();
   }

   public static void save(NameCollection nameCollection) {
      nameCollectionDBCollection().save(nameCollection);
   }

   private static MongoCollection nameCollectionDBCollection() {
      return zorgluxDB().getCollection(NameCollection.class.getSimpleName());
   }
}