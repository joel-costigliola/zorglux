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
import views.html.generatedNames;
import views.html.index;
import views.html.nameCollectionTemplate;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.*;
import static java.util.Collections.emptyList;

public class Inominax extends Controller {

   private static final Logger logger = LoggerFactory.getLogger(Inominax.class);
   private static final List<String> EMPTY_LIST = emptyList();
   /**
    * Defines a form wrapping the NameGeneratorParameters class.
    */
   private static Form<NameGeneratorParameters> generateNamesForm = form(NameGeneratorParameters.class);

   public static Result index() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();
      if (filledForm.data().isEmpty()) {
         generateNamesForm = filledForm.fill(new NameGeneratorParameters());
      }
      return ok(index.render(generateNamesForm, EMPTY_LIST));
   }

   public static Result generateNames() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();

      if (filledForm.hasErrors()) {
         return badRequest(index.render(filledForm, EMPTY_LIST));
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

   /**
    * called when user select a NameCollection.
    */
   public static Result getNameCollection(String nameCollectionName) {
      NameCollection nameCollection = AllNameCollections.findByName(nameCollectionName);
      logger.info("'{}' NameCollection selected", nameCollection);
      return ok(nameCollectionTemplate.render(nameCollection));
   }

   /**
    * called when user select creates a new NameCollection.
    */
   public static Result newNameCollection(String nameCollectionName) {
      NameCollection nameCollection = new NameCollection(nameCollectionName);
      AllNameCollections.save(nameCollection);
      logger.info("Name collection '{}' created", nameCollection);
      return ok();
   }

   public static List<String> nameCollectionsNamesOptions() {
      return newArrayList(AllNameCollections.findAllNameCollectionsName());
   }

   public static List<String> tokenCollectionsNamesOptions() {
      return newArrayList(AllTokenCollections.findAllTokenCollectionsName());
   }

}