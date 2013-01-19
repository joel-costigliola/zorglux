package repositories;

import models.NameCollection;
import org.jongo.MongoCollection;

import static repositories.mongo.ZorgluxMongoClient.*;

public class AllNameCollections {

   public Iterable<NameCollection> findAll() {
      return nameCollectionDBCollection().find().as(NameCollection.class);
   }

   public NameCollection findByName(String name) {
      return nameCollectionDBCollection().findOne("{name:#}", name).as(NameCollection.class);
   }

   public long count() {
      return nameCollectionDBCollection().count();
   }

   public void save(NameCollection nameCollection) {
      nameCollectionDBCollection().save(nameCollection);
   }

   private MongoCollection nameCollectionDBCollection() {
      return zorgluxDB().getCollection(NameCollection.class.getSimpleName());
   }
}