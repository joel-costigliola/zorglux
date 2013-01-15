package models;


import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Sets.newTreeSet;

public class TokenCollection extends StringCollection implements Serializable {

   private static final long serialVersionUID = -160520685358307201L;

   private SortedSet<String> tokens;

   public TokenCollection() {
      this("");
   }

   public TokenCollection(String name) {
      super(name);
      this.tokens = newTreeSet();
   }

   public SortedSet<String> getTokens() {
      return tokens;
   }

   public void setTokens(SortedSet<String> tokens) {
      this.tokens = tokens;
   }

   public void addTokens(String... newNames) {
      for (String token : newNames) {
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

    public static List<String> list() {
        return newArrayList("Elf", "Orc", "Dwarf", "Human");
    }

}
