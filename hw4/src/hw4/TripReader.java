package hw4;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class TripReader {
	// instance var
	HashMap<String, Trip> Lines = new HashMap<>();

	// constructor
	public TripReader(String filename) {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);
			int x = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				
				if (!line.startsWith("\"trip")&&!line.split(",")[5].matches("\"\"")&&!line.split(",")[8].matches("\"\"")) {
					Trip t = new Trip(line);
					Lines.put(t.getTrip_id(), t);
					//System.out.println(x);
					x++;
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println("file io problem");
		}
	}
	//
	public HashMap<String, Trip> getLines() {
		return Lines;
	}
	public void setLines(HashMap<String, Trip> lines) {
		Lines = lines;
	
	}
}
