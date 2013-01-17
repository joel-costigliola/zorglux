package repositories;

import models.TokenCollection;
import org.jongo.MongoCollection;

import static repositories.mongo.ZorgluxMongoClient.*;

public class AllTokenCollections {

   public Iterable<TokenCollection> findAll() {
      return tokenCollectionMongoCollection().find().as(TokenCollection.class);
   }

   public TokenCollection findByName(String name) {
      return tokenCollectionMongoCollection().findOne("{name:#}", name).as(TokenCollection.class);
   }

   public long count() {
      return tokenCollectionMongoCollection().count();
   }

   public void save(TokenCollection tokenCollection) {
      tokenCollectionMongoCollection().save(tokenCollection);
   }

   private MongoCollection tokenCollectionMongoCollection() {
      return zorgluxJongoDB().getCollection(TokenCollection.class.getSimpleName());
   }
}