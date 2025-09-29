import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FakeTalk {

   public static void main( String[] args ) {
      List<Quote> quotes = FakeTalk.loadQuotes( "corona.csv" );
      try {
         QuoteSelectionTerm selectionTerm = new QuoteSelectionTerm( quotes, 3, 4 );

         QuoteTerm t1 = new QuoteTerm( "Muenchhausen", selectionTerm );
         QuoteTerm t2 = new QuoteTerm( "Pinocchio", selectionTerm );
         selectionTerm.register( t1 );
         selectionTerm.register( t2 );

         selectionTerm.start();
      } catch ( FakeNewsException e ) {
         e.printStackTrace();
      }
   }

   private static List<Quote> loadQuotes( String filename ) {
      List<Quote> quotes = new LinkedList<>();
      List<String> lines = new ArrayList<>();
      System.out.println("Working dir: " + System.getProperty("user.dir"));

      // Zeilen einlesen als Strings
      File f = new File("corona.csv");
      try (BufferedReader br = new BufferedReader(new FileReader(f))) {
         while (br.ready()) {
               String line = br.readLine();
               lines.add(line);
               System.out.println("Line Read: " + line);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      // REPLACE THE CODE BELOW!
      for ( int i = 1; i <= 25; i++ ) {
         quotes.add( FakeTalk.parseQuote( String.format( lines.get(i - 1) )));
      }
      // REPLACE THE CODE ABOVE!

      return quotes;
   }

   private static Quote parseQuote( String s ) {
      if ( s != null ) {
         String[] parts = s.trim().split( ";" );
         if ( parts.length == 6 || parts.length == 5 ) {
            return new Quote( parts[1], parts[2], parts[3], parts[4], Boolean.parseBoolean( parts[0] ) ? QuoteType.HOT_SHIT : QuoteType.BULLSHIT );
         }
      }
      return null;
   }
}
