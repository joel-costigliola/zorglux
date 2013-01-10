package zorglux.inominax.models;


import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class NameCollection implements Serializable {

   private static final long serialVersionUID = -160520685358307201L;

   private String name;
   private SortedSet<String> names;

   public NameCollection() {
      this("");
   }

   public NameCollection(String name) {
      super();
      this.name = name;
      this.names = new TreeSet<String>();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Set<String> getNames() {
      return names;
   }

   public void setNames(SortedSet<String> tokens) {
      names = tokens;
   }

   public void addName(String... newNames) {
      for (String token : newNames) {
         names.add(token);
      }
   }

   public void removeNames(String... tokensToRemove) {
      for (String token : tokensToRemove) {
         names.remove(token);
      }
   }

   @Override
   public String toString() {
      return name;
   }

}
