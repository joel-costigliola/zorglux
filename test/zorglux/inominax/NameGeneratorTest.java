package zorglux.inominax;

import static com.google.common.collect.Lists.newArrayList;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NameGeneratorTest {

   private NameGenerator nameGenerator;

   @Before
   public void setup() {
      nameGenerator = new NameGenerator(2, 3, newArrayList("lae", "il", "mar", "sel", "fel", "fin", "iel", "gad", "del", "sin", "rin"));
   }

   @Test
   public void generateName() {
      for (int i = 0; i < 10; i++) {
         System.out.println("name : " + nameGenerator.generateName());
      }
   }

   @Test
   public void generateNameStartingWith() {
      nameGenerator.generatedNameMustStartsWith("toto");
      for (int i = 0; i < 100; i++) {
         assertThat(nameGenerator.generateName()).startsWith("Toto");
      }
   }

   @Test
   public void generateNameEndingWith() {
      nameGenerator.generatedNameMustEndWith("oto");
      for (int i = 0; i < 100; i++) {
         assertThat(nameGenerator.generateName()).endsWith("oto");
      }
   }

   @Test
   public void generateNameContainingString() {
      nameGenerator.generatedNameMustContain("ootoo");
      for (int i = 0; i < 100; i++) {
         assertThat(nameGenerator.generateName()).containsIgnoringCase("ootoo");
      }
   }

   @Test
   public void generateNameMultipleConstraints() {
      String startOfName = "aa";
      nameGenerator.generatedNameMustStartsWith(startOfName);
      nameGenerator.generatedNameMustContain("mm");
      String endOfName = "zz";
      nameGenerator.generatedNameMustEndWith(endOfName);
      nameGenerator.setMaxNumberOfTokensInName(2);
      for (int i = 0; i < 100; i++) {
         String generatedName = nameGenerator.generateName();
         assertThat(generatedName).startsWith("Aa");
         if (generatedName.length() > startOfName.length() + endOfName.length()) {
            assertThat(generatedName).containsIgnoringCase("mm");
         }
         assertThat(generatedName).endsWith(endOfName);
      }
   }
}
