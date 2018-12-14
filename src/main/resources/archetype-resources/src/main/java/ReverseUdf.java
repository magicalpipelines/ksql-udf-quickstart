package ${package};

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

@UdfDescription(name = "reverse", description = "Example UDF that reverses an object")
public class ReverseUdf {

  @Udf(description = "Reverse a string")
  public String reverseString(String source) {
    return new StringBuilder(source).reverse().toString();
  }

  @Udf(description = "Reverse an integer")
  public String reverseInt(Integer source) {
    return new StringBuilder(source.toString()).reverse().toString();
  }

  @Udf(description = "Reverse a long")
  public String reverseLong(Long source) {
    return new StringBuilder(source.toString()).reverse().toString();
  }

  @Udf(description = "Reverse a double")
  public String reverseDouble(Double source) {
    return new StringBuilder(source.toString()).reverse().toString();
  }
}
