package repositories;

import models.TokenCollection;
import org.jongo.MongoCollection;

import static repositories.mongo.ZorgluxMongoClient.*;

public class AllTokenCollections {

   public Iterable<TokenCollection> findAll() {
      return tokenCollectionDBCollection().find().as(TokenCollection.class);
   }

   public TokenCollection findByName(String name) {
      return tokenCollectionDBCollection().findOne("{name:#}", name).as(TokenCollection.class);
   }

   public long count() {
      return tokenCollectionDBCollection().count();
   }

   public void save(TokenCollection tokenCollection) {
      tokenCollectionDBCollection().save(tokenCollection);
   }

   private MongoCollection tokenCollectionDBCollection() {
      return zorgluxDB().getCollection(TokenCollection.class.getSimpleName());
   }
}