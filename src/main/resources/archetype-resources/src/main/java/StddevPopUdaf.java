package ${package};

import io.confluent.ksql.function.udaf.Udaf;
import io.confluent.ksql.function.udaf.UdafDescription;
import io.confluent.ksql.function.udaf.UdafFactory;

/**
 * In this example, we implement a simple UDAF for computing the population standard deviation
 * for a stream of doubles. Minor changes to this class, which are detailed in the comments below,
 * can be made to allow this Udaf to work for table aggregations instead of stream aggregations.
 *
 * Example query usage:
 *
 * CREATE STREAM api_responses (username VARCHAR, response_code INT, response_time DOUBLE) \
 * WITH (kafka_topic='api_logs', value_format='JSON');
 *
 * SELECT username, STDDEV_POP(response_time) \
 * FROM api_responses \
 * GROUP BY username ;
 */
@UdafDescription(name = "stddev_pop", description = "Example UDAF that computes the population standard deviation")
public class StddevPopUdaf {

    @UdafFactory(description = "computes the population standard deviation for doubles")
    // Can be used with stream aggregations
    // For table aggregations, change 'Udaf' to 'TableUdaf' and uncomment the 'undo' method below
    public static Udaf<Double, Double> createStddev() {

        return new Udaf<Double, Double>() {
            // Additional state can be tracked using class variables
            private Long sampleSize = 0L;
            private Double sum = 0.0;
            private Double sumSquares = 0.0;

            // The implementation logic for our UDAF. We can reused this across methods if needed,
            // but feel free to add the implementation details to the appropriate Override methods below
            // if you prefer
            private Double stddev() {
                if (sampleSize < 1L) {
                    return 0.0;
                }
                Double mean = sum / (double) sampleSize;
                Double variance = (sumSquares / (double) sampleSize) - (mean * mean);
                return Math.sqrt(variance);
            }

            // Specify an initial value for our aggregation
            @Override
            public Double initialize() {
                return 0.0;
            }

            // Perform the aggregation. in this example, we delegate to another method, 'stddev',
            // which can useful for table aggregations that contain an 'undo' method that may
            // reuse some of this code
            @Override
            public Double aggregate(final Double currentValue, final Double aggregateValue) {
                sampleSize += 1L;
                sum += currentValue;
                sumSquares += (currentValue * currentValue);
                return stddev();
            }

            // Uncomment if you'd like to implement a table aggregation
            /*
            @Override
            public Double undo(final Double valueToUndo, Double aggregateValue) {
                sampleSize -= 1L;
                sum -= valueToUndo;
                sumSquares -= (valueToUndo * valueToUndo);
                return stddev();
            }
            */

            // Specify what should happen when aggregations are merged
            @Override
            public Double merge(final Double aggOne, final Double aggTwo) {
                return aggTwo;
            }
        };
    }
}