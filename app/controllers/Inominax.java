package controllers;

import models.NameGeneratorParameters;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Inominax extends Controller {

   /**
    * Defines a form wrapping the NameGeneratorParameters class.
    */
   static Form<NameGeneratorParameters> generateNamesForm = form(NameGeneratorParameters.class);

   public static Result index() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();
      if (filledForm == null || filledForm.data().isEmpty()) {
         generateNamesForm = filledForm.fill(new NameGeneratorParameters());
      }
      return ok(index.render(generateNamesForm));
   }

   /**
    * Handle the form submission.
    */
   public static Result generateNames() {
      Form<NameGeneratorParameters> filledForm = generateNamesForm.bindFromRequest();

      // Check if the username is valid

      if (filledForm.hasErrors()) {
         return badRequest(index.render(filledForm));
      } else {
         // NameGeneratorParameters nameGeneratorParameters = filledForm.get();
         // System.out.println(nameGeneratorParameters.numberOfNamesToGenerate + " names to generate from " +
         // nameGeneratorParameters.tokensCollection);
         return ok(index.render(filledForm));
      }
   }

}