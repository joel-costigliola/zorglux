package repositories;

import models.TokenCollection;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AllTokenCollectionsTest extends DatabaseTest {

   private static AllTokenCollections allTokenCollections;

   @BeforeClass
   public static void setupOnce() {
      allTokenCollections = new AllTokenCollections();
   }

   @Test
   public void should_return_all_TokenCollection() {
      // When
      Iterable<TokenCollection> tokenCollections = allTokenCollections.findAll();

      // Then
      assertThat(tokenCollections).hasSize((int) allTokenCollections.count());
   }

   @Test
   public void should_return_TokenCollection_with_given_name() {
      // Given a newly created TokenCollection
      String name = "test-find-by-name";
      TokenCollection newTokenCollection = new TokenCollection(name);
      newTokenCollection.addTokens("token1", "token2");
      allTokenCollections.save(newTokenCollection);

      // When
      TokenCollection retrievedTokenCollection = allTokenCollections.findByName(name);

      // Then
      assertThat(retrievedTokenCollection.getName()).isEqualTo(name);
      assertThat(retrievedTokenCollection.getTokens()).contains("token1", "token2");
   }

   @Test
   public void should_insert_TokenCollection() {
      long count = allTokenCollections.count();
      // Given
      TokenCollection tokenCollection = new TokenCollection("test");
      tokenCollection.addTokens("1", "a", "z");

      // When
      allTokenCollections.save(tokenCollection);

      // Then
      assertThat(allTokenCollections.count()).isEqualTo(count + 1);
   }

   @Test
   public void should_add_tokens_to_existing_TokenCollection() {
      // Given a newly created TokenCollection
      TokenCollection tokenCollection = new TokenCollection("test-add-tokens");
      tokenCollection.addTokens("token1", "token2");
      int countTokens = tokenCollection.countTokens();
      allTokenCollections.save(tokenCollection);

      // When I add a new token
      tokenCollection.addTokens("newToken");
      allTokenCollections.save(tokenCollection);

      // Then
      assertThat(tokenCollection.getTokens()).hasSize(countTokens + 1).contains("newToken");
   }
}