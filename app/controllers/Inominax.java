package controllers;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Set;

import models.NameGenerator;
import models.NameGeneratorParameters;
import models.TokenCollection;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.AllTokenCollections;
import views.html.index;
import views.html.saved;

public class Inominax extends Controller {

   private static final List<String> EMPTY_SET = newArrayList();
   /**
    * Defines a form wrapping the NameGeneratorParameters class.
    */
   static Form<NameGeneratorParameters> generateNamesForm = form(NameGeneratorParameters.class);
   private static AllTokenCollections allTokenCollections = new AllTokenCollections();
   private static NameGenerator nameGenerator;

   public static Result index() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();
      if (filledForm.data().isEmpty()) {
         generateNamesForm = filledForm.fill(new NameGeneratorParameters());
      }
      return ok(index.render(generateNamesForm, EMPTY_SET));
   }

   /**
    * Handle the form submission.
    */
   public static Result generateNames() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();

      if (filledForm.hasErrors()) {
         return badRequest(index.render(filledForm, EMPTY_SET));
      }

      NameGeneratorParameters nameGeneratorParameters = filledForm.get();
      TokenCollection tokenCollection = allTokenCollections.findByName(nameGeneratorParameters.tokenCollection);
      if (tokenCollection != null) {
         nameGenerator = new NameGenerator(tokenCollection);
      } else {
         nameGenerator = new NameGenerator(newArrayList("fil", "el", "sar", "dri", "ga", "len"));
      }
      System.out.println(nameGeneratorParameters.numberOfNamesToGenerate + " names to generate from " +
         nameGeneratorParameters.tokenCollection);
      Set<String> names = nameGenerator.generateNames(nameGeneratorParameters.numberOfNamesToGenerate);
      return ok(index.render(filledForm, newArrayList(names)));
   }

   /**
    * Handle the form submission.
    */
   public static Result getSavedNamesOf(String namesCollection) {
      if ("Zim".equals(namesCollection)) {
         return ok(saved.render(newArrayList("Angus", "Sigfried", "Joachim")));
      }
      return ok(saved.render(newArrayList("Sarik", "Si-Shin", "Seldrick")));
   }


}