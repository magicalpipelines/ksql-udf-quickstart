package ${package};

import io.confluent.ksql.function.udaf.TableUdaf;
import io.confluent.ksql.function.udaf.UdafDescription;
import io.confluent.ksql.function.udaf.UdafFactory;

@UdafDescription(name = "stddev_pop", description = "Example UDAF that computes the population standard deviation")
public class StddevPopUdaf {

    @UdafFactory(description = "computes the population standard deviation for doubles")
    // Can be used with table aggregations
    public static TableUdaf<Double, Double> createStddevForTables() {

        return new TableUdaf<Double, Double>() {
            private Long sampleSize = 0L;
            private Double sum = 0.0;
            private Double sumSquares = 0.0;

            private Double stddev() {
                if (sampleSize < 1L) {
                    return 0.0;
                }
                Double mean = sum / (double) sampleSize;
                Double variance = (sumSquares / (double) sampleSize) - (mean * mean);
                return Math.sqrt(variance);
            }

            @Override
            public Double undo(final Double valueToUndo, Double aggregateValue) {
                sampleSize -= 1L;
                sum -= valueToUndo;
                sumSquares -= (valueToUndo * valueToUndo);
                return stddev();
            }

            @Override
            public Double initialize() {
                return 0.0;
            }

            @Override
            public Double aggregate(final Double currentValue, final Double aggregateValue) {
                sampleSize += 1L;
                sum += currentValue;
                sumSquares += (currentValue * currentValue);
                return stddev();
            }

            @Override
            public Double merge(final Double aggOne, final Double aggTwo) {
                return aggTwo;
            }
        };
    }
}