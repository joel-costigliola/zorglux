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

public class Names extends Controller {

   private static final Logger logger = LoggerFactory.getLogger(Names.class);

   public static Result manageNames() {
      return ok(namesManagement.render());
   }

   public static Result getNameCollection(String nameCollectionName) {
      NameCollection nameCollection = AllNameCollections.findByName(nameCollectionName);
      logger.info("'{}' NameCollection selected", nameCollection);
      return ok(nameCollectionTemplate.render(nameCollection));
   }

   public static Result newNameCollection(String nameCollectionName) {
      NameCollection nameCollection = new NameCollection(nameCollectionName);
      AllNameCollections.save(nameCollection);
      logger.info("Name collection '{}' created", nameCollection);
      return ok();
   }

   public static Result deleteNameCollection(String nameCollectionName) {
      AllNameCollections.remove(nameCollectionName);
      logger.info("'{}' NameCollection deleted", nameCollectionName);
      return ok();
   }

   public static Result addNameToCollection(String name, String nameCollectionName) {
      NameCollection nameCollection = AllNameCollections.findByName(nameCollectionName);
      nameCollection.addNames(name);
      AllNameCollections.save(nameCollection);
      logger.info("'{}' name added to '{}' NameCollection", name, nameCollection);
      return ok();
   }

   public static Result removeNameFromCollection(String name, String nameCollectionName) {
      NameCollection nameCollection = AllNameCollections.findByName(nameCollectionName);
      nameCollection.removeNames(name);
      AllNameCollections.save(nameCollection);
      logger.info("'{}' name removed from '{}' NameCollection", name, nameCollection);
      return ok();
   }

   public static List<String> nameCollectionsNames() {
      return newArrayList(AllNameCollections.findAllNameCollectionsName());
   }

}