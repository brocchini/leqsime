package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        List<Translation> translations = allTranslations();
        render( translations );
    }

    public static void listTranslationsInJson()
    { 
      renderJSON( allTranslations() );
    }
    
    private static List<Translation> allTranslations()
    {
      return Translation.findAll();
    }

    public static void translateMessageAsText(String sourceText)
    { 
      renderText( Translation.translate( sourceText ) );
    }
}