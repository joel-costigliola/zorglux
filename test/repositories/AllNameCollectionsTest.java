package repositories;

import models.NameCollection;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AllNameCollectionsTest {

   private static AllNameCollections allNameCollections;

   @BeforeClass
   public static void setupOnce() {
      allNameCollections = new AllNameCollections();
   }

   @Test
   public void should_return_all_NameCollection() {
      // When
      Iterable<NameCollection> nameCollections = allNameCollections.findAll();

      // Then
      assertThat(nameCollections).hasSize((int) allNameCollections.count());
   }

   @Test
   public void should_return_NameCollection_with_given_name() {
      // Given a newly created NameCollection
      String name = "test-find-by-name";
      NameCollection newNameCollection = new NameCollection(name);
      newNameCollection.addNames("name1", "name2");
      allNameCollections.save(newNameCollection);

      // When
      NameCollection retrievedNameCollection = allNameCollections.findByName(name);

      // Then
      assertThat(retrievedNameCollection.getName()).isEqualTo(name);
      assertThat(retrievedNameCollection.getNames()).contains("name1", "name2");
   }

   @Test
   public void should_insert_NameCollection() {
      long count = allNameCollections.count();
      // Given
      NameCollection nameCollection = new NameCollection("test");
      nameCollection.addNames("name1", "name2", "name3");

      // When
      allNameCollections.save(nameCollection);

      // Then
      assertThat(allNameCollections.count()).isEqualTo(count + 1);
   }

   @Test
   public void should_add_names_to_existing_NameCollection() {
      // Given a newly created NameCollection
      NameCollection nameCollection = new NameCollection("test-add-names");
      nameCollection.addNames("name1", "name2");
      int countNames = nameCollection.countNames();
      allNameCollections.save(nameCollection);

      // When I add a new name
      nameCollection.addNames("newName");
      allNameCollections.save(nameCollection);

      // Then
      assertThat(nameCollection.getNames()).hasSize(countNames + 1).contains("newName");
   }
}