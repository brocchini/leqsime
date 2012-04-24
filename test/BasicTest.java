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
        
        final String message2 = "this one uses a partial match (prefix) XAK_PERSON_LOGIN let's make sure prefixes work";
        assertEquals("XAK_PERSON_LOGIN", Translation.findKey( message2 ) );
        
        //Negative test
        final String message3 = "a message that doesn't match FK_PERSON there is no  FK_ is keyprefix in the database";
        assertNull( "Should return null if there is no matching prefix", Translation.findKey( message3 ) );
    }

}
