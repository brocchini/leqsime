package models;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;


import play.db.jpa.Model;

@Entity
public class KeyPrefix extends Model {
    @Column(unique=true)
    public String prefix;
    
    public static Pattern pattern()
    {
        final StringBuilder buffer = new StringBuilder();
        
        buffer.append("\\b(");
        List<KeyPrefix> all = KeyPrefix.findAll();
        for (KeyPrefix keyPrefix : all)
        {
          buffer.append(java.util.regex.Pattern.quote( keyPrefix.prefix ) );
          buffer.append( "|");
        }
        
        //Remove last separator bar
        buffer.deleteCharAt( buffer.length() - 1 );
        
        buffer.append(")\\w*\\b");
        Pattern p =  Pattern.compile( buffer.toString()  );
        return p;
        
    }
    
    
}
