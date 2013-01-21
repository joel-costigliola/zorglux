package repositories;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import models.NameCollection;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.fest.assertions.Assertions.assertThat;

public class AllNameCollectionsTest extends DatabaseTest {

   private static AllNameCollections allNameCollections;

   @BeforeClass
   public static void setupOnce() {
      System.out.println("starting AllNameCollectionsTest");
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
   public void should_return_NameCollection_with_given_name() throws UnknownHostException {
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
   @UsingDataSet()
   @ShouldMatchDataSet()
   public void should_insert_NameCollection() {
      long count = allNameCollections.count();
      // Given
      String name = "test";
      NameCollection nameCollection = new NameCollection(name);
      nameCollection.addNames("name1", "name2", "name3");

      // When
      allNameCollections.save(nameCollection);

      // Then
      NameCollection newlySavedNameCollection = allNameCollections.findByName(name);
      assertThat(allNameCollections.count()).isEqualTo(count + 1);
      assertThat(newlySavedNameCollection.getName()).isEqualTo(name);
      assertThat(newlySavedNameCollection.getNames()).contains("name1", "name2", "name3");
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