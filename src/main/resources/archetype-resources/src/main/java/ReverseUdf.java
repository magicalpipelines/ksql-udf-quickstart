package ${package};

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

@UdfDescription(name = "reverse", description = "Example UDF that reverses an object")
public class ReverseUdf {

    @Udf(description = "Reverse string")
    public String reverseString(String source) {
        return new StringBuilder(source).reverse().toString();
    }
}
