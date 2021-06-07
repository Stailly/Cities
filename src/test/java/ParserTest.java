import org.junit.Test;
import service.Parser;

import java.util.ArrayList;

public class ParserTest {

    @Test(expected = NumberFormatException.class)
    public void parseTest2() {
        Parser.parseCities( "src/test/resources/wrongNumber.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseTest3() {
        Parser.parseCities( "src/test/resources/negativeNumber.txt");

    }
}
