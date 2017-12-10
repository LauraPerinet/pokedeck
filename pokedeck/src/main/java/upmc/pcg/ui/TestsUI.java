package upmc.pcg.ui;

import java.util.Scanner;

/**
 * @author Laura
 */

public interface TestsUI {
	
    final static String error = "Please enter a valid value";
    final static String too_long = "It is too long ! Max is :";
    public final static Scanner console = new Scanner(System.in);
    
    public static String testString( int max_length) {
    	
          String str = "";
          String test = "";
          
          while ( str.equals("") || str.length() > max_length ) {
        	  
              test=console.nextLine();
              
              if ( !test.equals("") ) {
              
            	  while ( test.charAt(0) == ' ' && test.length() > 1 ) {
                      test = test.substring(1);
                  }
                  if ( test.length() > max_length ){
                	  
                      test = " ";
                      print( too_long + max_length );
                  }
              }
              
              if ( !test.equals(" ") ) {
            	  
                 System.out.println( str );
                 str = test;
                 
              } else {
                  str = "";
                  print( error );
              }
          }
          
          return str;
    }
    
    public static int testInt( int not_a_choice, int min, int max) {
        
    	int i = not_a_choice;
        
        while ( i == not_a_choice ) {
            
            if( console.hasNextInt() ) {
            	
                i = console.nextInt();
                
                if( i > max || i < min ) {
                	
                    i = not_a_choice;
                    print( error );
                }
                
            }else{
                i = not_a_choice; 
                print( error );
            }
            console.nextLine();
        }
        return i;
    }
    
    public static char testChar( char[] authorized_values ) {
    	
        char res = ' ';
        
        while ( res==' ' ) {
            String test=console.nextLine();
            
            if ( test.equals("") ) {
                res = ' ';
            }else{
            	
                test = test.toUpperCase();
                
                for ( int i=0 ; i< authorized_values.length ; i++ ) {
                    
                	if ( test.charAt(0) == authorized_values[i] ) {
                        
                    	res = test.charAt(0); 
                        break;
                    }
                }
                
            }
            
            if( res == ' ' ){
                print(error);
            }
        }
        
        return res;    
    }
    
    public static void print( String str){
        System.out.println( str );
    }

}
