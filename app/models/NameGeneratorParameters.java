package models;

import play.data.validation.Constraints.Required;

public class NameGeneratorParameters {

   @Required
   public int numberOfNamesToGenerate;

   public String tokenCollectionName;

   public NameGeneratorParameters(int numberOfNamesToGenerate) {
      super();
      this.numberOfNamesToGenerate = numberOfNamesToGenerate;
   }

   public NameGeneratorParameters() {
      this(20);
   }

   @Override
   public String toString() {
      return "NameGeneratorParameters[numberOfNamesToGenerate=" + numberOfNamesToGenerate + ", tokenCollection=" + tokenCollectionName + "]";
   }

}
