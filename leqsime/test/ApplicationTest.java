import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.*;

import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks()
    {
        Response response = GET( "/" );
        assertIsOk( response );
        assertContentType( "text/html", response );
        assertCharset( play.Play.defaultWebEncoding, response );
    }

    @Test
    public void testTextTranslation() throws UnsupportedEncodingException
    {
        Response response = null;

        response = GET( "/translation/txt/"
                + URLEncoder
                        .encode(
                                "blah bla ORA-0001 java.sql.SQLException something is not right",
                                "utf-8" ) );

        assertIsOk( response );
        assertContentType( "text/plain", response );
        assertCharset( "utf-8", response );
        assertContentEquals( "Oops, database is too busy.", response );
    }

}