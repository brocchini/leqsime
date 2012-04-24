package models;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import play.db.jpa.Model;

/**
 * Translates messages by matching a key within the message 
 * 
 * @author Mozart Brocchini
 * @since 04/01/2012
 */
@Entity
public class Translation extends Model
{
  @Column(name = "translation_key")
  public String key;
  
  @Column(name = "translation_type", length = 1)
  public String type;
  
  public String message;
  
  public Translation( String key, String type, String message )
  {
    super();
    this.key = key;
    this.type = type;
    this.message = message;
  }
  
  /**
   * This function finds a unique key in a message and looks for
   * a translation in the internal dictionary.  
   * @param sourceMessage
   * @return
   */
  public static String translate( String sourceMessage )
  {
    String key = findKey( sourceMessage );
    if( key == null )
    {
      return "";
    }
    
    Translation translation = Translation.find( "byKey", key )
      .first();
    if( translation != null )
    {
      return translation.message;
    }
    return "";
  }
  
  @Override
  public String toString()
  {
    return "Translation [id=" + id + ", key=" + key + ", type=" + type
      + ", message=" + message + "]";
  }
  
  /**
   * Looks for words starting with any of the prefixes
   * @return The first key found in the source message
   */
  public static String findKey( String sourceMessage )
  {
    Matcher m = KeyPrefix.pattern().matcher( sourceMessage );
    if ( m.find() )
    {
       return m.group();
    }
    
    return null;
  }
  
}
