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

public class Inominax extends Controller {

   private static final Logger logger = LoggerFactory.getLogger(Inominax.class);
   private static final List<String> EMPTY_LIST = emptyList();
   // Defines a form wrapping the NameGeneratorParameters class.
   private static Form<NameGeneratorParameters> generateNamesForm = form(NameGeneratorParameters.class);

   public static Result inominax() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();
      if (filledForm.data().isEmpty()) {
         generateNamesForm = filledForm.fill(new NameGeneratorParameters());
      }
      return ok(inominax.render(generateNamesForm, EMPTY_LIST));
   }

   public static Result generateNames() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();

      if (filledForm.hasErrors()) {
         return badRequest(inominax.render(filledForm, EMPTY_LIST));
      }

      NameGeneratorParameters nameGeneratorParameters = filledForm.get();
      TokenCollection tokenCollection = AllTokenCollections.findByName(nameGeneratorParameters.tokenCollectionName);
      NameGenerator nameGenerator;
      if (tokenCollection != null) {
         nameGenerator = new NameGenerator(tokenCollection);
      } else {
         nameGenerator = new NameGenerator(newArrayList("fil", "el", "sar", "dri", "ga", "len"));
      }
      logger.info("{} names to generate from {}", nameGeneratorParameters.numberOfNamesToGenerate, nameGeneratorParameters.tokenCollectionName);
      Set<String> names = nameGenerator.generateNames(nameGeneratorParameters.numberOfNamesToGenerate);
      return ok(generatedNames.render(newArrayList(names)));
   }

   // Tokens  collections

   public static Result getTokenCollection(String tokenCollectionName) {
      return Tokens.getTokenCollection(tokenCollectionName);
   }

   public static Result newTokenCollection(String tokenCollectionName) {
      return Tokens.newTokenCollection(tokenCollectionName);
   }

   public static Result addTokenToCollection(String token, String tokenCollectionName) {
      return Tokens.addTokenToCollection(token, tokenCollectionName);
   }

   public static Result removeTokenFromCollection(String token, String tokenCollectionName) {
      return Tokens.removeTokenFromCollection(token, tokenCollectionName);
   }

   public static Result deleteTokenCollection(String tokenCollectionName) {
      return Tokens.deleteTokenCollection(tokenCollectionName);
   }

   public static Result manageTokens() {
      return Tokens.manageTokens();
   }

   public static List<String> tokenCollectionsNames() {
      return Tokens.tokenCollectionsNames();
   }

}
