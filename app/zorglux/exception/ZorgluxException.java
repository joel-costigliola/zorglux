package zorglux.exception;

import java.io.Serializable;

public class ZorgluxException extends RuntimeException implements Serializable {

   private static final long serialVersionUID = -2489340994691939901L;

   public ZorgluxException() {}

   public ZorgluxException(String message, Throwable cause) {
      super(message, cause);
   }

   public ZorgluxException(String message) {
      super(message);
   }

   public ZorgluxException(Throwable cause) {
      super(cause);
   }

   public static void throwExceptionIfNull(Object objectToTestForNull, String descriptionOfObject) {
      if (objectToTestForNull == null) throw new ZorgluxException("Expected a non null" + descriptionOfObject);
   }

   public static void throwExceptionIfTrue(boolean expression, String message) {
      if (expression) throw new ZorgluxException(message);
   }

   public static void throwExceptionIfFalse(boolean expression, String message) {
      throwExceptionIfTrue(!expression, message);
   }

}
