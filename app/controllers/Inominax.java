package controllers;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Set;

import models.NameGenerator;
import models.NameGeneratorParameters;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Inominax extends Controller {

   private static final List<String> EMPTY_SET = newArrayList();
   /**
    * Defines a form wrapping the NameGeneratorParameters class.
    */
   static Form<NameGeneratorParameters> generateNamesForm = form(NameGeneratorParameters.class);

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

      if (filledForm.hasErrors()) { return badRequest(index.render(filledForm, EMPTY_SET)); }

      NameGeneratorParameters nameGeneratorParameters = filledForm.get();
      NameGenerator nameGenerator = new NameGenerator(newArrayList("fil", "el", "sar", "dri", "ga", "len"));
      System.out.println(nameGeneratorParameters.numberOfNamesToGenerate + " names to generate from " +
            nameGeneratorParameters.tokensCollection);
      Set<String> names = nameGenerator.generateNames(nameGeneratorParameters.numberOfNamesToGenerate);
      return ok(index.render(filledForm, newArrayList(names)));
   }

}