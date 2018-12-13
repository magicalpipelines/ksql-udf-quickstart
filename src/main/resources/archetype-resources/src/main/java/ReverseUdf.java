package ${package};

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

/**
 * In this example, we implement a simple UDF for reversing strings and numerics.
 *
 * Example query usage:
 *
 * CREATE STREAM api_responses (username VARCHAR, response_code INT, response_time DOUBLE) \
 * WITH (kafka_topic='api_logs', value_format='JSON');
 *
 * SELECT username, REVERSE(username) \
 * FROM api_responses ;
 */
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
