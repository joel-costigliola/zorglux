package models;


import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.google.common.collect.Lists.newArrayList;

public class NameCollection extends StringCollection implements Serializable {

   private static final long serialVersionUID = -160520685358307201L;

   private SortedSet<String> names;

   public NameCollection() {
      this("");
   }

   public NameCollection(String name) {
      super(name);
      this.names = new TreeSet<String>();
   }

   public Set<String> getNames() {
      return names;
   }

   public void setNames(SortedSet<String> tokens) {
      names = tokens;
   }

   public void addNames(String... newNames) {
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

   public int countNames() {
      return names.size();
   }

   public boolean hasNames() {
      return !names.isEmpty();
   }
}
