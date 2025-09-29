import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DBVasion {
	
	public static void main(String[] args) {
		try {
			// Ensure cross platform look&feel on all platforms, including MacOS for foreground/background color support
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
		}
		
		try {
            List<TrainConnection> connections = loadConnections();

			new ConnectionSelectionTerm(connections);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
	
	public static List<TrainConnection> loadConnections() throws DBException {
		List<TrainConnection> conns = new LinkedList<>();
		List<String> lines = new ArrayList<>();
		// REPLACE CODE BELOW
		File f = new File("connections.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while (br.ready()) {
                String line = br.readLine();
				lines.add(line);
                System.out.println("Line Read: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		
		for ( String dummy : lines ) {
			TrainConnection tc = DBVasion.parseTrainConnection(dummy);
			if ( tc!=null ) {
				conns.add(tc);
			}
		}
		// REPLACE CODE ABOVE
		
		for ( TrainConnection tc : conns ) {
			tc.setStops(DBVasion.loadTrainStops(tc.getName()));
		}
		
		return conns;
	}
		
	private static List<Stop> loadTrainStops(String trainName) throws DBException {
		String filename = trainName + ".txt"; // train name "ICE-123" => ICE-123.txt
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                String line = br.readLine();
				lines.add(line);
                System.out.println("Line Read: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		List<Stop> stops = new LinkedList<>();
		
		// REPLACE CODE BELOW
		// !! Adapt loadConnections first. The dummy connections !!
		// !! ICE-1, ICE-2 and ICE-3 do not have matching        !!
		// !! connection stop files.                             !!
		for ( String dummy : lines ) {
			Stop stop = DBVasion.parseStops(dummy);
			if ( stop != null ) {
				stops.add(stop);
			}
		}
		// REPLACE CODE ABOVE

		return stops;
	}
	
	private static TrainConnection parseTrainConnection(String line) {
		String[] parts = line.split("[;]");
		if ( parts.length == 5 ) {
			boolean regional = Boolean.parseBoolean(parts[3]);
			double price = Double.parseDouble(parts[4]);
			return new TrainConnection(parts[0],parts[1],parts[2], regional, price);
		}
		return null;
	}

	
	private static Stop parseStops(String line) throws DBException {
		String[] parts = line.split("[;]");
		if ( parts.length == 4 ) {
			String name = parts[2];
			int stayPeriod = Integer.parseInt(parts[1]);
			int stage = Integer.parseInt(parts[3]);
			
			String[] timeParts = parts[0].split("[:]");
			Time time = new Time(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]));
			
			
			return new Stop(name, time, stayPeriod, stage);
		}
		return null;
	}
	
	public static ImageIcon createDLTicketIcon() {
		int barH = 4;
		int width = 16;
		BufferedImage img = new BufferedImage(width, barH*3, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = img.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, barH);
		g.setColor(Color.RED);
		g.fillRect(0, barH, width, barH);
		g.setColor(Color.YELLOW);
		g.fillRect(0, barH*2, width, barH);
		g.dispose();
		return new ImageIcon(img);
	}

}
