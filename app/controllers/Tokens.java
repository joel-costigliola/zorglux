package controllers;

import models.NameCollection;
import models.NameGenerator;
import models.NameGeneratorParameters;
import models.TokenCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AllNameCollections;
import repositories.AllTokenCollections;
import views.html.*;
import views.html.inominax;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.*;
import static java.util.Collections.emptyList;

public class Tokens extends Controller {

   private static final Logger logger = LoggerFactory.getLogger(Tokens.class);

   public static Result getTokenCollection(String tokenCollectionName) {
      TokenCollection tokenCollection = AllTokenCollections.findByName(tokenCollectionName);
      logger.info("'{}' TokenCollection selected", tokenCollection);
      return ok(tokensTemplate.render(tokenCollection));
   }

   public static Result newTokenCollection(String tokenCollectionToken) {
      TokenCollection tokenCollection = new TokenCollection(tokenCollectionToken);
      AllTokenCollections.save(tokenCollection);
      logger.info("'{}' TokenCollection created", tokenCollection);
      return ok();
   }

   public static Result addTokenToCollection(String token, String tokenCollectionName) {
      TokenCollection tokenCollection = AllTokenCollections.findByName(tokenCollectionName);
      tokenCollection.addTokens(token);
      AllTokenCollections.save(tokenCollection);
      logger.info("'{}' token added to '{}' TokenCollection", token, tokenCollection);
      return ok();
   }

   public static Result removeTokenFromCollection(String token, String tokenCollectionName) {
      TokenCollection tokenCollection = AllTokenCollections.findByName(tokenCollectionName);
      tokenCollection.removeTokens(token);
      AllTokenCollections.save(tokenCollection);
      logger.info("'{}' token removed from '{}' TokenCollection", token, tokenCollection);
      return ok();
   }

   public static Result deleteTokenCollection(String tokenCollectionName) {
      AllTokenCollections.remove(tokenCollectionName);
      logger.info("'{}' TokenCollection deleted", tokenCollectionName);
      return ok();
   }

   public static Result renameTokenCollection(String nameCollectionToken, String newName) {
      TokenCollection tokenCollection = AllTokenCollections.findByName(nameCollectionToken);
      tokenCollection.renameTo(newName);
      AllTokenCollections.save(tokenCollection);
      logger.info("'{}' TokenCollection renamed to '{}'", nameCollectionToken, newName);
      return ok();
   }


   public static Result manageTokens() {
      logger.info("Managing Token collections ...");
      return ok(tokensManagement.render());
   }

   public static List<String> tokenCollectionsNames() {
      return newArrayList(AllTokenCollections.findAllTokenCollectionsName());
   }

}

