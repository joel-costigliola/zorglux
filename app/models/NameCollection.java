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
      this.names = new TreeSet<>();
   }

   public Set<String> getNames() {
      return names;
   }

   public void setNames(SortedSet<String> names) {
      this.names = names;
   }

   public void addNames(String... newNames) {
      for (String name : newNames) {
         names.add(name);
      }
   }

   public void removeNames(String... namesToRemove) {
      for (String name: namesToRemove) {
         names.remove(name);
      }
   }

   public List<String> getOrderedNames() {
      return newArrayList(names)  ;
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
