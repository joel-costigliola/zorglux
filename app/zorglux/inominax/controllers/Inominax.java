package zorglux.inominax.controllers;

import play.mvc.Controller;
import play.mvc.Result;
import zorglux.inominax.views.html.index;

public class Inominax extends Controller {
  
  public static Result index() {
      return ok(index.render("Inominax is ready to generates names for you ..."));
  }
  
}