package models;

import play.data.validation.Constraints.Required;

public class NameGeneratorParameters {

   @Required
   public int numberOfNamesToGenerate;

   public String tokensCollection;

   public NameGeneratorParameters(int numberOfNamesToGenerate) {
      super();
      this.numberOfNamesToGenerate = numberOfNamesToGenerate;
   }

   public NameGeneratorParameters() {
      this(20);
   }

}
