package ${package};

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReverseUdfTests {

	@Test
	@DisplayName("REVERSE(hello) = olleh")
	void reverseString() {
		ReverseUdf udf = new ReverseUdf();
        assertEquals("olleh", udf.reverseString("hello"), "Reversed string 'hello' should equal 'olleh'");
	}

    @Test
    @DisplayName("REVERSE(27425) = 52472")
    void reverseInteger() {
        ReverseUdf udf = new ReverseUdf();
        assertEquals("52472", udf.reverseInt(new Integer(27425)), "Reversed integer '27425' should equal '52472'");
    }

    @Test
    @DisplayName("REVERSE(27425) = 52472")
    void reverseLong() {
        ReverseUdf udf = new ReverseUdf();
        assertEquals("52472", udf.reverseLong(new Long(27425)), "Reversed long '27425' should equal '52472'");
    }

    @Test
    @DisplayName("REVERSE(27425.02) = 20.52472")
    void reverseDouble() {
        ReverseUdf udf = new ReverseUdf();
        assertEquals("20.52472", udf.reverseDouble(new Double(27425.02)), "Reversed double '27425.02' should equal '20.52472'");
    }
}

