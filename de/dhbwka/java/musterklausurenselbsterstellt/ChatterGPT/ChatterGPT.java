import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class ChatterGPT {

	public static void main(String[] args) {
		
		try {
			// Ensure cross platform look&feel on all platforms, including MacOS for foreground/background color support
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
		}
		
		try {
			List<ImageDescription> imageDescs = loadImageDescriptions();
			
			ClassificationsTerm classificationsTerm = new ClassificationsTerm(imageDescs);
			ResultStore classifications = new ResultStore(classificationsTerm);
			
			new ValidationTerm(imageDescs, classifications, 3);			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	private static List<ImageDescription> loadImageDescriptions() throws IOException {		
		// REPLACE/MODIFY CODE BELOW
		List<String> l = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("images.txt"))) {
            while (br.ready()) {
                String line = br.readLine();
				l.add(line);
                System.out.println("Line Read: " + line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

		String[] lines = l.toArray(new String[0]);
		
		List<ImageDescription> result = new LinkedList<>();
		for ( String line : lines ) {
			ImageDescription desc = ChatterGPT.parseLine(line);
			if ( desc != null ) {
				result.add(desc);
			}
		}
		// REPLACE/MODIFY CODE ABOVE
		return result;
	}

	private static ImageDescription parseLine(String line) {
		String[] parts = line.split(";");
		if (parts.length == 3) {
			return new ImageDescription(parts[0], decodeImage(parts[1]), decodeImage(parts[2]));
		}
		return null;
	}
	
	/**
	 * Decode base64 image data
	 *
	 * @param base64
	 *            base64 encoded image data
	 * @return image icon instance
	 */
	private static ImageIcon decodeImage( String base64 ) {
		// common JPEG header data not included in text file, add to base64 data
		base64 = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAA0JCgsKCA0LCgsODg0PEyAVExISEyccHhcgLikxMC4pLSwzOko+MzZGNywtQFdBRkxOUlNSMj5aYVpQYEpRUk//2wBDAQ4ODhMREyYVFSZPNS01T09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT09PT0//wAARCAD6APoDASIAAhEBAxEB/8QA" + base64;
		
		// For compatibility with JDK7 and above, different Java SE classes are being used in a loose coupling manner.
		// They cannot be used directly, that could result in missing imports for different JDKs.

		// if available, use the Base64 class provided in java.util-Package (Java SE 8+, unavailable for Java SE 7)
		try {
			final Class<?> base64Class = Class.forName( "java.util.Base64" );
			final Method getDecoder = base64Class.getDeclaredMethod( "getDecoder" );
			final Object decoder = getDecoder.invoke( null );
			final Method decode = decoder.getClass().getDeclaredMethod( "decode", String.class );

			try ( InputStream input = new ByteArrayInputStream( (byte[]) decode.invoke( decoder, base64 ) ) ) {
				return new ImageIcon( ImageIO.read( input ) );
			}
		}
		catch ( final Exception e ) {
		}

		// if usage of java.util.Base64 failed (e.g. with JDK7), try to use DatatypeConverter (available in Java SE 7-10, only in EE for Java 11)
		try {
			final Class<?> base64Class = Class.forName( "javax.xml.bind.DatatypeConverter" );
			final Method decode = base64Class.getDeclaredMethod( "parseBase64Binary", String.class );

			try ( InputStream input = new ByteArrayInputStream( (byte[]) decode.invoke( null, base64 ) ) ) {
				return new ImageIcon( ImageIO.read( input ) );
			}
		}
		catch ( final Exception e ) {
		}
		return null;
	}	
	
}
