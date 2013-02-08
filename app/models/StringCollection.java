package models;

import org.jongo.marshall.jackson.id.Id;

public class StringCollection {
   @Id
   protected String id;
   protected String name;

   public StringCollection(String name) {
      super();
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void renameTo(String newName) {
      this.name =  newName;
   }

}
