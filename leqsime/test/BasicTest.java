import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void keyPrefixPatternTest() {
        final String message = "blah bla ORA-0001 java.sql.SQLException something is not right";
        assertEquals("java.sql.SQLException", Translation.findKey( message ) );
        assertEquals("Oops, database is too busy.", Translation.translate( message ) );
    }

}
