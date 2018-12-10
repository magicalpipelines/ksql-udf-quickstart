package ${package};

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

@UdfDescription(name = "${udfName}", description = "${udfDescription}")
public class CustomUdf {

  @Udf(description = "${udfDescription}")
  public String apply() {
    // include custom UDF logic here
	return "";
  }
}

