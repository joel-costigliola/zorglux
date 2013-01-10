package zorglux.inominax.models;

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class TokenSet implements Serializable {

   private static final long serialVersionUID = 8401658275213004116L;

   private String name;
   private SortedSet<String> tokens;

   public TokenSet() {
      this("");
   }

   public TokenSet(String name) {
      super();
      this.name = name;
      tokens = new TreeSet<String>();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Set<String> getTokens() {
      return tokens;
   }

   public void setTokens(SortedSet<String> tokens) {
      this.tokens = tokens;
   }

   public void addToken(String... newTokens) {
      for (String token : newTokens) {
         tokens.add(token);
      }
   }

   public void removeTokens(String... tokensToRemove) {
      for (String token : tokensToRemove) {
         tokens.remove(token);
      }
   }

   @Override
   public String toString() {
      return name;
   }

   List<String> toList() {
      return newArrayList(tokens);
   }

}