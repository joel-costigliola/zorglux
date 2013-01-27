package repositories;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import models.TokenCollection;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AllTokenCollectionsTest extends DatabaseTest {

   @Test
   public void should_return_all_TokenCollection() {
      // When
      Iterable<TokenCollection> tokenCollections = AllTokenCollections.findAll();

      // Then
      assertThat(tokenCollections).hasSize((int) AllTokenCollections.count());
   }

   @Test
   public void should_return_TokenCollection_with_given_name() {
      // Given a newly created TokenCollection
      String name = "test-find-by-name";
      TokenCollection newTokenCollection = new TokenCollection(name);
      newTokenCollection.addTokens("token1", "token2");
      AllTokenCollections.save(newTokenCollection);

      // When
      TokenCollection retrievedTokenCollection = AllTokenCollections.findByName(name);

      // Then
      assertThat(retrievedTokenCollection.getName()).isEqualTo(name);
      assertThat(retrievedTokenCollection.getTokens()).contains("token1", "token2");
   }

   @Test
   public void should_insert_TokenCollection() {
      long count = AllTokenCollections.count();
      // Given
      TokenCollection tokenCollection = new TokenCollection("test");
      tokenCollection.addTokens("1", "a", "z");

      // When
      AllTokenCollections.save(tokenCollection);

      // Then
      assertThat(AllTokenCollections.count()).isEqualTo(count + 1);
   }

   @Test
   @UsingDataSet()
   public void should_remove_TokenCollection() {
      // Given
      assertThat(AllTokenCollections.findByName("elf")).isNotNull();
      assertThat(AllTokenCollections.findByName("dwarf")).isNotNull();

      // When
      AllTokenCollections.remove("elf");
      // Then
      assertThat(AllTokenCollections.findByName("elf")).isNull();

      // When
      AllTokenCollections.remove("dwarf");
      // Then
      assertThat(AllTokenCollections.findByName("dwarf")).isNull();

      // When
      AllTokenCollections.remove("unknown");
      // Then nothing wrong happen
   }

   @Test
   public void should_add_tokens_to_existing_TokenCollection() {
      // Given a newly created TokenCollection
      TokenCollection tokenCollection = new TokenCollection("test-add-tokens");
      tokenCollection.addTokens("token1", "token2");
      int countTokens = tokenCollection.countTokens();
      AllTokenCollections.save(tokenCollection);

      // When I add a new token
      tokenCollection.addTokens("newToken");
      AllTokenCollections.save(tokenCollection);

      // Then
      assertThat(tokenCollection.getTokens()).hasSize(countTokens + 1).contains("newToken");
   }

   @Test
   @UsingDataSet()
   public void should_return_TokenCollection_names() {

      // Given DataSet, When
      Iterable<String> AllTokenCollectionsName = AllTokenCollections.findAllTokenCollectionsName();

      // Then
      assertThat(AllTokenCollectionsName).containsOnly("dwarf", "elf");
   }


}